package com.serviceImpl;

import java.util.List;

import com.bean.Ppt;
import com.dao.PptDAO;
import com.service.PptService;

public class PptServiceImpl implements PptService {
	private PptDAO pptDAO;
	
	@Override
	public List<Ppt> getPpts(Integer cid) {
		return pptDAO.findByIdProperty("cid", cid, true);
	}

	@Override
	public void deletePpt(Ppt ppt) {
//		System.out.println("cid:"+ppt.getId().getCid()+" pchapter:"+ppt.getId().getPchapter()
//					+" pid:"+ppt.getId().getPid()+" ");
		pptDAO.delete(ppt);
	}
	
	@Override
	public void savePpt(Ppt ppt) {
		pptDAO.save(ppt);
	}
	
	@Override
	public Integer getPptCount(Integer cid, Short chapter) {
		return pptDAO.getCount(cid, chapter);
	}

	@Override
	public void updatePpt(Ppt ppt) {
		pptDAO.updatePptStatus(ppt);
	}

	public PptDAO getPptDAO() {
		return pptDAO;
	}

	public void setPptDAO(PptDAO pptDAO) {
		this.pptDAO = pptDAO;
	}
}
