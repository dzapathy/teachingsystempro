package com.bean;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private GradeId id;
	private StudentBasic studentBasic;
	private Paperform paperform;
	private Integer grade;

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** minimal constructor */
	public Grade(GradeId id, StudentBasic studentBasic, Paperform paperform) {
		this.id = id;
		this.studentBasic = studentBasic;
		this.paperform = paperform;
	}

	/** full constructor */
	public Grade(GradeId id, StudentBasic studentBasic, Paperform paperform,
			Integer grade) {
		this.id = id;
		this.studentBasic = studentBasic;
		this.paperform = paperform;
		this.grade = grade;
	}

	// Property accessors

	public GradeId getId() {
		return this.id;
	}

	public void setId(GradeId id) {
		this.id = id;
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

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}