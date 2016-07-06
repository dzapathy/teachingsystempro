package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.bean.CoursePre;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CourseSevice;

public class GetCourseSystemAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3882409024816028927L;
	private CourseSevice courseSevice;
	private Integer cid;
	
	
	public void getCourseSystem() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<CoursePre> list = courseSevice.getCourseSystem(cid);	
		JSONArray array = new JSONArray();
		for(CoursePre c : list){
			JSONObject obj = new JSONObject();
			obj.accumulate("id", c.getId());
			obj.accumulate("name", c.getName());
			obj.accumulate("pid", c.getPid());
			obj.accumulate("pname", c.getPname());
			array.add(obj);
		}
		response.getWriter().print(array);
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
}
