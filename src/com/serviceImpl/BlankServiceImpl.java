package com.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bean.Blank;
import com.bean.BlankId;
import com.bean.Choice;
import com.bean.ChoiceId;
import com.bean.Course;
import com.bean.PageBean;
import com.dao.BlankDAO;
import com.dao.CourseDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.BlankService;

public class BlankServiceImpl implements BlankService {

	private CourseDAO courseDao;
	private BlankDAO blankDao;
	
	public void setBlankDao(BlankDAO blankDao) {
		this.blankDao = blankDao;
	}
	
	public void setCourseDao(CourseDAO courseDao) {
		this.courseDao = courseDao;
	}



	@Override
	public void addBlank(Blank blank, Integer cid, short bchapter) throws Exception {
		
		Course course = courseDao.findById(cid);
		//查询该课程对应章节题的数目为设置bid
		int count = blankDao.findMaxId(cid,bchapter);
	//	System.out.println(count);
		
		blank.setId(new BlankId(cid,bchapter,count+1));
		System.out.println("cid="+cid+"bchapter"+bchapter+"bid="+(count+1));
		blank.setCourse(course);
		System.out.println(blank);
		blankDao.save(blank);
	}

	@Override
	public List findAllBlank(Integer cid, short chapter,PageBean pageBean) {
		List list0 = blankDao.findAllPage(cid,chapter,pageBean.getCurrentPage(),pageBean.getPagesize());
		//查询该课程对应章节题的总数目
		int totalsize = blankDao.findCountForSeri(cid,chapter);
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
	public void deleteById(Integer cid, short bchapter, Integer bid) {
		blankDao.deleteById(cid,bchapter,bid);
	}

	@Override
	public Object[] findById(Integer cid, short bchapter, Integer bid) {
		Object blank = blankDao.findById(new BlankId(cid,bchapter,bid));
		if(blank instanceof Object[]){
			Object[] blank2 = (Object[]) blank;
			System.out.println(Arrays.toString(blank2));
			System.out.println("变了");
			return blank2;
		}else{
			System.out.println("没变");
			return (Object[]) blank;
		}
		
	}

	@Override
	public void updateById(Integer cid, short bchapter, Integer bid, Blank blank) {
		blankDao.update(cid,bchapter,bid,blank);
	}

}
