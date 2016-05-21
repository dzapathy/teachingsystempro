package com.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.BlankId;
import com.bean.ChoiceId;
import com.bean.Course;
import com.bean.Paperdetail;
import com.bean.Paperform;
import com.bean.SubquesId;
import com.dao.PaperdetailDAO;
import com.dao.PaperformDAO;
import com.opensymphony.xwork2.ActionContext;
import com.service.PaperService;

public class PaperServiceImpl implements PaperService {
	private PaperformDAO paperformDAO;
	private PaperdetailDAO paperdetailDAO;
	@Override
	public List getPapers(Integer cid ,Integer page ,Integer pageSize) {
		String iid = (String)ActionContext.getContext().getSession().get("USER_ID");
		return paperformDAO.getPapers(cid, iid, page, pageSize);
	}

	@Override
	public Long getPapersCount(Integer cid) {
		String iid = (String)ActionContext.getContext().getSession().get("USER_ID");
		return paperformDAO.getPapersCount(cid ,iid);
	}
	
	@Override
	public void issuePaper(Integer pfid, Short pfstatus) {
		Paperform paperform = paperformDAO.findById(pfid);
		paperform.setPfstatus(pfstatus);
		paperformDAO.update(paperform);
	}

	@Override
	public List showPaperDetail(Integer pfid) {
		List<List<?>> list = new ArrayList<List<?>>();
		list.add(paperdetailDAO.getChoices(pfid)); //list[0]存储选择题
		list.add(paperdetailDAO.getBlanks(pfid));//list[1]存储填空题
		list.add(paperdetailDAO.getSubQues(pfid));//list[2]存储主观题
		return list;
	}
	
	@Override
	public List getStuAns(String stid, Integer pfid) {
		return paperdetailDAO.getStuAns(stid, pfid);
	}
	
	@Override
	public Paperform savePaperForm(String name, Short pftype) {
		Course course = (Course)ActionContext.getContext().getSession().get("course");
		String iid = (String)ActionContext.getContext().getSession().get("USER_ID");
		Paperform paperform = new Paperform();
		paperform.setCourse(course);
		paperform.setIid(iid);
		paperform.setPfcreatetime(new Date());
		paperform.setPfname(name);
		paperform.setPftype(pftype);
		return paperformDAO.saveId(paperform);
	}
	
	@Override
	public void changeScore(Integer pid, Short score) {
		paperdetailDAO.chageScore(pid, score);
	}
	
	@Override
	public List<List<?>> createPaper(Integer cid , Short pftype, Short startChapter ,Short endChapter,
			Integer choiceNum , Integer blankNum ,Integer subquesNum) {
		List<List<?>> list = new ArrayList<List<?>>();
		list.add(paperdetailDAO.getCChoQues(cid, pftype,startChapter,endChapter, choiceNum));//存放选择题
		list.add(paperdetailDAO.getCBlaQues(cid, pftype,startChapter,endChapter, blankNum));//存放填空题
		list.add(paperdetailDAO.getCSubQues(cid, pftype,startChapter,endChapter, subquesNum));//存放主观题
		return list;
	}

	public PaperformDAO getPaperformDAO() {
		return paperformDAO;
	}

	public void setPaperformDAO(PaperformDAO paperformDAO) {
		this.paperformDAO = paperformDAO;
	}

	public PaperdetailDAO getPaperdetailDAO() {
		return paperdetailDAO;
	}

	public void setPaperdetailDAO(PaperdetailDAO paperdetailDAO) {
		this.paperdetailDAO = paperdetailDAO;
	}

	@Override
	public void savePaperDetail(List<ChoiceId> choices, List<BlankId> blanks,
			List<SubquesId> subques , Paperform pf) { //批量插入？
		for(int i = 0 ; i < choices.size() ; i++){
			Paperdetail paperdetail = new Paperdetail();
			paperdetail.setPaperform(pf);
			paperdetail.setCid(choices.get(i).getCid());
			paperdetail.setChapter(choices.get(i).getChchapter());
			paperdetail.setQid(choices.get(i).getChid());
			Short s = 1;
			paperdetail.setQuestiontype(s);
			Short ii = Short.parseShort(new String(i+1+""));
			paperdetail.setNumber(ii);
			paperdetailDAO.saveP(paperdetail);
		}
		for(int i = 0 ; i < blanks.size() ; i++){
			Paperdetail paperdetail = new Paperdetail();
			paperdetail.setPaperform(pf);
			paperdetail.setCid(blanks.get(i).getCid());
			paperdetail.setChapter(blanks.get(i).getBchapter());
			paperdetail.setQid(blanks.get(i).getBid());
			Short s = 2;
			paperdetail.setQuestiontype(s);
			Short ii = Short.parseShort(new String(i+1+""));
			paperdetail.setNumber(ii);
			paperdetailDAO.saveP(paperdetail);
		}
		for(int i = 0 ; i < subques.size() ; i++){
			Paperdetail paperdetail = new Paperdetail();
			paperdetail.setPaperform(pf);
			paperdetail.setCid(subques.get(i).getCid());
			paperdetail.setChapter(subques.get(i).getSuchapter());
			paperdetail.setQid(subques.get(i).getSuid());
			Short s = 3;
			paperdetail.setQuestiontype(s);
			Short ii = Short.parseShort(new String(i+1+""));
			paperdetail.setNumber(ii);
			paperdetailDAO.saveP(paperdetail);
		}	
	}
}
