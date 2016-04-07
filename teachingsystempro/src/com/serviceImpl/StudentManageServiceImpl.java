package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.StudentBasic;
import com.bean.Takes;
import com.bean.TakesId;
import com.dao.StudentBasicDAO;
import com.dao.TakesDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.StudentManageService;

public class StudentManageServiceImpl implements StudentManageService {
	private TakesDAO takesDAO;
	private StudentBasicDAO studentBasicDAO;
	
	@Override
	public List<String> daoStudent(Short seid , List<String> list) {
		List<String> fails = new ArrayList<String>();
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		for(String str : list){
			StudentBasic stu = studentBasicDAO.findById(str);
			if(stu!=null){
				takesDAO.save(new Takes());
			}else{
				fails.add(str);
			}
		}
		return fails;
	}
	
	//批量删除学生
	@Override
	public void deleteTakes(TakesId takesIds) {
		takesDAO.delete(takesDAO.findById(takesIds));
	}
	
	//获取学生列表
	@Override
	public List getStudentMesssage(Integer cid, List<Short> seids,Integer page,Integer pageSize) {
		String seid ="";
		for (int i = 0; i < seids.size(); i++) {
			if(i!=(seids.size()-1)){
				seid += seids.get(i)+",";
			}else{
				seid += seids.get(i);
			}
		}
		return takesDAO.findStuList(cid, seid, page, pageSize);
	}

	//获取总页数
	@Override
	public Integer getStudentTotal(Integer cid, List<Short> seids) {
		String seid ="";
		for (int i = 0; i < seids.size(); i++) {
			if(i!=(seids.size()-1)){
				seid += seids.get(i)+",";
			}else{
				seid += seids.get(i);
			}
		}
		return takesDAO.getStudentTotal(cid, seid);
	}
	
	public TakesDAO getTakesDAO() {
		return takesDAO;
	}

	public void setTakesDAO(TakesDAO takesDAO) {
		this.takesDAO = takesDAO;
	}

	public StudentBasicDAO getStudentBasicDAO() {
		return studentBasicDAO;
	}

	public void setStudentBasicDAO(StudentBasicDAO studentBasicDAO) {
		this.studentBasicDAO = studentBasicDAO;
	}
}
