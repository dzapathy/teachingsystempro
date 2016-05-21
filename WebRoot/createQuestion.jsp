<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html lang="en">
  <head>
   <title>createQuestion</title> 
   <link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.css" rel="stylesheet">
   <link href="css/createquestion.css" rel="stylesheet">
   <link href="css/header.css" rel="stylesheet">
</head>
<body>
	<%
		Course c  =(Course) session.getAttribute("course");
		int chapter = c.getCchapter();
	 %>
	<input type="hidden" id="msg" value="${actionMessages[0]}">
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
	<div id="leftnav">
	  <div>
	    <button id="btn1" type="button" class="btn btn-success">添加选择题</button>
	    <button id="btn2" type="button" class="btn btn-success">添加填空题</button>
	    <button id="btn3" type="button" class="btn btn-success">添加主观题</button>
	    <button type="button" onclick="location.href='watchQuestion.jsp';" class="btn btn-success">查看习题</button>
	  </div>
	</div>	
	<div id="content">
	  <div id="con1">
	    <form id="form1"action="${pageContext.request.contextPath }/choice_add" method="post" enctype="multipart/form-data">
	    <fieldset class="form-group">
	      <label for="question">问题</label>
	      <input type="text" class="form-control" name="chquestion" id="question" placeholder="问题" required/>
	    </fieldset>
	      <input type="button" class="btn-success" id="tj" value="添加选项" />
	      <input type="text" class="form-control" name="chchoices" value="A:" required/>
	    <fieldset id="cho" class="form-group">    
	     <!-- -->
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="answer">答案</label>
	      <input type="text" class="form-control" name="chanswer" id="answer" placeholder="答案" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="chapter">章节</label>
	      <select id="chapter" name="chchapter">
	      	<%for ( int i = 1 ; i < chapter+1 ; i++) {%>
	      		<option value="<%=i%>">第<%=i%>章</option>
	      	<%} %>
	      </select>
	    </fieldset>
	     <fieldset class="form-group">
	      <label for="score">分数</label>
	      <input type="text" class="form-control" name="chscore" id="score" placeholder="分数" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="explain">解释</label>
	      <textarea class="form-control" id="explain" name="chexplain" placeholder="解释"></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="media">媒体信息</label>
	      <select class="media" name="mediaType">
	        <option value="music">音频</option>
	        <option value="photo">图片</option>
	        <option value="vidio">视频</option>
	      </select>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="mediaSourse">媒体信息</label>
	      <input type="file"  name="upload" value="选择文件" />
	    </fieldset>
	
	    <input id="in1" type="submit" class="btn btn-success" value="保存">
	   </form>
	  </div>
	  <div id="con2">
	    <form id="form2" action="${pageContext.request.contextPath }/blank_add" method="post" enctype="multipart/form-data">
	    <fieldset class="form-group">
	      <label for="question">问题</label>
	      <textarea class="form-control" id="question" name="bquestion" placeholder="问题" required></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="answer">答案</label>
	      <input type="text" class="form-control" id="answer" name="banswer" placeholder="答案" required>
	    </fieldset>
	     <fieldset class="form-group">
	      <label for="score">分数</label>
	      <input type="text" class="form-control" name="bscore" id="score" placeholder="分数" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="chapter">章节</label>
	      <select id="chapter" name="bchapter" >
	        <%for ( int i = 1 ; i < chapter+1 ; i++) {%>
	      		<option value="<%=i%>">第<%=i%>章</option>
	      	<%} %>
	      </select>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="explain">解释</label>
	      <textarea class="form-control" id="explain" name="bexplain" placeholder="解释"></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="media">媒体信息</label>
	      <select class="media" name="mediaType">
	        <option value="music">音频</option>
	        <option value="photo">图片</option>
	        <option value="vidio">视频</option>
	      </select>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="mediaSourse">媒体信息</label>
	      <input type="file" name="upload" value="选择文件" />
	    </fieldset>
	    <input id="in2" type="submit" class="btn btn-success" value="保存">
	   </form>
	  </div>
	  <div id="con3">
	    <form id="form3" action="${pageContext.request.contextPath }/subques_add" method="post" enctype="multipart/form-data">
	    <fieldset class="form-group">
	      <label for="question">问题</label>
	      <textarea class="form-control" id="question" name="suquestion" placeholder="问题" required></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="answer">答案</label>
	      <textarea class="form-control" id="answer" name="suanswer" placeholder="答案" required></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="score">分数</label>
	      <input type="text" class="form-control" name="suscore" id="score" placeholder="分数" required>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="chapter">章节</label>
	      <select id="chapter" name="suchapter">
	        <%for ( int i = 1 ; i < chapter+1 ; i++) {%>
	      		<option value="<%=i%>">第<%=i%>章</option>
	      	<%} %>
	      </select>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="explain">解释</label>
	      <textarea class="form-control" id="explain" name="suexplain" placeholder="解释"></textarea>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="media">媒体信息</label>
	      <select id="media" name="mediaType">
	        <option value="photo">图片</option>
	        <option value="vidio">视频</option>
	        <option value="music">音频</option>
	      </select>
	    </fieldset>
	    <fieldset class="form-group">
	      <label for="mediaSourse">媒体信息</label>
	      <input type="file" name="upload" value="选择文件" />
	    </fieldset>
	    <input id="in3" type="submit" class="btn btn-success" value="保存">
	   </form>
	  </div>
	</div>
    <script src="http://ajax.useso.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/createquestion.js"></script>
</body>
</html>
