package com.bean;

/**
 * Paperdetail entity. @author MyEclipse Persistence Tools
 */

public class Paperdetail implements java.io.Serializable {

	// Fields

	private Integer pid;
	private StudentBasic studentBasic;
	private Paperform paperform;
	private Integer cid;
	private short chapter;
	private Integer qid;
	private short questiontype;
	private short number;
	private String stanswer;
	private short pscore;
	private short pchecked;
	private short pstatus;

	// Constructors

	/** default constructor */
	public Paperdetail() {
	}

	/** minimal constructor */
	public Paperdetail(Paperform paperform, Integer cid, short chapter,
			Integer qid, short questiontype) {
		this.paperform = paperform;
		this.cid = cid;
		this.chapter = chapter;
		this.qid = qid;
		this.questiontype = questiontype;
	}

	/** full constructor */
	public Paperdetail(StudentBasic studentBasic, Paperform paperform,
			Integer cid, short chapter, Integer qid, short questiontype,
			short number, String stanswer, short pscore, short pchecked,
			short pstatus) {
		this.studentBasic = studentBasic;
		this.paperform = paperform;
		this.cid = cid;
		this.chapter = chapter;
		this.qid = qid;
		this.questiontype = questiontype;
		this.number = number;
		this.stanswer = stanswer;
		this.pscore = pscore;
		this.pchecked = pchecked;
		this.pstatus = pstatus;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public StudentBasic getStudentBasic() {
		return this.studentBasic;
	}

	public void setStudentBasic(StudentBasic studentBasic) {
		this.studentBasic = studentBasic;
	}

	public Paperform getPaperform() {
		return this.paperform;
	}

	public void setPaperform(Paperform paperform) {
		this.paperform = paperform;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getChapter() {
		return this.chapter;
	}

	public void setChapter(short chapter) {
		this.chapter = chapter;
	}

	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public short getQuestiontype() {
		return this.questiontype;
	}

	public void setQuestiontype(short questiontype) {
		this.questiontype = questiontype;
	}

	public short getNumber() {
		return this.number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public String getStanswer() {
		return this.stanswer;
	}

	public void setStanswer(String stanswer) {
		this.stanswer = stanswer;
	}

	public short getPscore() {
		return this.pscore;
	}

	public void setPscore(short pscore) {
		this.pscore = pscore;
	}

	public short getPchecked() {
		return this.pchecked;
	}

	public void setPchecked(short pchecked) {
		this.pchecked = pchecked;
	}

	public short getPstatus() {
		return this.pstatus;
	}

	public void setPstatus(short pstatus) {
		this.pstatus = pstatus;
	}

}