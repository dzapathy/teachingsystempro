package com.service;

import java.util.List;

import com.bean.Course;

public interface CourseSevice {
	//获取课程列表
	public List<Course> getCourses(String iid);
	//获取课程详情
	public List getCourseDetail(Integer cid);
	//添加课程
	public Integer addCourse(Course course);
}
