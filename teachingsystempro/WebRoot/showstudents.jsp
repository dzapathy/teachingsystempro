<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">		
	<title>${course.cname}学生列表</title>
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
				  <li class="active">查看学生</li>
				</ol>
			</div>
			<div class="col-sm-2 col-sm-offset-3">
				<a  href="studentdao.jsp" class="btn btn-success-outline">导入学生</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<hr>
				<table class="table table-hover table-sm">
					<thead>
						<tr>
							<th>序号</th>
							<th>学号</th>
							<th>姓名</th>
							<th>邮箱</th>
							<th>课序号</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>	
						<s:iterator value="list" id="item" status="st">				
							<tr>
								<td><s:property value="#st.count+(page-1)*pageSize"/> </td>
								<td>${item[0] }</td>
								<td>${item[1] }</td>
								<td>${item[2] }</td>
								<td>${item[3] }</td>
								<td><button class="btn btn-warning-outline removeS">删除</button></td>							
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<!-- 分页 -->
				<nav>
				  <ul class="pager">
				  	<!-- 上一页 -->
				  	<s:if test="page !=1">
				  		<li><a href="showstudents?page=${page}&pageTotal=${pageTotal}&tag=0">上一页</a></li>
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
				    	<li><a href="showstudents?page=${page}&pageTotal=${pageTotal}&tag=1">下一页</a></li>
				  	</s:if>
				  	<s:else>
				  		<li class="disabled"><a href="javascript:void(0)" >下一页</a></li>
				  	</s:else>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/showstudents.js"></script>
</body>
