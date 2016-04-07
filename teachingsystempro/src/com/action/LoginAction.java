package com.action;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.Instructor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CourseSevice;
import com.service.LoginService;

public class LoginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4680722795788633387L;
	private Instructor instructor;
	private LoginService loginService;
	private CourseSevice courseSevice;
	private List<Course> courses;
	private String tip;
	
	@Override
	public String execute() throws Exception {
//		System.out.println("userId:" + instructor.getIid()+" password:"+instructor.getIpassword());		
		if(loginService.loginById(instructor)){
			courses = courseSevice.getCourses(instructor.getIid());
			return SUCCESS;
		}else{
			tip = "登录失败,用户名或密码错误";
			return ERROR;
		}
	}
	
	//课程中心
	public String getCourseCenter(){
		String iid= (String) ActionContext.getContext().getSession().get("USER_ID");
		courses = courseSevice.getCourses(iid);
		return SUCCESS;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public CourseSevice getCourseSevice() {
		return courseSevice;
	}
	public void setCourseSevice(CourseSevice courseSevice) {
		this.courseSevice = courseSevice;
	}
}
