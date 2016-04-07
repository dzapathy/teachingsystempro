<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	<title>创建课程</title>
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
			<div class="col-sm-10 col-sm-offset-1">
				<h4>创建课程</h4>
				<hr>
				<form action="addCourse" method="post" enctype="multipart/form-data">
					<fieldset class="form-group">
					    <label for="cname">课程名:</label>
					    <input type="text" class="form-control" name="course.cname" id="cname" placeholder="课程名" required>
					</fieldset>
					<fieldset class="form-group">
					    <label for="cchapter">章节数:</label>
					    <input type="text" class="form-control" name="course.cchapter" id="cchapter" placeholder="章节数" required>
					</fieldset>
					<fieldset class="form-group">
					    <label for="ccredit">学分:</label>
					    <input type="text" class="form-control" name="course.ccredit" id="ccredit" placeholder="学分" required>
					</fieldset>
					<fieldset class="form-group">
					    <label for="ccontent">课程简介:</label>
					    <textarea class="form-control" name="course.ccontent" id="ccontent" placeholder="课程简介" required></textarea>
					</fieldset>
					<fieldset class="form-group">
						<label for="curl">课程图片:</label>
						<input type="file" class="form-contral" name="picUpload" id="curl" required accept="image/*">
						<small class="text-muted">请上传正方形图片</small>
					</fieldset>
					<button type="submit" class="btn btn-primary-outline">确定</button>
				</form>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/createcourse.js"></script>
</body>
