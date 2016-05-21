package com.serviceImpl;

import com.bean.Instructor;
import com.dao.InstructorDAO;
import com.service.RegisterService;

public class RegisterServiceImpl implements RegisterService{
	private InstructorDAO instructorDAO;
	
	@Override
	public boolean register(Instructor instructor) {
		if(instructorDAO.saveRegister(instructor))
			return true;
		return false;
		
	}

	public InstructorDAO getInstructorDAO() {
		return instructorDAO;
	}

	public void setInstructorDAO(InstructorDAO instructorDAO) {
		this.instructorDAO = instructorDAO;
	}

}
