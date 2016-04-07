package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Course;
import com.bean.Ppt;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PptService;

public class PptAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6553952655508586850L;
	private PptService pptService;
	private Ppt ppt;
	private List list;
	
	//获取ppt
	@Override
	public String execute() throws Exception {
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		if(course!=null){
			list = pptService.getPpts(course.getCid());
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//更新ppt信息
	public void updatePpt(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		ppt.getId().setCid(course.getCid());
		pptService.updatePpt(ppt);
		try {
			response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除ppt
	public void deletePpt(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		ppt.getId().setCid(course.getCid());
		pptService.deletePpt(ppt);
		try {
			response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public PptService getPptService() {
		return pptService;
	}
	public void setPptService(PptService pptService) {
		this.pptService = pptService;
	}
	public Ppt getPpt() {
		return ppt;
	}
	public void setPpt(Ppt ppt) {
		this.ppt = ppt;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

}
