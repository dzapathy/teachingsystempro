package com.bean;

/**
 * Coursesystem entity. @author MyEclipse Persistence Tools
 */

public class Coursesystem implements java.io.Serializable {

	// Fields

	private CoursesystemId id;

	// Constructors

	/** default constructor */
	public Coursesystem() {
	}

	/** full constructor */
	public Coursesystem(CoursesystemId id) {
		this.id = id;
	}

	// Property accessors

	public CoursesystemId getId() {
		return this.id;
	}

	public void setId(CoursesystemId id) {
		this.id = id;
	}

}