package com.bean;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private Integer currentPage = 1;
	private Integer totalsize;//×Ü¼ÇÂ¼Êý
	private Integer pagesize = 3;
	private ArrayList pagebar = new ArrayList();
	private Integer prePage;
	private Integer nexPage;
	
	public Integer getPrePage() {
		if(currentPage == 1) return 1;
		return currentPage - 1;
	}
	
	public Integer getNexPage() {
		if( Math.ceil(totalsize.doubleValue()/pagesize) <= currentPage ){
			return currentPage;
		}else{
			return currentPage +1;
		}
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
		
	}
	public ArrayList getPagebar() {
		double num = Math.ceil((totalsize.doubleValue()/pagesize));
		for(int i = 0; i < num ; i ++) pagebar.add(i, i+1);
		return pagebar;
	}
	public void setPagebar(ArrayList pagebar) {
		this.pagebar = pagebar;
	}
	
	
}
