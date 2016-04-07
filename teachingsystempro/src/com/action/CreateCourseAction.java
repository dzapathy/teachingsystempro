package com.action;

import java.io.File;

import com.bean.Course;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CourseSevice;
import com.util.FileUploadYun;

public class CreateCourseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8246910563502144665L;
	private CourseSevice courseSevice;
	private Course course;
	private Integer cid;
	
	//上传文件
	private File picUpload;
	private String picUploadContentType;
	private String picUploadFileName;
		
	@Override
	public String execute() throws Exception {
		String curl = FileUploadYun.uploadPicture(picUpload,picUploadFileName);
		course.setCurl(curl);
		cid = courseSevice.addCourse(course);
		return SUCCESS;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public File getPicUpload() {
		return picUpload;
	}
	public void setPicUpload(File picUpload) {
		this.picUpload = picUpload;
	}
	public String getPicUploadContentType() {
		return picUploadContentType;
	}
	public void setPicUploadContentType(String picUploadContentType) {
		this.picUploadContentType = picUploadContentType;
	}
	public String getPicUploadFileName() {
		return picUploadFileName;
	}
	public void setPicUploadFileName(String picUploadFileName) {
		this.picUploadFileName = picUploadFileName;
	}
}
