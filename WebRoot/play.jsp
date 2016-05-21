<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">		
	<title>播放</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
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
	      <s:if test="#session.USER_NAME != null">	
	      <a class="nav-item nav-link " href="courseCenter">课程中心</a>
	      </s:if>
	      <a class="nav-item nav-link " href="#">关于我们</a>
	    </nav>    
	  </div>
	  <s:if test="#session.USER_NAME != null">			  			  
	  <nav id="headerright" class="nav navbar-nav navbar-right">	  	
	    <img src="${sessionScope.USER_URL }">  				    
	  	<div class="dropdown ">	  	 	     
	       <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"   aria-expanded="false">${sessionScope.USER_NAME}</a>		  		     	
		   <div class="dropdown-menu" aria-labelledby="dLabel">
		       <a class="dropdown-item" href="getInfo">修改资料</a>
		       <a class="dropdown-item" href="exit">退出</a>
		   </div>
	   </div>
	  </nav>
	  </s:if>
	</header>
			<hr>			
			<div class="col-sm-4 col-sm-offset-2">
				<ol class="breadcrumb">
				  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
				  <li class="active"><%=new String( request.getParameter("name").getBytes("ISO-8859-1"),"utf-8") %></li>
				</ol>
			</div>
			<div class="col-sm-offset-2">
				<div class="btn-group btn-group-sm" role="group" aria-label="...">
					<a class="btn btn-info" target="_blank"
						href="http://connect.qq.com/widget/shareqq/index.html?url=<%=request.getParameter("url") %>&title=课程分享&summary=<%=new String( request.getParameter("name").getBytes("ISO-8859-1"),"utf-8") %>" >QQ好友</a>
					<a class="btn btn-primary" target="_blank"
						href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=<%=request.getParameter("url") %>&title=课程分享&summary=<%=new String( request.getParameter("name").getBytes("ISO-8859-1"),"utf-8") %>">QQ空间</a>
					<a class="btn btn-danger" target="_blank"
						href="http://service.weibo.com/share/share.php?url=<%=request.getParameter("url") %>&title=课程分享">新浪微博</a>
					<button type="button" class="btn btn-success-outline" data-toggle="modal" data-target=".bd-example-modal-sm">微信分享</button>
				</div>
			</div>		
			<div class="col-sm-8 col-sm-offset-2">
				<video  src="<%=request.getParameter("url") %>"
					 controls="controls" autoplay="autoplay" width="100%"></video>
			</div>
		</div>	
	</div>
	<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
       <div class="modal-dialog modal-sm">
         <div class="modal-content">
           <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
               <span class="sr-only">Close</span>
             </button>
             <h4 class="modal-title">微信扫一扫</h4>
           </div>
           <div class="modal-body">
               <center><span id="erweima"></span></center>
           </div>
         </div>
       </div>
     </div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
	<script src="js/html5media.min.js"></script>
	<script src="http://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
    <script>
      $("#erweima").qrcode({
      	render: "table", //table方式
      	width: 150, //宽度
      	height:150, //高度
      	text: window.location +"" //任意内容
      	//text:'http://7xsq2n.com2.z0.glb.clouddn.com/465be40c-a026-471d-a8df-259e5e314bca.mp4'
      });
    </script>
</body>
