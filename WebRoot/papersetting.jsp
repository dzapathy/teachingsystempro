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
