<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">		
	<title>播放</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
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
			<div class="col-sm-4 col-sm-offset-2">
				<ol class="breadcrumb">
				  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
				  <li class="active"><%=new String( request.getParameter("name").getBytes("ISO-8859-1"),"utf-8") %></li>
				</ol>
			</div>		
			<div class="col-sm-8 col-sm-offset-2">
				<video  src="<%=request.getParameter("url") %>"
					 controls="controls" autoplay="autoplay" width="100%"></video>
			</div>
		</div>	
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
	<script src="js/html5media.min.js"></script>
</body>
