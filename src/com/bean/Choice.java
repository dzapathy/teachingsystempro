package com.bean;

/**
 * Choice entity. @author MyEclipse Persistence Tools
 */

public class Choice implements java.io.Serializable {

	// Fields

	private ChoiceId id;
	private Course course;
	private String chquestion;
	private String chchoices;
	private String chanswer;
	private String chexplain;
	private short chscore;
	private String chmediaurl;
	private short chstatus;

	// Constructors

	/** default constructor */
	public Choice() {
	}

	/** minimal constructor */
	public Choice(ChoiceId id, Course course, String chquestion,
			String chchoices, String chanswer, String chexplain) {
		this.id = id;
		this.course = course;
		this.chquestion = chquestion;
		this.chchoices = chchoices;
		this.chanswer = chanswer;
		this.chexplain = chexplain;
	}

	/** full constructor */
	public Choice(ChoiceId id, Course course, String chquestion,
			String chchoices, String chanswer, String chexplain, short chscore,
			String chmediaurl, short chstatus) {
		this.id = id;
		this.course = course;
		this.chquestion = chquestion;
		this.chchoices = chchoices;
		this.chanswer = chanswer;
		this.chexplain = chexplain;
		this.chscore = chscore;
		this.chmediaurl = chmediaurl;
		this.chstatus = chstatus;
	}

	// Property accessors

	public ChoiceId getId() {
		return this.id;
	}

	public void setId(ChoiceId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getChquestion() {
		return this.chquestion;
	}

	public void setChquestion(String chquestion) {
		this.chquestion = chquestion;
	}

	public String getChchoices() {
		return this.chchoices;
	}

	public void setChchoices(String chchoices) {
		this.chchoices = chchoices;
	}

	public String getChanswer() {
		return this.chanswer;
	}

	public void setChanswer(String chanswer) {
		this.chanswer = chanswer;
	}

	public String getChexplain() {
		return this.chexplain;
	}

	public void setChexplain(String chexplain) {
		this.chexplain = chexplain;
	}

	public short getChscore() {
		return this.chscore;
	}

	public void setChscore(short chscore) {
		this.chscore = chscore;
	}

	public String getChmediaurl() {
		return this.chmediaurl;
	}

	public void setChmediaurl(String chmediaurl) {
		this.chmediaurl = chmediaurl;
	}

	public short getChstatus() {
		return this.chstatus;
	}

	public void setChstatus(short chstatus) {
		this.chstatus = chstatus;
	}

}