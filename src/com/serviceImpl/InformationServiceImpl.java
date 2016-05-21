package com.serviceImpl;

import com.bean.Instructor;
import com.dao.InstructorDAO;
import com.service.InformationService;

public class InformationServiceImpl implements InformationService {
	private InstructorDAO instructorDAO;
	

	public InstructorDAO getInstructorDAO() {
		return instructorDAO;
	}

	public void setInstructorDAO(InstructorDAO instructorDAO) {
		this.instructorDAO = instructorDAO;
	}

	@Override
	public boolean changeBasicInfo(Instructor instructor) {
		return instructorDAO.changeBasicInfo(instructor);
	}

	@Override
	public boolean changePassInfo(String iid, String ipassword,
			String newpassword) {
		return instructorDAO.changePassInfo(iid, ipassword, newpassword);
	}

	@Override
	public boolean changePicInfo(String iid, String iurl) {
		return instructorDAO.changePicInfo(iid, iurl);
	}

	@Override
	public Instructor getInfo(String iid) {
		return instructorDAO.findById(iid);
	}
	
}
