package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Course;
import com.bean.TakesId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StudentManageService;

public class StudentManageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2111691673733300760L;
	private StudentManageService studentManageService;
	private TakesId takesId;
	private List list;
	private Integer page;//当前页数
	private Integer pageTotal;//总页数
	private Integer tag; //0--上一页,1--下一页
	private Integer pageSize = 10;
	
	//查看学生列表
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
		
		Course course =(Course) ActionContext.getContext().getSession().get("course");
		if(course!=null){
			@SuppressWarnings("unchecked")
			List<Short> seid = (List<Short>)ActionContext.getContext().getSession().get("seid");
			setList(studentManageService.getStudentMesssage(course.getCid(),seid,page,pageSize));
			if(pageTotal ==null){
				pageTotal = studentManageService.getStudentTotal(course.getCid(),seid);
			}
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public void removeStudent(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");		
		Course course =(Course) ActionContext.getContext().getSession().get("course");
		takesId.setCid(course.getCid());
		studentManageService.deleteTakes(takesId);
		try {
			response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public StudentManageService getStudentManageService() {
		return studentManageService;
	}
	
	public void setStudentManageService(StudentManageService studentManageService) {
		this.studentManageService = studentManageService;
	}

	public TakesId getTakesId() {
		return takesId;
	}

	public void setTakesId(TakesId takesId) {
		this.takesId = takesId;
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

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
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
