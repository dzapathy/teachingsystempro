package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.bean.Course;
import com.bean.Media;
import com.bean.Ppt;
import com.bean.TeachesId;
import com.dao.CourseDAO;
import com.dao.MediaDAO;
import com.dao.PptDAO;
import com.dao.TeachesDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.CourseSevice;

public class CourseServiceImpl implements CourseSevice {
	private CourseDAO courseDAO;
	private MediaDAO mediaDAO;
	private PptDAO pptDAO;
	private TeachesDAO teachesDAO;
	
	@Override
	public List<Course> getCourses(String iid) {
		return courseDAO.getCourses(iid);
	}
	
	@Override
	public List getCourseDetail(Integer cid) {
		String iid = (String)ActionContext.getContext().getSession().get("USER_ID");
		List l = teachesDAO.findIid(cid);
		boolean tag = false;
		for (int i = 0; i < l.size(); i++) {
			if(l.get(i).toString().equals(iid)){
				tag =true;
				break;
			}
		}
		if(tag){
			Course course = courseDAO.findById(cid);
			ActionContext.getContext().getSession().put("course", course);
			List<Media> list = mediaDAO.findByIdProperty("cid", cid ,false);
			List<Ppt> ppts = pptDAO.findByIdProperty("cid", cid ,false);
			TeachesId teachesId = new TeachesId();
			teachesId.setIid(iid);
			teachesId.setCid(cid);
			List sec = teachesDAO.findCharge(teachesId);
			List<Short> secs = new ArrayList<Short>(); //¥Ê¥¢øŒ–Ú∫≈
			for (int i = 0; i < sec.size(); i++) {
				if(sec.get(i) instanceof Object[]){
					Object[] objects = (Object[])sec.get(i);
					secs.add((Short)objects[0]);
				}
			}
			ActionContext.getContext().getSession().put("seid", secs);//øŒ–Ú∫≈¥Ê»Îsession
			List<Object> objects = new ArrayList<Object>();
			objects.add(list); //objects[0]¥Ê¥¢ List<Media>
			objects.add(ppts); //objects[1]¥Ê¥¢ List<Ppt>
			objects.add(sec); //objects[2]¥Ê¥¢teaches–≈œ¢
			return objects;
		}else{
			return null;
		}
	}
	
	@Override
	public Integer addCourse(Course course) {	
		return courseDAO.addCourse(course);
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public MediaDAO getMediaDAO() {
		return mediaDAO;
	}

	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	public PptDAO getPptDAO() {
		return pptDAO;
	}

	public void setPptDAO(PptDAO pptDAO) {
		this.pptDAO = pptDAO;
	}

	public TeachesDAO getTeachesDAO() {
		return teachesDAO;
	}

	public void setTeachesDAO(TeachesDAO teachesDAO) {
		this.teachesDAO = teachesDAO;
	}
}
