package com.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.service.PaperService;

public class GetPapersDetailAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7260652742722054639L;
	private PaperService paperService;
	private List list;
	private Integer pfid;
	
	@Override
	public String execute() throws Exception {
		System.out.println("pfid:"+pfid);
		setList(paperService.showPaperDetail(pfid));
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

	public Integer getPfid() {
		return pfid;
	}

	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}

}
