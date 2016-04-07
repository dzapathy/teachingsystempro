<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>发布话题</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
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
				  <li><a href="post.jsp">课程论坛</a></li>
				  <li class="active">发布话题</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<form id="send">
					<fieldset class="form-group">
					    <label for="title">标题</label>
					    <input type="text" class="form-control" name="posts.ptitle" id="title" placeholder="请输入标题" required>
					 </fieldset>
					 <fieldset class="form-group">
					 	<label for="content">内容</label>
					 	<textarea class="form-control" name="posts.pcontent" id="content" style="width:100%;height:500px;" required></textarea>
					 </fieldset>
					 <button type="submit" class="btn btn-primary" onclick="getContent()">提交</button>
				</form>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="umeditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/createpost.js"></script>
    <script type="text/javascript">
    	var um = UM.getEditor('content');
    	function getContent() {
	        alert(um.getContent());
	    }
    </script>
	
</body>
