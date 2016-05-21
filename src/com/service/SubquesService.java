package com.service;

import java.util.List;

import com.bean.Blank;
import com.bean.PageBean;
import com.bean.Subques;

public interface SubquesService {

	void addSubques(Subques subques, Integer cid, short suchapter);

	List findAllChoice(Integer cid, Short chapter, PageBean pageBean);

	void deleteById(Integer cid, short suchapter, Integer suid);

	Object[] findById(Integer cid, short suchapter, Integer suid);

	void updateById(Integer cid, short suchapter, Integer suid, Subques subques);

}
