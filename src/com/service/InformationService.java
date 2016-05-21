package com.service;

import com.bean.Instructor;

public interface InformationService {
	//修改基本信息
	public boolean changeBasicInfo(Instructor instructor);
	//修改密码
	public boolean changePassInfo(String iid ,String ipassword , String newpassword);
	//修改头像
	public boolean changePicInfo(String iid , String iurl);
	//获取信息
	public Instructor getInfo(String iid);
}
