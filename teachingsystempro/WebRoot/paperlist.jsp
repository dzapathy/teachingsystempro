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
	<title>试卷列表</title>
	<link href="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" rel="stylesheet">
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
		<div class="col-sm-5 col-sm-offset-1">
			<ol class="breadcrumb">
			  <li><a href="courseDetail?cid=${course.cid}">${course.cname }</a></li>
			  <li class="active">考试系统</li>
			</ol>
		</div>	
		<div class="col-sm-2 col-sm-offset-3">
			<a href="papersetting.jsp" class="btn btn-primary-outline" >抽取试题</a>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>试卷名称</th>
							<th>试卷类型</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" id="paperform" status="pf">
							<tr>
								<td><s:property value="#pf.count+(page-1)*pageSize"/> </td>
								<td><a href="getpaperdetail?pfid=${paperform[0]}" data-pfid="${paperform[0]}">${paperform[1]}</a></td>
								<td>
									<s:if test="#paperform[4] == 0">
										班级小测
									</s:if>
									<s:else>
										统一考试
									</s:else>
								</td>
								<td>${paperform[2] }</td>
								<td>
									<s:if test="#paperform[3] == 0">
										<button class="btn btn-success-outline btn-block isIssued">发布</button>
									</s:if>
									<s:else>
										<a href="showgrade?pfid=${paperform[0]}" class="btn btn-primary-outline btn-block">查看成绩</a>
									</s:else>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<!-- 分页 -->
				<nav>
				  <ul class="pager">
				  	<!-- 上一页 -->
				  	<s:if test="page !=1">
				  		<li><a href="getpapers?page=${page}&tag=0">上一页</a></li>
				  	</s:if>
				  	<s:else>
				  		<li class="disabled"><a href="javascript:void(0)" >上一页</a></li>
				  	</s:else>
				  	<!-- 显示页码/总页数 -->				  	
				  	<s:if test="pageTotal%pageSize==0">
				  		<s:set value="pageTotal/pageSize" name="count"></s:set>	
				  	</s:if>
				  	<s:else>
				  		<s:set value="pageTotal/pageSize+1" name="count"></s:set>
				  	</s:else>    
				    <li>${page}/${count}</li>
				    <li hidden>${pageTotal}</li>
				    <!-- 下一页 -->
				    <s:if test="#count > page">
				    	<li><a href="getpapers?page=${page}&tag=1">下一页</a></li>
				  	</s:if>
				  	<s:else>
				  		<li class="disabled"><a href="javascript:void(0)" >下一页</a></li>
				  	</s:else>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/paperlist.js"></script>
</body>
