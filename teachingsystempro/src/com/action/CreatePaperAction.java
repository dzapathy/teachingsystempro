package com.action;

import java.util.List;

import com.bean.Course;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PaperService;

public class CreatePaperAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8496024247623834147L;
	private PaperService paperService;
	private List list;
	private Integer cid;
	private Short pftype;
	private Short startChapter;
	private Short endChapter;
	private Integer choiceNum;
	private Integer blankNum;
	private Integer subquesNum;
	
	@Override
	public String execute() throws Exception {
		Course course = (Course)ActionContext.getContext().getSession().get("course");
//		System.out.println("pftype:"+pftype+" startChapter:"+startChapter+" endChapter:"+endChapter
//				+" choiceNum:"+choiceNum +" blankNum:"+blankNum+" subquesNum:"+subquesNum);
		setList(paperService.createPaper(course.getCid(), pftype ,startChapter ,endChapter,
				choiceNum,blankNum,subquesNum));
		return SUCCESS;
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Short getPftype() {
		return pftype;
	}

	public void setPftype(Short pftype) {
		this.pftype = pftype;
	}

	public Short getStartChapter() {
		return startChapter;
	}

	public void setStartChapter(Short startChapter) {
		this.startChapter = startChapter;
	}

	public Short getEndChapter() {
		return endChapter;
	}

	public void setEndChapter(Short endChapter) {
		this.endChapter = endChapter;
	}

	public Integer getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(Integer choiceNum) {
		this.choiceNum = choiceNum;
	}

	public Integer getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
	}

	public Integer getSubquesNum() {
		return subquesNum;
	}

	public void setSubquesNum(Integer subquesNum) {
		this.subquesNum = subquesNum;
	}
}
