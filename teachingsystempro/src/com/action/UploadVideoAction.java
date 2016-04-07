package com.action;

import java.io.File;

import com.bean.Course;
import com.bean.Media;
import com.bean.MediaId;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.VideoService;
import com.util.FileUploadYun;

public class UploadVideoAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5652176983291831441L;
	private VideoService videoService;
	private Media media = new Media();
	private File   uploadify;//上传文件file对象  
    private String uploadifyFileName;//上传文件名
    private Short chapter;
      
    public void upload(){
    	String murl = FileUploadYun.uploadVideo(uploadify, uploadifyFileName);
    	Course course = (Course)ActionContext.getContext().getSession().get("course");
    	int count = videoService.getVideoCount(course.getCid(), chapter);
    	//System.out.println("count:"+count);
    	Short status = 0;
    	//System.out.println("chapter:"+chapter);
    	//media.setCourse(course);
    	media.setMname(uploadifyFileName);    	
    	media.setMstatus(status);
    	media.setMtype(status);
    	media.setMurl(murl);
    	media.setId(new MediaId(course.getCid(),chapter,count+1));
    	videoService.saveVideo(media);
    }  
  
    public File getUploadify() {  
        return uploadify;  
    }  
  
    public void setUploadify(File uploadify) {  
        this.uploadify = uploadify;  
    }  
  
    public String getUploadifyFileName() {  
        return uploadifyFileName;  
    }  
  
    public void setUploadifyFileName(String uploadifyFileName) {  
        this.uploadifyFileName = uploadifyFileName;  
    }

	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Short getChapter() {
		return chapter;
	}

	public void setChapter(Short chapter) {
		this.chapter = chapter;
	}
	
}
