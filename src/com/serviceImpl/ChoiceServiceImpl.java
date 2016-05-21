package com.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bean.Blank;
import com.bean.BlankId;
import com.bean.Choice;
import com.bean.ChoiceId;
import com.bean.Course;
import com.bean.PageBean;
import com.dao.BlankDAO;
import com.dao.ChoiceDAO;
import com.dao.CourseDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.ChoiceService;

public class ChoiceServiceImpl implements ChoiceService{

	private CourseDAO courseDao;
	private ChoiceDAO choiceDao;
	
	public void setChoiceDao(ChoiceDAO choiceDao) {
		this.choiceDao = choiceDao;
	}

	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}

	public void addChoice(Choice choice, Integer cid, short chchapter) {
		Course course = courseDao.findById(cid);
		//查询该课程对应章节题的数目为设置bid
		int maxId = choiceDao.findMaxId(cid,chchapter);
	//	System.out.println(count);
		
		choice.setId(new ChoiceId(cid,chchapter,++maxId));
		choice.setCourse(course);
		
		System.out.println(cid+"_________"+chchapter+"________"+maxId);
		System.out.println(choice);
		choiceDao.save(choice);
		
	}

	@Override
	public List findAllChoice(Integer cid, Short chapter, PageBean pageBean) {
		List list0 = choiceDao.findAllPage(cid,chapter,pageBean.getCurrentPage(),pageBean.getPagesize());
		//查询该课程对应章节题的总数目
		int totalsize = choiceDao.findCountForSeri(cid,chapter);
		List list = new ArrayList();
		for(Object o : list0){
			if(o instanceof Object[]){
				Object[] o2 = (Object[]) o;
				list.add(o2);
				System.out.println(Arrays.toString(o2));
			}else{
				System.out.println(o);
			}
		}
		System.out.println("totalsize = "+ totalsize);
		System.out.println("list.size() = "+ list.size());
		System.out.println(list);
		
		/*ArrayList pagebar = new ArrayList();
		int total = 0;
		int pagesize = pageBean.getPagesize();
		if(totalsize % pagesize == 0){
			total = totalsize / pagesize;
		}else{
			total = totalsize / pagesize + 1;
		}
		for(int i = 0; i < total ; i++){
			pagebar.add(i, i+1);
		}*/
		pageBean.setTotalsize(totalsize);
		/*pageBean.setPagebar(pagebar);
		for(int i = 0; i < pagebar.size() ; i++){
			System.out.println(pagebar.get(i));
		}*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return list;
		
	}

	@Override
	public void deleteById(Integer cid, short chchapter, Integer chid) {
		choiceDao.deleteById(cid,chchapter,chid);
	}

	@Override
	public void updateById(Integer cid, short chchapter, Integer chid, Choice choice) {
		choiceDao.update(cid,chchapter,chid,choice);
	}

	@Override
	public Object[] findById(Integer cid, short chchapter, Integer chid) {
		Object choice = choiceDao.findById(new ChoiceId(cid,chchapter,chid));
		if(choice instanceof Object[]){
			Object[] choice2 = (Object[]) choice;
			System.out.println(Arrays.toString(choice2));
			System.out.println("变了");
			return choice2;
		}else{
			System.out.println("没变");
			return (Object[]) choice;
		}
		
	}

}
