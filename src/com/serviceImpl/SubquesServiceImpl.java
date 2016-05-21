package com.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bean.Blank;
import com.bean.BlankId;
import com.bean.ChoiceId;
import com.bean.Course;
import com.bean.PageBean;
import com.bean.Subques;
import com.bean.SubquesId;
import com.dao.CourseDAO;
import com.dao.SubquesDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.SubquesService;

public class SubquesServiceImpl implements SubquesService{

	private CourseDAO courseDao;
	private SubquesDAO subquesDao;
	
	
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}


	public void setSubquesDao(SubquesDAO subquesDao) {
		this.subquesDao = subquesDao;
	}


	@Override
	public void addSubques(Subques subques, Integer cid, short suchapter) {
		Course course = courseDao.findById(cid);
		//查询该课程对应章节题的数目为设置bid
		int count = subquesDao.findMaxId(cid,suchapter);
		subques.setId(new SubquesId(cid,suchapter,++count));
		subques.setCourse(course);
		subquesDao.save(subques);
		System.out.println(subques);
	}


	@Override
	public List findAllChoice(Integer cid, Short chapter, PageBean pageBean) {
		List list0 = subquesDao.findAllPage(cid,chapter,pageBean.getCurrentPage(),pageBean.getPagesize());
		//查询该课程对应章节题的总数目
		int totalsize = subquesDao.findCountForSeri(cid,chapter);
		List list = new ArrayList();
		for(Object o : list0){
			if(o instanceof Object[]){
				Object[] o2 = (Object[]) o;
				list.add(o2);
				System.out.println(Arrays.toString(o2));
			}
		}
		System.out.println("totalsize = "+ totalsize);
		System.out.println("list.size() = "+ list.size());
		pageBean.setTotalsize(totalsize);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return list;
	}


	@Override
	public void deleteById(Integer cid, short suchapter, Integer suid) {
		subquesDao.deleteById(cid,suchapter,suid);
	}


	@Override
	public Object[] findById(Integer cid, short suchapter, Integer suid) {
		Object subques = subquesDao.findById(new SubquesId(cid,suchapter,suid));
		if(subques instanceof Object[]){
			Object[] subques2 = (Object[]) subques;
			System.out.println(Arrays.toString(subques2));
			System.out.println("变了");
			return subques2;
		}else{
			System.out.println("没变");
			return (Object[]) subques;
		}
	}


	@Override
	public void updateById(Integer cid, short suchapter, Integer suid,
			Subques subques) {
		subquesDao.update(cid,suchapter,suid,subques);
	}

}
