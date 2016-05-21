package com.bean;

/**
 * Media entity. @author MyEclipse Persistence Tools
 */

public class Media implements java.io.Serializable {

	// Fields

	private MediaId id;
	private Course course;
	private String mname;
	private String murl;
	private short mtype;
	private short mstatus;

	// Constructors

	/** default constructor */
	public Media() {
	}

	/** minimal constructor */
	public Media(MediaId id, Course course) {
		this.id = id;
		this.course = course;
	}

	/** full constructor */
	public Media(MediaId id, Course course, String mname, String murl,
			short mtype, short mstatus) {
		this.id = id;
		this.course = course;
		this.mname = mname;
		this.murl = murl;
		this.mtype = mtype;
		this.mstatus = mstatus;
	}

	// Property accessors

	public MediaId getId() {
		return this.id;
	}

	public void setId(MediaId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMurl() {
		return this.murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public short getMtype() {
		return this.mtype;
	}

	public void setMtype(short mtype) {
		this.mtype = mtype;
	}

	public short getMstatus() {
		return this.mstatus;
	}

	public void setMstatus(short mstatus) {
		this.mstatus = mstatus;
	}

}