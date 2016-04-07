<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>修改</title> 
    <link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet"> 
    <script type="text/javascript">
    	window.onload= function(){
		var msg = document.getElementById("msg");
		if(msg.value.length>0){
			alert(msg.value);
		}
	};	
    </script>
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
  	 	<div class="row">
  	 		<div class="col-sm-8 col-sm-offset-2">
  	 			<input type="hidden" id="msg" value="${actionMessages[0]}">
		    	<form action="blank_update" method="post" enctype="multipart/form-data" >
		    		<input type="hidden" name="cid" value="${blank[0] }">
		    		<input type="hidden" name="bchapter" value="${blank[1] }">
		    		<input type="hidden" name="bid" value="${blank[2] }">
			    	<fieldset class="form-group">
					    <label for="bquestion">问题</label>
					    <input type="text" class="form-control" id="bquestion" name="bquestion" value="${blank[3]}" placeholder="问题">
					</fieldset>
			    	<fieldset class="form-group">
					    <label for="banswer">答案</label>
					    <input type="text" class="form-control" id="banswer" name="banswer" value="${blank[4] }" placeholder="答案">
					</fieldset>
					<fieldset class="form-group">
					    <label for="bexplain">解释</label>
					    <input type="text" class="form-control" id="bexplain" name="bexplain" value="${blank[5] }" placeholder="解释">
					</fieldset>
			    	<fieldset class="form-group">
					    <label for="bexplain">分数</label>
					    <input type="text" class="form-control" id="bscore" name="bscore" value="${blank[6] }" placeholder="分数">
					</fieldset>
					<fieldset class="form-group">
					    <label for="mediaType">选择媒体类型</label>
					    <select class="form-control" id="mediaType" name="mediaType">
					      	<option value="photo" selected="selected">图片</option>
	   						<option value="music">音频</option>
	   						<option value="vidio">视频</option>
					    </select>
					 </fieldset>
					 <fieldset class="form-group">
					    <label for="upload">上传</label>
					    <input type="file" name="upload" class="form-control" id="upload" placeholder="上传">
					  </fieldset>
					  <button type="submit" class="btn btn-success">保存修改</button>
			    	<!--  
			    	<ul>
			    		<li>问题：&nbsp;&nbsp;<input type="text" id="bquestion" name="bquestion" value="${blank[3]}"/></li>
			    		<li>答案:&nbsp;&nbsp;<input type="text" id="banswer" name="banswer" value="${blank[4] }"/></li>
			    		<li>解释:&nbsp;&nbsp;<textarea id="bexplain" name="bexplain" rows="2" cols="35"> ${blank[5] } </textarea></li>
			    		<li>分数:&nbsp;&nbsp;<input type="text" name="bscore" value="${blank[6] }"/></li>			    		
			    		<li>
			    			选择媒体类型<select name="mediaType">
			    						<option value="photo" selected="selected">图片</option>
			    						<option value="music">音频</option>
			    						<option value="vidio">视频</option>
			    					</select>
			    		</li>
			    		<li><input type="file" name="upload" /></li>
						<li>
							<input type="submit" value="提交"/>
						</li>
			    	</ul>
			    	-->
			 	</form>
  	 		</div>
  	 	</div>
  	 </div>
 	 	
    <script src="http://ajax.useso.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
  </body>
</html>
