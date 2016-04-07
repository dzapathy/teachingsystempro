package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.AddCourseIns;
import com.opensymphony.xwork2.ActionSupport;
import com.service.InstructorService;

public class AddCourseInsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6075620696043734442L;
	private InstructorService instructorService;
	private List<AddCourseIns> courseIns;
	private String iid;
	
	@Override
	public String execute() throws Exception {
		for (int i = 0; i < courseIns.size(); i++) {
			instructorService.insertTeaches(courseIns.get(i));
		}
		return SUCCESS;
	}
	
	//¼ì²éÊÇ·ñ´æÔÚinstructor
	public void checkInstructor(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		try {
			response.getWriter().print(instructorService.checkIns(iid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<AddCourseIns> getCourseIns() {
		return courseIns;
	}
	public void setCourseIns(List<AddCourseIns> courseIns) {
		this.courseIns = courseIns;
	}

	public InstructorService getInstructorService() {
		return instructorService;
	}

	public void setInstructorService(InstructorService instructorService) {
		this.instructorService = instructorService;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}
}
