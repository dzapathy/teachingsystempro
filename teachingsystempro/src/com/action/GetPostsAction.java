package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Course;
import com.bean.Posts;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PostService;

public class GetPostsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2890620216610555536L;
	private PostService postService;
	private List list;
	private Integer page;//页数
	private Long pageTotal;//总页数
	private Integer tag; //0--上一页,1--下一页
	private Integer pageSize = 10;
	
	//查看话题列表
	@Override
	public String execute() throws Exception {
		if(page == null){
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
			setList(postService.getPosts(course.getCid(), page, pageSize));
			setPageTotal(postService.getPostsCount(course.getCid()));
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//获取无人回复posts
	public void getNoReplyList() throws JSONException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		pageSize = 5; //默认一次读取5条
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		setList(postService.getNoreplyPosts(course.getCid(), page, pageSize));				
		Long count = postService.getNoReplyCount(course.getCid());
		
		JSONObject json = new JSONObject();
		json.put("count", count);		
		JSONArray postslist = new JSONArray();//转换成json
		for(int i = 0; i < list.size();i++){
			if(list.get(i) instanceof Posts){
				Posts p = (Posts)list.get(i);
				JSONObject object = new JSONObject();
				object.put("pid", p.getPid());
				object.put("ptitle", p.getPtitle());
				object.put("pcontent", p.getPcontent());
				object.put("ptime", p.getPcreatetime());
				postslist.put(object);
			}
		}
		json.put("list", postslist);
		try {
			response.getWriter().print(json.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PostService getPostService() {
		return postService;
	}
	public void setPostService(PostService postService) {
		this.postService = postService;
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

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	
}
