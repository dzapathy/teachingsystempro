package com.service;

import java.util.List;

import com.bean.Choice;
import com.bean.PageBean;

public interface ChoiceService {

	void addChoice(Choice choice, Integer cid, short chchapter);

	List findAllChoice(Integer cid, Short chapter, PageBean pageBean);

	void deleteById(Integer cid, short chchapter, Integer chid);

	void updateById(Integer cid, short chchapter, Integer chid, Choice choice);

	Object[] findById(Integer cid, short chchapter, Integer chid);

}
