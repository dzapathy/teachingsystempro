package com.bean;

/**
 * Blank entity. @author MyEclipse Persistence Tools
 */

public class Blank implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087571758792610050L;
	private BlankId id;
	private Course course;
	private String bquestion;
	private String banswer;
	private String bexplain;
	private short bscore;
	private String bmediaurl;
	private short bstatus;

	// Constructors

	/** default constructor */
	public Blank() {
	}

	/** minimal constructor */
	public Blank(BlankId id, Course course, String bquestion, String banswer,
			String bexplain) {
		this.id = id;
		this.course = course;
		this.bquestion = bquestion;
		this.banswer = banswer;
		this.bexplain = bexplain;
	}

	/** full constructor */
	public Blank(BlankId id, Course course, String bquestion, String banswer,
			String bexplain, short bscore, String bmediaurl, short bstatus) {
		this.id = id;
		this.course = course;
		this.bquestion = bquestion;
		this.banswer = banswer;
		this.bexplain = bexplain;
		this.bscore = bscore;
		this.bmediaurl = bmediaurl;
		this.bstatus = bstatus;
	}

	// Property accessors

	public BlankId getId() {
		return this.id;
	}

	public void setId(BlankId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getBquestion() {
		return this.bquestion;
	}

	public void setBquestion(String bquestion) {
		this.bquestion = bquestion;
	}

	public String getBanswer() {
		return this.banswer;
	}

	public void setBanswer(String banswer) {
		this.banswer = banswer;
	}

	public String getBexplain() {
		return this.bexplain;
	}

	public void setBexplain(String bexplain) {
		this.bexplain = bexplain;
	}

	public short getBscore() {
		return this.bscore;
	}

	public void setBscore(short bscore) {
		this.bscore = bscore;
	}

	public String getBmediaurl() {
		return this.bmediaurl;
	}

	public void setBmediaurl(String bmediaurl) {
		this.bmediaurl = bmediaurl;
	}

	public short getBstatus() {
		return this.bstatus;
	}

	public void setBstatus(short bstatus) {
		this.bstatus = bstatus;
	}

}