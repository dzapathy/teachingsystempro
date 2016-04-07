package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.service.GradeService;

public class GradeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8115009069810002622L;
	private GradeService gradeService;
	private List list;
	private Integer pfid;
	private Integer page;
	private String stid;
	
	@Override
	public String execute() throws Exception {
		if(page == null){
			page = 1;
		}
		setList(gradeService.getGradeList(pfid,page));
		return SUCCESS;
	}
	
	//加载更多
	public void getMoreGrade(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		setList(gradeService.getGradeList(pfid,page));
		try {
			response.getWriter().print("true");
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//存储成绩
	public void saveGrade(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		int grade =  gradeService.saveStuGrade(stid, pfid);
		try {
			response.getWriter().print(grade);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GradeService getGradeService() {
		return gradeService;
	}
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public Integer getPfid() {
		return pfid;
	}

	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getStid() {
		return stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}
	
}
