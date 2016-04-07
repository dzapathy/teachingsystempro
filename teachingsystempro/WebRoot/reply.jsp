<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>回复</title>
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
		<div class="col-sm-5 col-sm-offset-1">
			<ol class="breadcrumb">
			  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
			  <li><a href="getposts">课程论坛</a></li>
			  <li class="active">${list[0].get(0)[4]} 的回复</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-sm-9 col-sm-offset-1">
				<h4 id="pid" data-pid="${pid}">Question:</h4>
				<hr>
				<div class="media">
				  <a class="media-left" href="#">
				    <img class="media-object img-circle" width="50" height="50" src="${list[0].get(0)[3]}" alt="Generic placeholder image">
				  </a>
				  <div class="media-body">
				    <h4 class="media-heading">${list[0].get(0)[1]} </h4>
				    ${list[0].get(0)[5]} / ${list[0].get(0)[6]}
				    <p> <small >联系方式: ${list[0].get(0)[2]}</small></p>			    
				  </div>
				</div>
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<h4>Answer:</h4>
				<hr>
				<ul class="list-group">
					<s:if test="list[1].size() == 0 && list[2].size() == 0">
						暂无回复.
					</s:if>
					<s:else>
					<!-- instructor -->
				  	<s:iterator value="list[1]" id="ins">
					  <li class="list-group-item">
					  	<div class="media">
						  <a class="media-left" href="#">
						    <img class="media-object img-circle" width="50" height="50" src="${ins[3]}" alt="Generic placeholder image">
						  </a>
						  <div class="media-body">
						    <h4 class="media-heading">${ins[1]}</h4>
						    ${ins[5]} / ${ins[6]}
						    <p><small>联系方式: ${ins[2]}</small></p>
						  </div>
						</div>
					  </li>
				    </s:iterator>
				    <s:iterator value="list[2]" id="stu">
				    	<li class="list-group-item">
					  	<div class="media">
						  <a class="media-left" href="#">
						    <img class="media-object img-circle" width="50" height="50" src="${stu[3]}" alt="Generic placeholder image">
						  </a>
						  <div class="media-body">
						    <h4 class="media-heading">${stu[1]}</h4>
						    ${stu[5]} / ${stu[6]}
						    <p><small>联系方式: ${stu[2]}</small></p>
						  </div>
						</div>
					  </li>
				    </s:iterator>
				    </s:else>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<hr>
				<script type="text/plain" id="myEditor" style="width:100%;height:240px;"></script>
				<hr>
				<button class="btn btn-success-outline" id="rep">回复</button>
			</div>		
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="umeditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/reply.js"></script>
    <script type="text/javascript">
    	var um = UM.getEditor('myEditor');
    </script>
</body>
