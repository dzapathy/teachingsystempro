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
	<title>成绩列表</title>
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
			  <li class="active">查看成绩</li>
			</ol>
		</div>
		<div class="col-sm-2 col-sm-offset-3">
			<button class="btn btn-primary-outline" id="daoGrade" hidden>导出Excel</button>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>课序号</th>
							<th>学号</th>
							<th>姓名</th>
							<th>成绩</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" id="grade" status="g">
						<tr>
							<td><s:property value="#g.count"/> </td>
							<td>${grade[0] }</td>
							<td>${grade[1] }</td>
							<td>${grade[2] }</td>
							<td>
								<s:if test="#grade[3] == null">
									<a href="mark?stid=${grade[1] }&pfid=${pfid}" class="btn btn-primary-outline">批阅</a>
								</s:if>
								<s:else>
									${grade[3] }
								</s:else>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2 col-sm-offset-5" hidden>
				<button class="btn btn-success-outline btn-block" id="more">加载更多</button>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/showgrade.js"></script>
</body>
