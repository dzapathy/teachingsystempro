<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!-- 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
    -->
	<title>注册</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/register.css">
</head>
<body>
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
	      <a class="nav-item nav-link " href="#">关于我们</a>
	    </nav>    
	  </div>
	  <nav class="nav navbar-nav navbar-right">
	     <a href="login.jsp"><button type="button" id="a" class="btn btn-warning-outline pull-right">登陆</button></a>
	    </nav>
	</header>
	<div class="row img-rounded">

  	<div class="row1 img-rounded"><h3>注册</h3><p style="color: orange;">${tip}</p></div>	
	  <form id="form1" action="register" method="post">
	    <fieldset class="form-group">
	      <label for="id">职工号</label>
	      <input type="text" class="form-control" name="instructor.iid" id="id" placeholder="userid" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="name">用户名</label>
	      <input type="text" class="form-control" name="instructor.iname" id="name" placeholder="username" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="email">邮箱</label>
	      <input type="email" class="form-control" name="instructor.iemail" id="email" placeholder="email" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="password">密码</label>
	      <input type="password" class="form-control" name="instructor.ipassword" id="password" placeholder="password" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="password1">确认密码</label>
	      <input type="password" class="form-control" id="password1" placeholder="password" required>
	    </fieldset>
	    <input id="in3" type="submit" class="btn btn-success" value="注册">
	  </form>            
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/register.js"></script>
</body>
