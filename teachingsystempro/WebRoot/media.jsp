<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>My JSP 'media.jsp' starting page</title>  
  </head>
  
  <body>
  		<c:if test="${mediaType =='1' }">
	  		<img src="${media }" width="450" height="600" />
		</c:if>
		
	  <c:if test="${mediaType =='2' }">
	  	<audio controls="controls" height="500" width="500" src="${media }" />
	</c:if>
		
	<c:if test="${mediaType =='3' }">
	  	<video width="1000" height="700" controls="controls" autoplay="autoplay">
			  <source src="${media }" type="video/ogg" />
			  <source src="${media }" type="video/mp4" />
			  <source src="${media }" type="video/webm" />
		</video>
	</c:if>
	
	<c:if test="${mediaType =='4' }">
	  	<h2>无媒体文件</h2>
	</c:if>
  </body>
</html>
