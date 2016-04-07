package com.service;

import java.util.List;

import com.bean.Blank;
import com.bean.PageBean;

public interface BlankService {
	void addBlank(Blank blank, Integer cid, short bchapter) throws Exception;

	List findAllBlank(Integer cid, short chapter, PageBean pageBean);

	void deleteById(Integer cid, short bchapter, Integer bid);

	Object[] findById(Integer cid, short bchapter, Integer bid);

	void updateById(Integer cid, short bchapter, Integer bid, Blank blank);



}
