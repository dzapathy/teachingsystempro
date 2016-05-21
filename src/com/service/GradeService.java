package com.service;

import java.util.List;

public interface GradeService {
	//获取学生成绩列表
	public List getGradeList(Integer pfid , Integer page);
	//存储学生成绩
	public Integer saveStuGrade(String stid , Integer pfid);
}
