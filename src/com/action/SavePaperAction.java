package com.action;

import java.util.List;

import com.bean.BlankId;
import com.bean.ChoiceId;
import com.bean.Paperform;
import com.bean.SubquesId;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PaperService;

public class SavePaperAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752333156252192593L;
	private PaperService paperService;
	private List<ChoiceId> choices;
	private List<BlankId> blanks;
	private List<SubquesId> subques;
	private String name;
	private Short pftype;
	
	@Override
	public String execute() throws Exception {
		Paperform pf = paperService.savePaperForm(name ,pftype);
		paperService.savePaperDetail(choices, blanks, subques, pf);
		return SUCCESS;
	}
	
	public PaperService getPaperService() {
		return paperService;
	}
	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ChoiceId> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceId> choices) {
		this.choices = choices;
	}

	public List<BlankId> getBlanks() {
		return blanks;
	}

	public void setBlanks(List<BlankId> blanks) {
		this.blanks = blanks;
	}

	public List<SubquesId> getSubques() {
		return subques;
	}

	public void setSubques(List<SubquesId> subques) {
		this.subques = subques;
	}

	public Short getPftype() {
		return pftype;
	}

	public void setPftype(Short pftype) {
		this.pftype = pftype;
	}
}
