package com.serviceImpl;

import java.util.List;

import com.bean.Media;
import com.dao.MediaDAO;
import com.service.VideoService;

public class VideoServiceImpl implements VideoService {
	private MediaDAO mediaDAO;
	
	@Override
	public List<Media> getVideos(Integer cid) {
		return mediaDAO.findByIdProperty("cid", cid, true);
	}

	@Override
	public void deleteVideo(Media media) {
//		System.out.println("cid:"+media.getId().getCid()+" mchapter:"+media.getId().getMchapter()
//		+" mid:"+media.getId().getMid());
		mediaDAO.delete(media);
	}

	@Override
	public void updateVideo(Media media) {
		mediaDAO.updateVideoStatus(media);
	}

	@Override
	public void saveVideo(Media media) {
		mediaDAO.save(media);
	}
	
	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	@Override
	public Integer getVideoCount(Integer cid, Short chapter) {
		return mediaDAO.getCount(cid, chapter);
	}

}
