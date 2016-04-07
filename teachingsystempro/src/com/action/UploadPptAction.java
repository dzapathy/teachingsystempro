package com.action;

import java.io.File;

import com.bean.Course;
import com.bean.Ppt;
import com.bean.PptId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PptService;
import com.util.FileUploadYun;

public class UploadPptAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4096685073127129319L;
	private PptService pptService;
	private Ppt ppt = new Ppt();
	private File   uploadify;//上传文件file对象  
    private String uploadifyFileName;//上传文件名
    private Short chapter;
    
    public void upload(){
    	String murl = FileUploadYun.uploadPpt(uploadify, uploadifyFileName);
    	Course course = (Course)ActionContext.getContext().getSession().get("course");
    	int count = pptService.getPptCount(course.getCid(), chapter);
    	//System.out.println("count:"+count);
    	Short status = 0;
    	//System.out.println("chapter:"+chapter);
    	ppt.setPname(uploadifyFileName);    	
    	ppt.setPstatus(status);
    	ppt.setPstatus(status);
    	ppt.setPurl(murl);
    	ppt.setId(new PptId(course.getCid(),chapter,count+1));
    	pptService.savePpt(ppt);
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
	public File getUploadify() {
		return uploadify;
	}
	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}
	public String getUploadifyFileName() {
		return uploadifyFileName;
	}
	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}
	public Short getChapter() {
		return chapter;
	}
	public void setChapter(Short chapter) {
		this.chapter = chapter;
	}

}
