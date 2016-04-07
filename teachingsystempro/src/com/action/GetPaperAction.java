package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Course;
import com.bean.Paperform;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PaperService;

public class GetPaperAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3461089413673411784L;
	private PaperService paperService;
	private Paperform  paperform;
	private List list;
	private Integer page;//当前页数
	private Long pageTotal;//总页数
	private Integer tag; //0--上一页,1--下一页
	private Integer pageSize = 10;
	
	@Override
	public String execute() throws Exception {
		if(page==null){
			page = 1;
		}else{
			if(tag == 0){
				page--;
			}else{
				page++;
			}
		}		
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		if(course!=null){
			Integer cid = course.getCid();
			setList(paperService.getPapers(cid, page, pageSize));
			setPageTotal(paperService.getPapersCount(cid));
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//公布试题
	public void issuePaper(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		paperService.issuePaper(paperform.getPfid()	, paperform.getPfstatus());
		try {
			response.getWriter().print("true");
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PaperService getPaperService() {
		return paperService;
	}
	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	public Paperform getPaperform() {
		return paperform;
	}

	public void setPaperform(Paperform paperform) {
		this.paperform = paperform;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
