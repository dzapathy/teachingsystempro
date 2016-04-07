<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
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
		function check(){
		   var suquestion = document.getElementById("suquestion").value;
		   var suanswer = document.getElementById("suanswer").value;
		   var suexplain = document.getElementById("suexplain").value;
		   if(suquestion == "" || suquestion == null ){
		     alert("问题不能为空");
		     return false;
		   }
		   if(suanswer == "" || suanswer == null){
		   	 alert("答案不能为空");
		     return false;
		   }if(suexplain == "" || suexplain == null){
		   	 alert("解释不能为空");
		     return false;
		   }else{
		   return true;
	   }
	}
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
		     	<form action="subques_update" method="post" enctype="multipart/form-data" onsubmit="return check()">
		     		<input type="hidden" name="cid" value="${subques[0] }">
		     		<input type="hidden" name="suchapter" value="${subques[1] }">
		     		<input type="hidden" name="suid" value="${subques[2] }">
			    	<fieldset class="form-group">
					    <label for="suquestion">问题</label>
					    <input type="text" class="form-control" id="suquestion" name="suquestion" value="${subques[3]}" placeholder="问题">
					</fieldset>
					<fieldset class="form-group">
					    <label for="suanswer">答案</label>
					    <input type="text" class="form-control" id="suanswer" name="suanswer" value="${subques[4]}" placeholder="答案">
					</fieldset>
					<fieldset class="form-group">
					    <label for="suexplain">解释</label>
					    <input type="text" class="form-control" id="suexplain" name="suexplain" value="${subques[5]}" placeholder="解释">
					</fieldset>
					<fieldset class="form-group">
					    <label for="suscore">分数</label>
					    <input type="text" class="form-control" id="suscore" name="suscore" value="${subques[6]}" placeholder="分数">
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
			    		<li>问题：&nbsp;&nbsp;<input type="text" id="suquestion" name="suquestion" value="${subques[3]}"/></li>
			    		<li>答案:&nbsp;&nbsp;<input type="text" id="suanswer" name="suanswer" value="${subques[4] }"/></li>
			    		<li>解释:&nbsp;&nbsp;<textarea id="suexplain" id="suexplain" name="suexplain" rows="2" cols="35"> ${subques[5] } </textarea></li>
			    		<li>分数:&nbsp;&nbsp;<input type="text" name="suscore" value="${subques[6] }"/></li>	    		
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
