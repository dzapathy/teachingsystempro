<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">	 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">	
	<title>添加任课教师</title>	
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
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<form action="addinstructor" method="post">
					<table class="table" id="tab">
						<thead>
							<tr>
								<th>课程号</th>
								<th>课序号</th>
								<th>任课教师ID</th>
								<th>是否是管理员</th>
								<th>课程开始时间</th>
								<th>课程结束时间</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${cid}<input type="text" name="courseIns[0].cid" hidden="true" value="${cid}"/></td>
								<td>1<input type="text" name="courseIns[0].seid" hidden="true" value="1"/></td>
								<td><input type="text" name="courseIns[0].iid" class="form-control check" required/></td>
								<td>
									<label class="checkbox-inline">
								  		<input type="radio" name="courseIns[0].tcharge" value="1"> 是
									</label>
									<label class="checkbox-inline">
										<input type="radio" name="courseIns[0].tcharge" value="0" checked> 否
									</label>
								</td>
								<td><input type="date" name="courseIns[0].sestart_time" required/></td>
								<td><input type="date" name="courseIns[0].seend_time" required/></td>
							</tr>
						</tbody>
					</table>				
					<button class="btn btn-primary-outline" type="button" id="addins">添加任课教师</button>
					<button class="btn btn-primary-outline" type="submit">确定</button>
				</form>	
			</div>					
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
	<script src="js/addinstructor.js"></script>
</body>
