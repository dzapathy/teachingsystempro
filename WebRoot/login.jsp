<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!-- 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
    -->
	<title>login</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/header.css">
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
	     <a href="register.jsp"><button type="button" id="a" class="btn">注册</button></a>
	    </nav>
	</header>
	<div class="row img-rounded">	
	  <div class="row1 img-rounded"><h3>登陆</h3><p style="color: orange;">${tip}</p></div>	
	  <form id="form1" action="login" method="post">
	    
	    <fieldset class="form-group">
	      <label for="formGroupExampleInput">用户名</label>
	      <input type="text" class="form-control" name="instructor.iid" id="formGroupExampleInput" placeholder="username" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="formGroupExampleInput2">密码</label>
	      <input type="password" class="form-control" name="instructor.ipassword" id="formGroupExampleInput2" placeholder="password" required>
	      </fieldset>
	    <input id="in3" type="submit" class="btn btn-success" value="登陆"/>
	  </form>	             
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
</html>
