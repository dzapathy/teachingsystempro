package com.service;

import com.bean.Instructor;

public interface LoginService {
	public boolean loginById(Instructor instructor);
	public boolean loginByEmail(Instructor instructor);
}
