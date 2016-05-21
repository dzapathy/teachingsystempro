package com.serviceImpl;

import java.util.List;

import com.bean.Course;
import com.bean.Grade;
import com.bean.GradeId;
import com.dao.GradeDAO;
import com.dao.PaperdetailDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.GradeService;

public class GradeServiceImpl implements GradeService {
	private GradeDAO gradeDAO;
	private PaperdetailDAO paperdetailDAO;
	
	@Override
	public List getGradeList(Integer pfid ,Integer page) {
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		return gradeDAO.getGradeList(pfid , course.getCid(),page);
	}
	
	@Override
	public Integer saveStuGrade(String stid, Integer pfid) {
		List list =paperdetailDAO.getTotalGrade(stid, pfid);
		Integer score = Integer.parseInt(list.get(0).toString());
		GradeId gradeId = new GradeId(stid, pfid);
		Grade grade = new Grade();
		grade.setId(gradeId);
		grade.setGrade(score);
		gradeDAO.save(grade);
		return score;
	}
	
	public GradeDAO getGradeDAO() {
		return gradeDAO;
	}
	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	public PaperdetailDAO getPaperdetailDAO() {
		return paperdetailDAO;
	}

	public void setPaperdetailDAO(PaperdetailDAO paperdetailDAO) {
		this.paperdetailDAO = paperdetailDAO;
	}

}
