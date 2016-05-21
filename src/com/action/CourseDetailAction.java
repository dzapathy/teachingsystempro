package com.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.service.CourseSevice;

public class CourseDetailAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1722906719145003945L;
	private CourseSevice courseSevice;
	private Integer cid;
	private List list;
	@Override
	public String execute() throws Exception {
		list = courseSevice.getCourseDetail(cid);
		return SUCCESS;
	}

	public CourseSevice getCourseSevice() {
		return courseSevice;
	}

	public void setCourseSevice(CourseSevice courseSevice) {
		this.courseSevice = courseSevice;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
