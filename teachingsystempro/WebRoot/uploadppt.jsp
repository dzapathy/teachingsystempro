<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>上传视频</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="uploadify/uploadify.css" rel="stylesheet">
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
					<li class="active">
						<span class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" id="choose" aria-haspopup="true" aria-expanded="false">资源管理</a>
							<span class="dropdown-menu" aria-labelledby="choose">
								<a class="dropdown-item" href="mgetvideos">视频资源管理</a>
	  							<a class="dropdown-item" href="mgetppts">PPT资源管理</a>
							</span>
						</span>
					</li>
					<li>PPT资源上传</li>
				</ol>
			</div>
			<div class="col-sm-2 col-sm-offset-4">
				<a href="mgetppts" class="btn btn-success" >返回PPT列表</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">		
				<hr>
				<select class="form-control" id="chap">
					<option>请选择章节</option>
					<%
						Course course=(Course) session.getAttribute("course");
						int chapter = course.getCchapter();
						for(int i = 1 ; i < chapter+1 ;i++ ){
					%>
						<option value="<%=i%>">第<%=i %>章</option>
					<%} %>						
				</select>
				<hr>	
				<input type="file" id="uploadify" name="uploadify" class="form-control">    
				<div id="fileQueue"></div>    
				<a href="javascript:$('#uploadify').uploadify('upload','*')" class="btn btn-success-outline">开始上传</a>    
				<a href="javascript:$('#uploadify').uploadify('cancel')" class="btn btn-warning-outline">取消上传</a> 
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="uploadify/jquery.uploadify.min.js"></script>
	<script src="js/uploadppt.js"></script>
</body>
