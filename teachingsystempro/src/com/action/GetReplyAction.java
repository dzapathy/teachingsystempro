package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Reply;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ReplyService;

public class GetReplyAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7520815114455399227L;
	private ReplyService replyService;
	private List list;
	private Integer pid;
	private String content;
	
	//获取reply列表
	@Override
	public String execute() throws Exception {
		setList(replyService.getReplys(pid));
		return SUCCESS;
	}
	
	//回复
	public void saveReply(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		String iid = (String) ActionContext.getContext().getSession().get("USER_ID");
		replyService.reply(iid, pid, content);
		try {
			response.getWriter().print("true");
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ReplyService getReplyService() {
		return replyService;
	}
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
