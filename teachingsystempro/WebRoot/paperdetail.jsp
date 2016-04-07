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
	<title>试卷详情</title>
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
			  <li class="active">试卷详情</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<h4><strong>一.选择题</strong></h4>
				<hr>
				<ul class="list-group">
					<s:if test="list[0].size() != 0">
						<s:iterator value="list[0]" id="choice" status="c">
						<li class="list-group-item">
							<p>${c.count}&nbsp;${choice[0]}<br>${choice[1]}</p>
							<p>参考答案:${choice[2] }</p>
							<p>解释:${choice[3] }</p>
							<p>分数:${choice[4] }分&nbsp;&nbsp;
								<s:if test="#choice[5] != null && #choice[5] !=''">
									<button data-url="${choice[5] }" class="btn btn-success-outline showmedia">查看媒体</button>
								</s:if>
							</p>
						</li>
						</s:iterator>
					</s:if>
					<s:else>
						暂无试题.
					</s:else>
				</ul>
				<hr>
			</div>
			<div class="col-sm-8 col-sm-offset-2">
				<h4><strong>二.填空题</strong></h4>
				<hr>
				<ul class="list-group">
					<s:if test="list[1].size() != 0">
						<s:iterator value="list[1]" id="blank" status="b">
						<li class="list-group-item">
							<p>${b.count}.&nbsp;${blank[0]}</p>
							<p>参考答案:${blank[1] }</p>
							<p>解释:${blank[2] }</p>
							<p>分数:${blank[3] }分&nbsp;&nbsp;
								<s:if test="#blank[4] != null && #blank[4] !=''">
									<button data-url="${blank[4]}" class="btn btn-success-outline showmedia">查看媒体</button>
								</s:if>
							</p>
						</li>
						</s:iterator>
					</s:if>
					<s:else>
						暂无试题.
					</s:else>
				</ul>
				<hr>
			</div>
			<div class="col-sm-8 col-sm-offset-2">
				<h4><strong>三.主观题</strong></h4>
				<hr>
				<ul class="list-group">
					<s:if test="list[2].size() != 0">
						<s:iterator value="list[2]" id="subques" status="s">
						<li class="list-group-item">
							<p>${s.count}.&nbsp;${subques[0]}</p>
							<p>参考答案:${subques[1] }</p>
							<p>解释:${subques[2] }</p>
							<p>分数:${subques[3] }分&nbsp;&nbsp;
								<s:if test="#subques[4] != null &&#subques[4] !='' ">
									<button data-url="${subques[4] }" class="btn btn-success-outline showmedia">查看媒体</button>
								</s:if>
							</p>
						</li>
						</s:iterator>
					</s:if>
					<s:else>
						暂无试题.
					</s:else>
				</ul>
				<hr>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/showmedia.js"></script>
</body>
