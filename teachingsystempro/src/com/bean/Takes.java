package com.bean;

/**
 * Takes entity. @author MyEclipse Persistence Tools
 */

public class Takes implements java.io.Serializable {

	// Fields

	private TakesId id;
	private Section section;
	private StudentBasic studentBasic;
	private short status;

	// Constructors

	/** default constructor */
	public Takes() {
	}

	/** minimal constructor */
	public Takes(TakesId id, Section section, StudentBasic studentBasic) {
		this.id = id;
		this.section = section;
		this.studentBasic = studentBasic;
	}

	/** full constructor */
	public Takes(TakesId id, Section section, StudentBasic studentBasic,
			short status) {
		this.id = id;
		this.section = section;
		this.studentBasic = studentBasic;
		this.status = status;
	}

	// Property accessors

	public TakesId getId() {
		return this.id;
	}

	public void setId(TakesId id) {
		this.id = id;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public StudentBasic getStudentBasic() {
		return this.studentBasic;
	}

	public void setStudentBasic(StudentBasic studentBasic) {
		this.studentBasic = studentBasic;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

}