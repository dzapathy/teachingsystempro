package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.Course;
import com.bean.Media;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.VideoService;

public class VideoAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8827060572668211901L;
	private VideoService videoService;
	private Media media;
	private List list;
	
	@Override
	public String execute() throws Exception {
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		if(course!=null){
			list = videoService.getVideos(course.getCid());
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//É¾³ý
	public void deleteVideo(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		media.getId().setCid(course.getCid());
		videoService.deleteVideo(media);
		try {
			response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//¸üÐÂ
	public void updateVideo(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		media.getId().setCid(course.getCid());
		videoService.updateVideo(media);
		try {
			response.getWriter().print("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public VideoService getVideoService() {
		return videoService;
	}
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
}
