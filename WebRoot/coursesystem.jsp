<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<title>课程体系</title>
	<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">	
	<script src="//cdn.bootcss.com/vue/1.0.24/vue.min.js"></script>
	<script src="//cdn.bootcss.com/vue-resource/0.7.2/vue-resource.min.js"></script>
	<script type="text/javascript" src="http://d3js.org/d3.v2.js"></script>
<style type="text/css">
.link { stroke: green; stroke-linejoin:bevel;}
.link_error{
    stroke:red;
    stroke-linejoin:bevel;
}
.nodetext {

    font: 12px sans-serif;
    -webkit-user-select:none;
    -moze-user-select:none;
    stroke-linejoin:bevel;
    
}
#container{
    //width:800px;
    height:600px;
    border:1px solid gray;
    border-radius:5px;
    //position:relative;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<header class="navbar navbar-dark navbar-static-top bd-navbar" role="banner">
	  		<div class="clearfix">	  
			    <button class="navbar-toggler pull-right hidden-sm-up" type="button" data-toggle="collapse" data-target="#bd-main-nav">
			      &#9776;
			    </button>
			    <a class="navbar-brand hidden-sm-up" href="#">教学系统</a>
	  		</div>
		  	<div class="collapse navbar-toggleable-xs" id="bd-main-nav">
		    	<nav class="nav navbar-nav">
		      		<a class="nav-item nav-link active" href="#">教学系统</a>
		     		<a class="nav-item nav-link " href="courseCenter">课程中心</a>
		      		<a class="nav-item nav-link " href="#">关于我们</a>
		    	</nav>    
		  	</div>
			<nav id="headerright" class="nav navbar-nav navbar-right">	  	
			   <img src="${sessionScope.USER_URL }">  				    
			   <div class="dropdown ">	  	 	     
			       <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"   aria-expanded="false">${sessionScope.USER_NAME}</a>		  		     	
				   <div class="dropdown-menu" aria-labelledby="dLabel">
				       <a class="dropdown-item" href="getInfo">修改资料</a>
				       <a class="dropdown-item" href="exit">退出</a>
				   </div>
			   </div>
			 </nav>
	    </header>
		<hr>
		<div class="col-sm-5 col-sm-offset-1">
			<ol class="breadcrumb">
			  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
			  <li class="active">{{title}}</li>			  
			</ol>
		</div>
		<div class="col-sm-offset-1">
			<button class="btn btn-success-outline" @click="change" data-toggle="modal" data-target=".bd-example-modal-lg">修改体系结构</button>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>课程</th>
							<th>前驱课程</th>
						</tr>						
					</thead>
					<tbody>
						<template v-for="l in list">
							<tr>
								<td data-id="{{l.id}}">{{l.name}}</td>
								<td data-pid="{{l.pid}}">{{l.pname}}</td>
							</tr>
						</template>
					</tbody>
				</table>
				<div id='container'></div>
			</div>
		</div>
		
		<!-- 模态框 -->
		<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		          <span class="sr-only">Close</span>
		        </button>
		        <h4 class="modal-title" id="myModalLabel">更新课程体系</h4>
		      </div>
		      <div class="modal-body">
		          	<h6>前驱课程</h6>
		          	<hr>
		          	<table class="table table-striped">
		          		<thead>
						<tr>
							<th>课程号</th>
							<th>名称</th>
						</tr>						
					</thead>
					<tbody>
						<template v-for="pre in precourse">
							<tr>
								<td>{{pre.pid}}</td>
								<td>{{pre.pname}}</td>
							</tr>
						</template>
					</tbody>
		          	</table>
		          	<h6>修改前导课程</h6>
		          	<select multiple class="form-control" v-model="selected">
				      <option v-for="c in courselist" :value="c">{{c.name}}</option>
				    </select>
				    <hr>
				    <p class="text-primary">已选择: {{selected | json}}</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" @click="save">Save</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<%
		Integer cid = ((Course)session.getAttribute("course")).getCid();		
	 %>
	<script>
		var v = new Vue({
			el:".container-fluid",
			data: {
				title:"课程体系",
				list: [],
				precourse:[],
				courselist:[],
				selected:[]
			},
			ready:function(){
				this.$http.get("getcoursesystem",{cid:<%=cid%>}).then(function(response){
					this.$set('list', response.data);
					var list = v.list;
		
					var topology=new Topology('container');
					
					var nodes = [];
					var links = [];
					
					var arr = [];
					for(var l in list){
						console.log(list[l]);
						if(arr.indexOf(list[l].id)==-1){
							arr.push(list[l].id);
							nodes.push({id:list[l].name,type:'switch',status:1});
						}
						if(arr.indexOf(list[l].pid)==-1){
							arr.push(list[l].pid);
							nodes.push({id:list[l].pname,type:'switch',status:1});
						}
						links.push({source:list[l].name,target:list[l].pname});
					}
					
					topology.addNodes(nodes);
					topology.addLinks(links);
					//可展开节点的点击事件
					topology.setNodeClickFn(function(node){
					    if(!node['_expanded']){
					        expandNode(node.id);
					        node['_expanded']=true;
					    }else{
					        collapseNode(node.id);
					        node['_expanded']=false;
					    }
					});
					topology.update();
					
				}
				,function(response){
				
				});
			},
			methods:{
				change :function(){
					this.$http.get("getprecourse").then(function(response){						
						this.$set('precourse', response.data);
					},function(response){
						//错误处理
					});
					this.$http.get("getallcourse").then(function(response){						
						this.$set('courselist', response.data);								
					},function(response){
						//错误处理
					});
				},
				save:function(){
					this.$http.get("changeprecourse",{coursePre:JSON.stringify(this.selected)}).then(function(response){
						location.reload();
					},function(response){
						//错误处理
					});
				}
			}
		});
				
		
		
		
		function expandNode(id){
		    topology.addNodes(childNodes);
		    topology.addLinks(childLinks);
		    topology.update();
		}
		
		function collapseNode(id){
		    topology.removeChildNodes(id);
		    topology.update();
		}
	</script>
</body>
