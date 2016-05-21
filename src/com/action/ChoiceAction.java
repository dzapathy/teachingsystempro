package com.action;

import java.io.File;
import java.util.List;

import com.bean.Choice;
import com.bean.Course;
import com.bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.ChoiceService;
import com.util.FileUploadYun;

public class ChoiceAction extends ActionSupport implements ModelDriven<Choice>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 775391920943315773L;
	private Choice choice = new Choice();
	private ChoiceService choiceService;
	private Course course;
	private short chchapter;
	
	//媒体信息类型
	private String mediaType;
	
	@Override
	public Choice getModel() {
		return choice;
	}
	public void setChoiceService(ChoiceService choiceService) {
		this.choiceService = choiceService;
	}
	public void setChchapter(short chchapter) {
		this.chchapter = chchapter;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String add(){
		try {
			Course course = (Course)ActionContext.getContext().getSession().get("course");			
			String str = FileUploadYun.dealUpload(mediaType, upload, uploadFileName);
			choice.setChmediaurl(str);			
			choiceService.addChoice(choice,course.getCid(),chchapter);
			this.addActionMessage("添加成功");
			return "add_success";			
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("添加失败");
			return "add_input";
		}
	}
		
		
	//列出全部题目
	private Short chapter;
	public void setChapter(Short chapter) {
		this.chapter = chapter;
	}
	//用于分页的当前页码
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String list(){
		PageBean pageBean = new PageBean();
		pageBean.setPagesize(3);
		pageBean.setCurrentPage(currentPage);
		Course course = (Course)ActionContext.getContext().getSession().get("course");		
		List list = choiceService.findAllChoice(course.getCid(),chapter,pageBean); 
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getSession().put("selectedChapter", chapter);
		return "findAll_success";
	}
	
	//删除题目
	private Integer cid;     
	private Integer chid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setChid(Integer chid) {
		this.chid = chid;
	}
	
	public String delete(){
		choiceService.deleteById(cid,chchapter,chid);
		return this.NONE;
	}

	//按id查找
	public String findById(){
		Object[] choice2 = choiceService.findById(cid,chchapter,chid);
		ActionContext.getContext().getValueStack().set("choice", choice2);
		return "findById_success";
	}
	
	//修改
	public String update(){
		try {
			String str = FileUploadYun.dealUpload( mediaType, upload,
					uploadFileName);			
			if(str == null || "".equals(str)){
				Object[] choice2 = choiceService.findById(cid, chchapter, chid);
				choice.setChmediaurl((String) choice2[8]);
			}else{
				choice.setChmediaurl(str);
			}		
			choiceService.updateById(cid, chchapter, chid, choice);
			this.addActionMessage("修改成功");
			return "update_success";
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage("修改失败");
			return "update_input";
		}
	}
	
	// 上传操作
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
