package com.service;

import java.util.List;

import com.bean.Ppt;

public interface PptService {
	//获取PPT资源
	public List<Ppt> getPpts(Integer cid);
	//删除ppt
	public void deletePpt(Ppt ppt);
	//改变ppt状态
	public void updatePpt(Ppt ppt);
	//获取某章某节ppt数
	public Integer getPptCount(Integer cid ,Short chapter);
	//存储ppt
	public void savePpt(Ppt ppt);
}
