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
	<link href="css/header.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<header class="navbar navbar-dark navbar-static-top bd-navbar" role="banner">
	  <div class="clearfix">	  
	    <button class="navbar-toggler pull-right hidden-sm-up" type="button" data-toggle="collapse" data-target="#bd-main-nav">
	      &#9776;
	    </button>
	    <a class="navbar-brand hidden-sm-up" href="#">
	     	 教学系统
	    </a>
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
