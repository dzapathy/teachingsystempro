<%@page import="com.bean.Course"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head> 
    <title>查看习题</title> 
   	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/header.css" rel="stylesheet">
  </head>
  <body> 
 	<%
		Course c  =(Course) session.getAttribute("course");
		int chapter = c.getCchapter();
	//	session.setAttribute("chap", chapter);
	 %>
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
				<h4>查看已创建题目</h4>
				<form method="post">
					<div id="up">
						选择章节
						<%-- <select name="chapter" id="chapter">
							<%for ( int i = 1 ; i < chapter+1 ; i++) {%>
					      		<option value="<%=i%>">第<%=i%>章</option>
					      	<%} %>
						</select> --%>
						
						<select name="chapter" id="chapter">
							<c:forEach begin="1" end="${course.cchapter }" step="1" var="num">
								<option value="${num }" <c:if test="${num == selectedChapter}">selected</c:if> >第${num }章</option>
							</c:forEach>
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
				    <c:choose>
					   <c:when test="${list != null && list.size() > 0}">
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
						</c:when>
						<c:otherwise>
							<a disabled>上一页 </a>&nbsp;&nbsp;&nbsp;&nbsp;<a disabled>下一页 </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>		
  	</div> 	
	<script src="http://ajax.useso.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/listchoice.js"></script>
  </body>
</html>
