package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.service.PaperService;

public class MarkAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3103810795535068057L;
	private PaperService paperService;
	private List list;
	private String stid;
	private Integer pfid;
	private Integer pid;
	private Short score;
	
	@Override
	public String execute() throws Exception {
		System.out.println("stid:"+stid + " pfid:"+pfid);
		setList(paperService.getStuAns(stid, pfid));
		return SUCCESS;
	}
	
	public void changeScore(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");		
		paperService.changeScore(pid, score);
		try {
			response.getWriter().print("true");
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PaperService getPaperService() {
		return paperService;
	}
	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public Integer getPfid() {
		return pfid;
	}
	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
