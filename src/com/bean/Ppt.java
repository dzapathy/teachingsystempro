package com.bean;

/**
 * Ppt entity. @author MyEclipse Persistence Tools
 */

public class Ppt implements java.io.Serializable {

	// Fields

	private PptId id;
	private Course course;
	private String pname;
	private String purl;
	private short pstatus;

	// Constructors

	/** default constructor */
	public Ppt() {
	}

	/** minimal constructor */
	public Ppt(PptId id, Course course) {
		this.id = id;
		this.course = course;
	}

	/** full constructor */
	public Ppt(PptId id, Course course, String pname, String purl, short pstatus) {
		this.id = id;
		this.course = course;
		this.pname = pname;
		this.purl = purl;
		this.pstatus = pstatus;
	}

	// Property accessors

	public PptId getId() {
		return this.id;
	}

	public void setId(PptId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPurl() {
		return this.purl;
	}

	public void setPurl(String purl) {
		this.purl = purl;
	}

	public short getPstatus() {
		return this.pstatus;
	}

	public void setPstatus(short pstatus) {
		this.pstatus = pstatus;
	}

}