package com.bean;

/**
 * StudentDetail entity. @author MyEclipse Persistence Tools
 */

public class StudentDetail implements java.io.Serializable {

	// Fields

	private StudentDetailId id;
	private StudentBasic studentBasic;

	// Constructors

	/** default constructor */
	public StudentDetail() {
	}

	/** full constructor */
	public StudentDetail(StudentDetailId id, StudentBasic studentBasic) {
		this.id = id;
		this.studentBasic = studentBasic;
	}

	// Property accessors

	public StudentDetailId getId() {
		return this.id;
	}

	public void setId(StudentDetailId id) {
		this.id = id;
	}

	public StudentBasic getStudentBasic() {
		return this.studentBasic;
	}

	public void setStudentBasic(StudentBasic studentBasic) {
		this.studentBasic = studentBasic;
	}

}