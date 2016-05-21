package com.service;

import java.util.List;

import com.bean.Media;

public interface VideoService {
	//获取视频资源
	public List<Media> getVideos(Integer cid);
	//删除视频
	public void deleteVideo(Media media);
	//改变视频状态
	public void updateVideo(Media media);
	//存储Media
	public void saveVideo(Media media);
	//获取某章某节的视频总数
	public Integer getVideoCount(Integer cid ,Short chapter);
}
