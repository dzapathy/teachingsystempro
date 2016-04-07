package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Section entity. @author MyEclipse Persistence Tools
 */

public class Section implements java.io.Serializable {

	// Fields

	private SectionId id;
	private Course course;
	private Date sestartTime;
	private Date seendTime;
	private Set takeses = new HashSet(0);
	private Set teacheses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Section() {
	}

	/** minimal constructor */
	public Section(SectionId id, Course course, Date sestartTime, Date seendTime) {
		this.id = id;
		this.course = course;
		this.sestartTime = sestartTime;
		this.seendTime = seendTime;
	}

	/** full constructor */
	public Section(SectionId id, Course course, Date sestartTime,
			Date seendTime, Set takeses, Set teacheses) {
		this.id = id;
		this.course = course;
		this.sestartTime = sestartTime;
		this.seendTime = seendTime;
		this.takeses = takeses;
		this.teacheses = teacheses;
	}

	// Property accessors

	public SectionId getId() {
		return this.id;
	}

	public void setId(SectionId id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getSestartTime() {
		return this.sestartTime;
	}

	public void setSestartTime(Date sestartTime) {
		this.sestartTime = sestartTime;
	}

	public Date getSeendTime() {
		return this.seendTime;
	}

	public void setSeendTime(Date seendTime) {
		this.seendTime = seendTime;
	}

	public Set getTakeses() {
		return this.takeses;
	}

	public void setTakeses(Set takeses) {
		this.takeses = takeses;
	}

	public Set getTeacheses() {
		return this.teacheses;
	}

	public void setTeacheses(Set teacheses) {
		this.teacheses = teacheses;
	}

}