<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>课程论坛</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-light bg-faded">
		  <a class="navbar-brand" href="#">教学系统</a>
		  <ul class="nav navbar-nav">
		    <li class="nav-item active">
		      <a class="nav-link" href="courseCenter">课程中心 <span class="sr-only">(current)</span></a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="#">关于我们</a>
		    </li>
		  </ul>
		</nav>
		<hr>
		<div class="row">
			<div class="col-sm-5 col-sm-offset-1">
				<ol class="breadcrumb">
				  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
				  <li class="active">课程论坛</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-1">
				<ul class="list-group">					
						<s:iterator value="list" id="post">
							<li class="list-group-item">
								<h5>
									<a href="getreply?pid=${post[0]}">${post[1] }</a>
								</h5>
								<p>${post[2] } / <span class="pull-right">${post[3]}</span></p>
							</li>
						</s:iterator>					
				</ul>
				<!-- 分页 -->
				<nav>
				  <ul class="pager">
				  	<!-- 上一页 -->
				  	<s:if test="page !=1">
				  		<li><a href="getposts?page=${page}&tag=0">上一页</a></li>
				  	</s:if>
				  	<s:else>
				  		<li class="disabled"><a href="javascript:void(0)" >上一页</a></li>
				  	</s:else>
				  	<!-- 显示页码/总页数 -->				  	
				  	<s:if test="pageTotal%pageSize==0">
				  		<s:set value="pageTotal/pageSize" name="count"></s:set>	
				  	</s:if>
				  	<s:else>
				  		<s:set value="pageTotal/pageSize+1" name="count"></s:set>
				  	</s:else>    
				    <li>${page}/${count}</li>
				    <li hidden id="ptotal">${pageTotal}</li>
				    <!-- 下一页 -->
				    <s:if test="#count > page">
				    	<li><a href="getposts?page=${page}&tag=1">下一页</a></li>
				  	</s:if>
				  	<s:else>
				  		<li class="disabled"><a href="javascript:void(0)" >下一页</a></li>
				  	</s:else>
				  </ul>
				</nav>
			</div>
			<div class="col-sm-3 col-sm-offset-1">
				<div class="row">
					<ul class="list-group">
						<li class="list-group-item list-group-item-info">
	    					<a href="javascript:void(0)" id="getno">无人回复的话题</a>
	    					<span id="nrcount" class="label label-success pull-right">0</span>
						</li>
					</ul>					
					<div class="list-group" id="nrlist">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/post.js"></script>
</body>
