package com.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.service.StudentManageService;
import com.util.ExcelUtil;

public class AddStudentAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5199055294683509511L;
	private StudentManageService studentManageService;
	private List<String> list = new ArrayList<String>();
	private Short seid;
	private File excel;
	@Override
	public String execute() throws Exception {
		ArrayList<String> arrayList = ExcelUtil.getDatas(excel);
		setList(studentManageService.daoStudent(seid, arrayList));
		return SUCCESS;
	}
	
	public StudentManageService getStudentManageService() {
		return studentManageService;
	}
	public void setStudentManageService(StudentManageService studentManageService) {
		this.studentManageService = studentManageService;
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Short getSeid() {
		return seid;
	}
	public void setSeid(Short seid) {
		this.seid = seid;
	}
	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
}
