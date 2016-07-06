package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import com.bean.Course;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CourseSevice;

public class ChangeCourseSysAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1492888715359914865L;
	private CourseSevice courseSevice;
	private String coursePre;
	
	//获取前驱课程
	public void getCoursePrev() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Integer cid = ((Course)ActionContext.getContext().getSession().get("course")).getCid();
		List list = courseSevice.getPreCourse(cid);
		JSONArray array = new JSONArray();
		for(Object obj : list){
			if(obj instanceof Object[]){
				Object[] arr = (Object[])obj;
				JSONObject jsonObj = new JSONObject();
				jsonObj.accumulate("pid", arr[0]);
				jsonObj.accumulate("pname", arr[1]);
				array.add(jsonObj);
			}
		}
		response.getWriter().print(array);
	}
	
	//修改前驱课程
	public void changeCoursePrev() throws IOException, JSONException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Integer> list = new ArrayList<Integer>();
		JSONArray array = JSONArray.fromObject(coursePre);
		for(Object obj :array){
			if(obj instanceof JSONObject){
				JSONObject j = (JSONObject)obj;
				list.add(Integer.parseInt(j.get("id").toString()));
			}
		}
		courseSevice.editCourseSystem(list);
		response.getWriter().print("true");
	}
	
	//获取所有课程
	public void getAllCourse() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List list = courseSevice.getAllCourse();
		JSONArray array = new JSONArray();
		for(Object obj : list){
			if(obj instanceof Course){
				Course c = (Course)obj;
				JSONObject jsonObj = new JSONObject();
				jsonObj.accumulate("id",c.getCid() );
				jsonObj.accumulate("name", c.getCname());
				array.add(jsonObj);
			}
		}
		response.getWriter().print(array);
	}
	
	public CourseSevice getCourseSevice() {
		return courseSevice;
	}
	public void setCourseSevice(CourseSevice courseSevice) {
		this.courseSevice = courseSevice;
	}

	public String getCoursePre() {
		return coursePre;
	}

	public void setCoursePre(String coursePre) {
		this.coursePre = coursePre;
	}
}
