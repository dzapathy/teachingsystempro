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
	<title>在线批阅</title>
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
			  <li><a href="showgrade?pfid=${pfid }">学生列表</a></li>
			  <li class="active">批阅</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<h4><strong>批阅主观题</strong></h4>
				<hr>
				<ul class="list-group">
					<s:if test="list.size() != 0">
						<s:iterator value="list" id="ques" status="q">
						<li class="list-group-item">
							<p>${q.count }.${ques[1] }</p>
							<p>总分:${ques[4] }</p>
							<p>学生答案:${ques[2] }</p>
							<div class="input-group ">
							  <s:if test="#ques[3] == null">
							      <input type="number" class="form-control" placeholder="输入得分...">
							      <span class="input-group-btn">
							        <button data-pid="${ques[0] }" class="btn btn-primary-outline check" type="button">确定</button>
							      </span>
						      </s:if>
						      <s:else>
						      	  <input type="number" class="form-control" placeholder="输入得分..." value="${ques[3]}" disabled>
							      <span class="input-group-btn">
							        <button data-pid="${ques[0] }" class="btn btn-primary-outline" type="button" disabled>确定</button>
							      </span>
						      </s:else>
						    </div>							
						</li>
						</s:iterator>
						<div class="col-sm-4 col-sm-offset-4">
							<hr>
							<button data-pfid="${pfid}" data-stid="${stid }" class="btn btn-success-outline btn-block" id="setgrade">批阅完成,整合分数</button>
						</div>
					</s:if>
					<s:else>
						暂未答题.
					</s:else>
				</ul>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/mark.js"></script>
</body>
