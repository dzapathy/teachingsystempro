<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!-- 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    -->	
	<title>课程清单</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/courselist.css" rel="stylesheet">
</head>
<body>
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
	  	<div class="dropdown ">	  	 	     
	       <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"   aria-expanded="false">${sessionScope.USER_NAME}</a>		  		     	
		   <div class="dropdown-menu" aria-labelledby="dLabel">
		       <a class="dropdown-item" href="getInfo">修改资料</a>
		       <a class="dropdown-item" href="exit">退出</a>
		   </div>
	   </div>
	  </nav>
	</header>
		
	<!--  
	<div id="content">
	-->
	<div class="row">
		<div class="col-sm-1">
			<a class="btn btn-success-outline" href="createcourse.jsp">添加课程</a>
		</div>
		<div class="col-sm-8 col-sm-offset-1" style="background-color: white;border-radius: 6px;">
		   <s:if test="courses.size()==0">
		   		您还没有课程,赶快添加课程吧.
		   </s:if>
		   <s:else>			   
		   <ul class="list-group" id="coulist">			
				<s:iterator value="courses" id="course">
					<li class="list-group-item">
						<div class="media">
						  <a class="media-left" href="courseDetail?cid=${course.cid}" data-cid="${course.cid}">
						    <img class="media-object" width="100" height="100" src="${course.curl}" alt="${course.cname}">
						  </a>
						  <div class="media-body">
						    <h4 class="media-heading">${course.cname}</h4>
						     ${course.ccontent}
						  </div>
						</div>
					</li>
				</s:iterator>
			</ul>
			</s:else>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
