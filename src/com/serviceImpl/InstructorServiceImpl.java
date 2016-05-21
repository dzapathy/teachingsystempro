package com.serviceImpl;

import com.bean.AddCourseIns;
import com.bean.Course;
import com.bean.Instructor;
import com.bean.Section;
import com.bean.SectionId;
import com.bean.Teaches;
import com.bean.TeachesId;
import com.dao.CourseDAO;
import com.dao.InstructorDAO;
import com.dao.SectionDAO;
import com.dao.TeachesDAO;
import com.service.InstructorService;

public class InstructorServiceImpl implements InstructorService {
	private InstructorDAO instructorDAO;
	private SectionDAO sectionDAO;
	private TeachesDAO teachesDAO;
	private CourseDAO courseDAO;
	@Override
	public boolean checkIns(String iid) {
		Instructor instructor = instructorDAO.findById(iid);
		if(instructor == null){
			return false;
		}else{
			return true;
		}
	}
	
	//≤Â»ÎΩÃ—ß
	@Override
	public void insertTeaches(AddCourseIns courseIns) {
		Section section = new Section(new SectionId(courseIns.getCid(),courseIns.getSeid()), 
				courseDAO.findById(courseIns.getCid()), courseIns.getSestart_time(), courseIns.getSeend_time());		
		sectionDAO.save(section);
		Teaches teaches = new Teaches(new TeachesId(courseIns.getIid(),courseIns.getCid(),courseIns.getSeid()),
					section, instructorDAO.findById(courseIns.getIid()),courseIns.getTcharge());
		teachesDAO.save(teaches);
	}
	
	public InstructorDAO getInstructorDAO() {
		return instructorDAO;
	}
	public void setInstructorDAO(InstructorDAO instructorDAO) {
		this.instructorDAO = instructorDAO;
	}
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	public TeachesDAO getTeachesDAO() {
		return teachesDAO;
	}
	public void setTeachesDAO(TeachesDAO teachesDAO) {
		this.teachesDAO = teachesDAO;
	}
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
}
