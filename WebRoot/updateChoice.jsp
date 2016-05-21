<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
  <head> 
    <title>修改</title> 
    <link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
    <script type="text/javascript">
    	window.onload= function(){
		var msg = document.getElementById("msg");
		if(msg.value.length>0){
			alert(msg.value);
		}
	};
	 function check(){
	   var chquestion = document.getElementById("chquestion").value;
	   var chchoices = document.getElementById("chchoices").value;
	   var chanswer = document.getElementById("chanswer").value;
	   var chexplain = document.getElementById("chexplain").value;
	   if(chquestion == "" || chquestion == null ){
	     alert("问题不能为空");
	     return false;
	   }
	   if(chchoices == "" || chchoices == null){
	    alert("选项不能为空");
	     return false;
	   }if(chanswer == "" || chanswer == null){
	   	 alert("答案不能为空");
	     return false;
	   }if(chexplain == "" || chexplain == null){
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
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<input type="hidden" id="msg" value="${actionMessages[0]}">  	
		    	<form action="choice_update" method="post" enctype="multipart/form-data" onsubmit="return check()">
		    		<input type="hidden" name="cid" value="${choice[0] }">
		    		<input type="hidden" name="chchapter" value="${choice[1] }">
		    		<input type="hidden" name="chid" value="${choice[2] }">
			    	<fieldset class="form-group">
					    <label for="chquestion">问题</label>
					    <input type="text" class="form-control" id="chquestion" name="chquestion" value="${choice[3]}" placeholder="问题">
					</fieldset>
			    	<fieldset class="form-group">
					    <label for="chchoices">选项</label>
					    <input type="text" class="form-control" id="chchoices" name="chchoices" value="${choice[4]}" placeholder="选项">
					</fieldset>
					<fieldset class="form-group">
					    <label for="chchoices">答案</label>
					    <input type="text" class="form-control" id="chanswer" name="chanswer" value="${choice[5]}" placeholder="答案">
					</fieldset>
					<fieldset class="form-group">
					    <label for="chexplain">解释</label>
					    <input type="text" class="form-control" id="chexplain" name="chexplain" value="${choice[6]}" placeholder="解释">
					</fieldset>
					<fieldset class="form-group">
					    <label for="chscore">分数</label>
					    <input type="text" class="form-control" id="chscore" name="chscore" value="${choice[7]}" placeholder="分数">
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
			    		<li>问题：&nbsp;&nbsp;<input type="text" id="chquestion" name="chquestion" value="${choice[3]}"/></li>
			    		<li>选项:&nbsp;&nbsp;<textarea id="chchoices" name="chchoices" rows="2" cols="35"> ${choice[4] } </textarea> </li>
			    		<li>答案:&nbsp;&nbsp;<input type="text" id="chanswer" name="chanswer" value="${choice[5] }"/></li>
			    		<li>解释:&nbsp;&nbsp;<textarea id="chexplain" name="chexplain" rows="2" cols="35"> ${choice[6] } </textarea></li>
			    		<li>分数:&nbsp;&nbsp;<input type="text" name="chscore" value="${choice[7] }"/></li>
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
