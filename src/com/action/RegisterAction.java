package com.action;

import com.bean.Instructor;
import com.opensymphony.xwork2.ActionSupport;
import com.service.RegisterService;

public class RegisterAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4750587017910410574L;
	private Instructor instructor;
	private RegisterService registerService;	
	private String tip;
	
	@Override
	public String execute() throws Exception {
//		System.out.println("id:"+instructor.getIid()+ " name:"+instructor.getIname()+" password:"
//	                              +instructor.getIpassword()+" email:"+instructor.getIemail());
		instructor.setIurl("http://7xsq2n.com2.z0.glb.clouddn.com/morentouxiang.png");//设置默认头像
		if(registerService.register(instructor)){
			return SUCCESS;
		}
		tip = "注册失败,职工号或邮箱已被注册";
		return ERROR;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public RegisterService getRegisterService() {
		return registerService;
	}
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
