package com.service;

import java.util.List;

import com.bean.TakesId;

public interface StudentManageService {
	//导入学生
	public List<String> daoStudent(Short seid , List<String> list);
	//批量删除学生选课
	public void deleteTakes(TakesId takesId);
	//获取学生信息
	public List getStudentMesssage(Integer cid, List<Short> seids,Integer page,Integer pageSize);//使用cid和seid查询
	//获取总数
	public Integer getStudentTotal(Integer cid, List<Short> seids);
}
