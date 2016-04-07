package com.bean;

/**
 * Subques entity. @author MyEclipse Persistence Tools
 */

public class Subques implements java.io.Serializable {

	// Fields

	private SubquesId id;
	private Course course;
	private String suquestion;
	private String suanswer;
	private String suexplain;
	private short suscore;
	private String sumediaurl;
	private short sustatus;

	// Constructors

	/** default constructor */
	public Subques() {
	}

	/** minimal constructor */
	public Subques(SubquesId id, Course course, String suquestion,
			String suanswer, String suexplain) {
		this.id = id;
		this.course = course;
		this.suquestion = suquestion;
		this.suanswer = suanswer;
		this.suexplain = suexplain;
	}

	/** full constructor */
	public Subques(SubquesId id, Course course, String suquestion,
			String suanswer, String suexplain, short suscore,
			String sumediaurl, short sustatus) {
		this.id = id;
		this.course = course;
		this.suquestion = suquestion;
		this.suanswer = suanswer;
		this.suexplain = suexplain;
		this.suscore = suscore;
		this.sumediaurl = sumediaurl;
		this.sustatus = sustatus;
	}

	// Property accessors

	public SubquesId getId() {
		return this.id;
	}

	public void setId(SubquesId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSuquestion() {
		return this.suquestion;
	}

	public void setSuquestion(String suquestion) {
		this.suquestion = suquestion;
	}

	public String getSuanswer() {
		return this.suanswer;
	}

	public void setSuanswer(String suanswer) {
		this.suanswer = suanswer;
	}

	public String getSuexplain() {
		return this.suexplain;
	}

	public void setSuexplain(String suexplain) {
		this.suexplain = suexplain;
	}

	public short getSuscore() {
		return this.suscore;
	}

	public void setSuscore(short suscore) {
		this.suscore = suscore;
	}

	public String getSumediaurl() {
		return this.sumediaurl;
	}

	public void setSumediaurl(String sumediaurl) {
		this.sumediaurl = sumediaurl;
	}

	public short getSustatus() {
		return this.sustatus;
	}

	public void setSustatus(short sustatus) {
		this.sustatus = sustatus;
	}

}