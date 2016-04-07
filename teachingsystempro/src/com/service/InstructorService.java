package com.service;

import com.bean.AddCourseIns;

public interface InstructorService {
	//检查是否有instructor
	public boolean checkIns(String iid);
	//教师授课
	public void insertTeaches(AddCourseIns courseIns);
}
