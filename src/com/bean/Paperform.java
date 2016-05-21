package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Paperform entity. @author MyEclipse Persistence Tools
 */

public class Paperform implements java.io.Serializable {

	// Fields

	private Integer pfid;
	private Course course;
	private String pfname;
	private String iid;
	private short pftype;
	private Date pfcreatetime;
	private short pfstatus;
	private Set grades = new HashSet(0);
	private Set paperdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Paperform() {
	}

	/** minimal constructor */
	public Paperform(Course course, String pfname) {
		this.course = course;
		this.pfname = pfname;
	}

	/** full constructor */
	public Paperform(Course course, String pfname, String iid, short pftype,
			Date pfcreatetime, short pfstatus, Set grades, Set paperdetails) {
		this.course = course;
		this.pfname = pfname;
		this.iid = iid;
		this.pftype = pftype;
		this.pfcreatetime = pfcreatetime;
		this.pfstatus = pfstatus;
		this.grades = grades;
		this.paperdetails = paperdetails;
	}

	// Property accessors

	public Integer getPfid() {
		return this.pfid;
	}

	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getPfname() {
		return this.pfname;
	}

	public void setPfname(String pfname) {
		this.pfname = pfname;
	}

	public String getIid() {
		return this.iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public short getPftype() {
		return this.pftype;
	}

	public void setPftype(short pftype) {
		this.pftype = pftype;
	}

	public Date getPfcreatetime() {
		return this.pfcreatetime;
	}

	public void setPfcreatetime(Date pfcreatetime) {
		this.pfcreatetime = pfcreatetime;
	}

	public short getPfstatus() {
		return this.pfstatus;
	}

	public void setPfstatus(short pfstatus) {
		this.pfstatus = pfstatus;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public Set getPaperdetails() {
		return this.paperdetails;
	}

	public void setPaperdetails(Set paperdetails) {
		this.paperdetails = paperdetails;
	}

}