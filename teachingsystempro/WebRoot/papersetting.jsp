<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>创建试卷</title>
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
		<div class="col-sm-5 col-sm-offset-1">
			<ol class="breadcrumb">
			  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
			  <li><a href="getpapers">考试系统</a></li>
			  <li class="active">创建试卷</li>
			</ol>
		</div>
		<div class="col-sm-8 col-sm-offset-2">
			<form action="createpaper">
				<div class="form-group row">
				    <label for="pftype" class="col-sm-2 form-control-label">试卷类型</label>
				    <div class="col-sm-10">
				      <select id="pftype" class="form-control" name="pftype">
				        <option value="0">选择试卷类型</option>
				        <option value="0">班级小考</option>
				        <option value="1">统一大考</option>
				      </select>
				    </div>
				 </div>
				 <div class="form-group row">
				    <label for="startChapter" class="col-sm-2 form-control-label">起始章节</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" name="startChapter" id="startChapter" placeholder="选择起始章节" required>
				    </div>
				 </div>
				 <div class="form-group row">
				    <label for="endChapter" class="col-sm-2 form-control-label">结束章节</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" name="endChapter" id="endChapter" placeholder="选择结束章节" required>
				    </div>
				 </div>
				 <div class="form-group row">
				    <label for="choiceNum" class="col-sm-2 form-control-label">选择题数</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" name="choiceNum" id="choiceNum" placeholder="选择题数" required>
				    </div>
				 </div>
				 <div class="form-group row">
				    <label for="blankNum" class="col-sm-2 form-control-label">填空题数</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" name="blankNum" id="blankNum" placeholder="填空题数" required>
				    </div>
				 </div>
				 <div class="form-group row">
				    <label for="subquesNum" class="col-sm-2 form-control-label">主观题数</label>
				    <div class="col-sm-10">
				      <input type="number" class="form-control" name="subquesNum" id="subquesNum" placeholder="主观题数" required>
				    </div>
				 </div>
				 <div class="form-group row">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-success-outline">点击抽取</button>
				    </div>
				 </div>
			</form>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
