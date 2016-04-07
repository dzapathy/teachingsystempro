<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
    -->
	<title>个人信息</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/information.css" rel="stylesheet">
</head>
<body>
	<!-- 
	<button class="btn btn-primary-outline" id="basic">提交基本信息</button>
	<button class="btn btn-primary-outline" id="pass">提交密码信息</button>
	<button class="btn btn-primary-outline" id="pic">提交头像信息</button>
	-->
	<header class="navbar navbar-dark navbar-static-top bd-navbar bg-inverse" role="banner">
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
	  <nav class="nav navbar-nav navbar-right">	  	
	    <img src="${sessionScope.USER_URL }">  				    
	  	<div class="dropdown">	  	 	     
	       <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"   aria-expanded="false">${sessionScope.USER_NAME}</a>		  		     	
		   <div class="dropdown-menu" aria-labelledby="dLabel">
		       <a class="dropdown-item" href="getInfo">修改资料</a>
		       <a class="dropdown-item" href="exit">退出</a>
		   </div>
	   </div>
	  </nav>
	</header>
	<div id="leftnav">
	  <div>
	    <button id="btn1" type="button" class="btn btn-success-outline">更换头像</button>
	    <button id="btn2" type="button" class="btn btn-success-outline">重置密码</button>
	    <button id="btn3" type="button" class="btn btn-success-outline">修改资料</button>
	  </div>
	</div>	
	<div id="content">
	  <div id="con1">
	     <p>选择图片<input id="in1" type="file"></p>
	     <div><img id="con1img" alt="head portrait" src="${instructor.iurl}"></div>
	     <button id="a" type="button" class="btn btn-success">确定</button>
	  </div>
	  <div id="con2">
	    <form id="form1">
		    <fieldset class="form-group">
		      <label for="prepass">原密码</label>
		      <input type="password" class="form-control" name="instructor.ipassword" id="prepass" placeholder="原密码" required>
		    </fieldset>
		    <fieldset class="form-group">
		      <label for="new">新密码</label>
		      <input type="password" class="form-control" id="new" name="newpassword" placeholder="新密码" required>
		    </fieldset>
		    <fieldset class="form-group">
		      <label for="confirm">确认密码</label>
		      <input type="password" class="form-control" id="confirm" placeholder="确认密码" required>
		    </fieldset>
		    <input id="in2" type="submit" class="btn btn-success" value="完成">
	   </form>
	  </div>
	  <div id="con3">
	    <form id="form2">
	    <fieldset class="form-group">
	      <label for="id">id</label>
	      <input type="text" class="form-control" id="id" name="instructor.iid" value="${instructor.iid }" disabled>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="name">名字</label>
	      <input type="text" class="form-control" id="name" name="instructor.iname" value="${instructor.iname }" disabled>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="phone">手机</label>
	      <input type="text" class="form-control" id="phone" name="instructor.iphone" value="${instructor.iphone }">
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="mail">邮箱</label>
	      <input type="text" class="form-control" id="mail" name="instructor.iemail" value="${instructor.iemail }">
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="introduction">简介</label>
	      <textarea class="form-control" name="instructor.iintroduction" id="introduction">${instructor.iintroduction }</textarea>
	    </fieldset>
	    <input id="in3" type="submit" class="btn btn-success" value="完成">
	   </form>
	  </div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/information.js"></script>
</body>
