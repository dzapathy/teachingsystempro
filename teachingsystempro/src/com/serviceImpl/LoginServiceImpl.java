package com.serviceImpl;

import java.util.List;

import com.bean.Instructor;
import com.dao.InstructorDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private InstructorDAO instructorDAO;
	
	@Override
	public boolean loginById(Instructor instructor) {
		List ins = instructorDAO.loginById(instructor);
		if(ins.size()!=0){
			if(ins.get(0) instanceof Object[]){
				Object[] objects = (Object[])ins.get(0); 
				//System.out.println(objects[0]+" "+objects[1]+" "+objects[2]);			
				ActionContext.getContext().getSession().put("USER_ID", objects[0]);
				ActionContext.getContext().getSession().put("USER_NAME", objects[1]);
				ActionContext.getContext().getSession().put("USER_URL", objects[2]);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean loginByEmail(Instructor instructor) {
		// TODO Auto-generated method stub
		return false;
	}

	public InstructorDAO getInstructorDAO() {
		return instructorDAO;
	}

	public void setInstructorDAO(InstructorDAO instructorDAO) {
		this.instructorDAO = instructorDAO;
	}

}
