package com.bean;

/**
 * Teaches entity. @author MyEclipse Persistence Tools
 */

public class Teaches implements java.io.Serializable {

	// Fields

	private TeachesId id;
	private Section section;
	private Instructor instructor;
	private short tcharge;

	// Constructors

	/** default constructor */
	public Teaches() {
	}

	/** minimal constructor */
	public Teaches(TeachesId id, Section section, Instructor instructor) {
		this.id = id;
		this.section = section;
		this.instructor = instructor;
	}

	/** full constructor */
	public Teaches(TeachesId id, Section section, Instructor instructor,
			short tcharge) {
		this.id = id;
		this.section = section;
		this.instructor = instructor;
		this.tcharge = tcharge;
	}

	// Property accessors

	public TeachesId getId() {
		return this.id;
	}

	public void setId(TeachesId id) {
		this.id = id;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public short getTcharge() {
		return this.tcharge;
	}

	public void setTcharge(short tcharge) {
		this.tcharge = tcharge;
	}

}