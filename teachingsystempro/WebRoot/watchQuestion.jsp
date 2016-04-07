<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>查看已创建题目</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%
		Course c  =(Course) session.getAttribute("course");
		int chapter = c.getCchapter();
	 %>
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
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<h4>查看已创建题目</h4>
				<form method="post">
					<div id="up">
						选择章节:
						<select name="chapter" id="chapter">
							<%for ( int i = 1 ; i < chapter+1 ; i++) {%>
					      		<option value="<%=i%>">第<%=i%>章</option>
					      	<%} %>
						</select>			
						<div class="btn-group">
							<button onclick="toChoiceAction()" class="btn btn-success-outline">查看选择题</button>					
							<button onclick="toBlankAction()" class="btn btn-success-outline">查看填空题</button>					
							<button onclick="toSubquesAction()" class="btn btn-success-outline">查看主观题</button>
						</div>				
					</div>
				</form>	
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/watchquestion.js"></script>
</body>
</html>
