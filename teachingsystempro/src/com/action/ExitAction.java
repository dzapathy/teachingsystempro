package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600976075900298393L;
	
	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getSession().remove("USER_ID");
		ActionContext.getContext().getSession().remove("USER_NAME");
		ActionContext.getContext().getSession().remove("USER_URL");
		ActionContext.getContext().getSession().remove("course");
		return SUCCESS;
	}
	
}
