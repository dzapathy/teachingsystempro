<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
  <head> 
    <title>查看习题</title> 
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
						选择章节
						<select name="chapter" id="chapter">
							<%for ( int i = 1 ; i < chapter+1 ; i++) {%>
					      		<option value="<%=i%>">第<%=i%>章</option>
					      	<%} %>
						</select>
						<div class="btn-group">			
							<button onclick="toChoiceAction()" class="btn btn-success">查看选择题</button>
							<button onclick="toBlankAction()" class="btn btn-success-outline">查看填空题</button>
							<button onclick="toSubquesAction()" class="btn btn-success-outline">查看主观题</button>
						</div>
					</div>
				</form>
			 	 <div>
				    <s:iterator value="%{list}" var="item">
				    	<ul style="list-style: none;">
				    		<li><input type="hidden" id="cid" value="<s:property value='#item[0]'/>" /></li>
				    		<li><input type="hidden" id="chchapter" value= "<s:property value='#item[1]'/>" /></li>
				    		<li><input type="hidden" id="chid" value= "<s:property value='#item[2]'/>" /></li>
				    		<li>问题：&nbsp;&nbsp;<s:property value="#item[3]"/></li>
				    		<li>选项:&nbsp;&nbsp;<s:property value="#item[4]"/></li>
				    		<li>答案:&nbsp;&nbsp;<s:property value="#item[5]"/></li>
				    		<li>解释:&nbsp;&nbsp;<s:property value="#item[6]"/></li>
				    		<li>分数:&nbsp;&nbsp;<s:property value="#item[7]"/></li>
				    		<li>
				    		    <a href="${pageContext.request.contextPath }/play_media?media=<s:property value='#item[8]'/> ">播放媒体</a>  </li>       
							<li>
								<a href="${pageContext.request.contextPath }/choice_findById
								?cid=<s:property value='#item[0]'/>
								&chchapter=<s:property value='#item[1]'/>
								&chid=<s:property value='#item[2]'/>">修改</a>
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;								
								<button class="del" >删除</button>
							</li>
				    	</ul>
				    </s:iterator>  
					<a href="${pageContext.request.contextPath }/choice_list?
					currentPage=${pageBean.prePage}&
					chapter=${list[0][1] } ">上一页</a>&nbsp;&nbsp;
					<s:iterator value="%{pageBean.pagebar}" var="i">
				 		<a href="${pageContext.request.contextPath }/choice_list?
				 		currentPage=<s:property value='#i'/>&
				 		chapter=${list[0][1] }"> <s:property value="#i"/> </a>&nbsp;&nbsp;
					 </s:iterator>
					<a href="${pageContext.request.contextPath }/choice_list?
					currentPage=<s:property value='pageBean.nexPage'/>&
					chapter=${list[0][1] }">下一页</a>&nbsp;&nbsp;
				</div>
			</div>
		</div>		
  	</div> 	
	<script src="http://ajax.useso.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/listchoice.js"></script>
  </body>
</html>
