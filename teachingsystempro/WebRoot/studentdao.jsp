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
				  <li><a href="showstudents">查看学生</a></li>
				  <li class="active">导入学生</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<form action="studentdao" method="post" class="form-inline" id="studao" enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="seid">课序号:</label>
				    <select name="seid" id="seid" class="form-control" required>
				       <s:iterator value="#session.seid" id="se">
				       		<option>${se}</option>
				       </s:iterator>			       
				    </select>
				  </div>
				  <div class="form-group">
				    <label>Excel:</label>
				    <input type="file" class="form-control" id="excel" name="excel" required>
				  </div>
				  <button type="submit" class="btn btn-primary">导入学生</button>
				</form>
				<hr>
				<s:if test="list !=null && list.size()!=0">
				<div class="alert alert-warning">
  					<strong>以下学号的学生未注册,无法导入！</strong>
  					<ul class="list-group" style="background-color: orange;">
  						<s:iterator value="list" id="l">
  							<li class="list-group-item list-group-item-warning">${l }</li>
  						</s:iterator>
  					</ul>
				</div>
				</s:if>
				<s:if test="list !=null && list.size()==0">
					<p>导入成功...<a href="showstudents" class="btn btn-success-outline">返回学生列表</a> </p>
				</s:if>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
