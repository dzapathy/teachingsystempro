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
	<title>生成试卷</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
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
	      <a class="nav-item nav-link " href="courseCenter">课程中心</a>
	      <a class="nav-item nav-link " href="#">关于我们</a>
	    </nav>    
	  </div>
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
	</header>
	
		<hr>
		<div class="col-sm-5 col-sm-offset-1">
			<ol class="breadcrumb">
			  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
			  <li><a href="getpapers">考试系统</a></li>
			  <li class="active">生成试卷</li>
			</ol>
		</div>
		<div class="col-sm-2 col-sm-offset-3">
			<a href="createpaper?pftype=${pftype}&startChapter=${startChapter}&endChapter=${endChapter}&choiceNum=${choiceNum}&blankNum=${blankNum}&subquesNum=${subquesNum}" 
				type="button" class="btn btn-warning-outline">重新抽取</a>
		</div>
		<form action="savepaper" method="post">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<hr>
				<h4>一.选择题</h4>
				<hr>
				<ul class="list-group">
					<s:iterator value="list[0]" id="choice" status="c">
					<li class="list-group-item">
						<p hidden>
							<input type="text" name="choices[${c.count-1 }].cid" value="${choice[0] }">
							<input type="text" name="choices[${c.count-1 }].chchapter" value="${choice[1] }">
							<input type="text" name="choices[${c.count-1 }].chid" value="${choice[2] }">
						</p>
						<p>${c.count }.${choice[3] }<br>${choice[4]}</p>
						<p>答案:${choice[5] }</p>
						<p>分数:${choice[6] }&nbsp;&nbsp;
							<s:if test="#choice[7] != null && #choice[7] != ''">
								<button data-url="${choice[7] }" class="btn btn-success-outline showmedia">查看媒体</button> 
							</s:if>
						</p>
					</li>	
					</s:iterator>
				</ul>
			</div>
			<div class="col-sm-8 col-sm-offset-2">
				<hr>
				<h4>二.填空题</h4>
				<ul class="list-group">
					<s:iterator value="list[1]" id="blank" status="b">
					<li class="list-group-item">
						<p hidden>
							<input type="text" name="blanks[${b.count-1 }].cid" value="${blank[0] }">
							<input type="text" name="blanks[${b.count-1 }].bchapter" value="${blank[1] }">
							<input type="text" name="blanks[${b.count-1 }].bid" value="${blank[2] }">
						</p>
						<p>${b.count }.${blank[3] }</p>
						<p>答案:${blank[4] }</p>
						<p>分数:${blank[5] }&nbsp;&nbsp;
							<s:if test="#blank[6] != null && #blank[6] !=''">
								<a data-url="${blank[6] }" class="btn btn-success-outline showmedia">查看媒体</a> 
							</s:if>
						</p>
					</li>	
					</s:iterator>
				</ul>
				<hr>
			</div>
			<div class="col-sm-8 col-sm-offset-2">
				<hr>
				<h4>三.主观题</h4>
				<ul class="list-group">
					<s:iterator value="list[2]" id="subques" status="s">
					<li class="list-group-item">
						<p hidden>
							<input type="text" name="subques[${s.count-1 }].cid" value="${subques[0] }">
							<input type="text" name="subques[${s.count-1 }].suchapter" value="${subques[1] }">
							<input type="text" name="subques[${s.count-1 }].suid" value="${subques[2] }">
						</p>
						<p>${s.count }.${subques[3] }</p>
						<p>答案:${subques[4] }</p>
						<p>分数:${subques[5] }&nbsp;&nbsp;
							<s:if test="#subques[6] != null &&#subques[6] !='' ">
								<a data-url="${subques[6] }" class="btn btn-success-outline showmedia">查看媒体</a> 
							</s:if>
						</p>
					</li>	
					</s:iterator>
				</ul>
				<hr>
			</div>
			<div class="col-sm-8 col-sm-offset-2">
				<div class="input-group">
					<input hidden name="pftype" value="${pftype}">
					<input type="text" name="name" class="form-control" placeholder="输入试卷名称..." required>
					<span class="input-group-btn">
						<button type="submit" class="btn btn-success-outline">保存试卷</button>
					</span>
				</div>
				<hr>				
			</div>
		</div>
		</form>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/showmedia.js"></script>
</body>
