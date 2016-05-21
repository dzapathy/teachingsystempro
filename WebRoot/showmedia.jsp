<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<!--  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	-->
	<title>播放媒体</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<%
			String url = request.getParameter("url"); 
			String index = url.substring(url.lastIndexOf(".")+1);
			if(index.equals("mp4") || index.equals("rmvb") || index.equals("wmv")){
			%>  
			<video src="<%=url %>" 
				controls="controls" autoplay="autoplay" width="100%"></video>
			<%}else if(index.equals("mp3") || index.equals("wav")) {%>
			<audio src="<%=url%>" autoplay="autoplay" controls="controls" width="100%"></audio>
			<%}else if(index.equals("jpg") || index.equals("png")){ %>
			<img alt="img" src="<%=url%>" width="100%">
			<%}else{ %>
				不支持格式.
			<%} %>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</body>
