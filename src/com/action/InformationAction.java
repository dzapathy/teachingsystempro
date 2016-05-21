package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Instructor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.InformationService;

public class InformationAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2938782997759314498L;
	private Instructor instructor;
	private InformationService informationService;
	private String newpassword;
	
	//修改基本信息
	public void changeBasicInfo() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		String iid = (String) ActionContext.getContext().getSession().get("USER_ID");
		String iname = (String) ActionContext.getContext().getSession().get("USER_NAME");
		instructor.setIid(iid);
		instructor.setIname(iname);
//		System.out.println("changeBasicInfo:");
//		System.out.println("id:"+instructor.getIid()+"\nname:"+instructor.getIname()
//				+"\nemail:"+instructor.getIemail()+"\nphone:"+instructor.getIphone()
//				+"\ninformaiton:"+instructor.getIintroduction());
		try {
			if(informationService.changeBasicInfo(instructor)){
				response.getWriter().print(true);
			}else{
				response.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改密码
	public void changePassInfo() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		String iid = (String) ActionContext.getContext().getSession().get("USER_ID");
//		System.out.println("changePassInfo:");
//		System.out.println("id:"+iid);
//		System.out.println("pass:"+instructor.getIpassword());
//		System.out.println("newpass:"+newpassword);
		try {
			if(informationService.changePassInfo(iid, instructor.getIpassword(), newpassword)){
				response.getWriter().print(true);
			}else{
				response.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改头像
	public void changePicInfo(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
//		System.out.println("changePicInfo:");
//		System.out.println("id:"+instructor.getIid());
//		System.out.println("url:"+instructor.getIurl());
		try {
			if(informationService.changePicInfo(instructor.getIid(),instructor.getIurl())){
				response.getWriter().print(true);
			}else{
				response.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取用户信息
	public String getInfo(){
		String iid = (String) ActionContext.getContext().getSession().get("USER_ID");
		instructor = informationService.getInfo(iid);
		return SUCCESS;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public InformationService getInformationService() {
		return informationService;
	}

	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
}
