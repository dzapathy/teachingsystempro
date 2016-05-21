package com.bean;

import java.util.Date;

public class AddCourseIns implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7169727108546796841L;
	private Integer cid;
	private Short seid;
	private Date sestart_time;
	private Date seend_time;
	private String iid;
	private Short tcharge;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Short getSeid() {
		return seid;
	}
	public void setSeid(Short seid) {
		this.seid = seid;
	}
	public Date getSestart_time() {
		return sestart_time;
	}
	public void setSestart_time(Date sestart_time) {
		this.sestart_time = sestart_time;
	}
	public Date getSeend_time() {
		return seend_time;
	}
	public void setSeend_time(Date seend_time) {
		this.seend_time = seend_time;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public Short getTcharge() {
		return tcharge;
	}
	public void setTcharge(Short tcharge) {
		this.tcharge = tcharge;
	}
}
