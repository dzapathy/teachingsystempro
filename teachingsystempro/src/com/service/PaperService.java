package com.service;

import java.util.List;

import com.bean.BlankId;
import com.bean.ChoiceId;
import com.bean.Paperform;
import com.bean.SubquesId;

public interface PaperService {
	//获取paper
	public List getPapers(Integer cid ,Integer page ,Integer pageSize);
	//获取试卷总数
	public Long getPapersCount(Integer cid);
	//发布试卷
	public void issuePaper(Integer pfid, Short pfstatus);
	//查看试卷
	public List showPaperDetail(Integer pfid);
	//获取主观题在线批阅列表
	public List getStuAns(String stid , Integer pfid);
	//判分
	public void changeScore(Integer pid , Short score);
	//生成试卷
	public List createPaper(Integer cid, Short pftype, Short startChapter ,Short endChapter,
			Integer choiceNum , Integer blankNum ,Integer subquesNum);
	//存储paperform
	public Paperform savePaperForm(String name , Short pftype);
	//存储paperdetail
	public void savePaperDetail(List<ChoiceId> choices , List<BlankId> blanks ,List<SubquesId> subques ,Paperform pf);
}
