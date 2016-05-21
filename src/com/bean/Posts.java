package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Posts entity. @author MyEclipse Persistence Tools
 */

public class Posts implements java.io.Serializable {

	// Fields

	private Integer pid;
	private Course course;
	private StudentBasic studentBasic;
	private String ptitle;
	private String pcontent;
	private Date pcreatetime;
	private Set replies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Posts() {
	}

	/** minimal constructor */
	public Posts(Course course, StudentBasic studentBasic, String ptitle,
			String pcontent) {
		this.course = course;
		this.studentBasic = studentBasic;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
	}

	/** full constructor */
	public Posts(Course course, StudentBasic studentBasic, String ptitle,
			String pcontent, Date pcreatetime, Set replies) {
		this.course = course;
		this.studentBasic = studentBasic;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pcreatetime = pcreatetime;
		this.replies = replies;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public StudentBasic getStudentBasic() {
		return this.studentBasic;
	}

	public void setStudentBasic(StudentBasic studentBasic) {
		this.studentBasic = studentBasic;
	}

	public String getPtitle() {
		return this.ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPcontent() {
		return this.pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public Date getPcreatetime() {
		return this.pcreatetime;
	}

	public void setPcreatetime(Date pcreatetime) {
		this.pcreatetime = pcreatetime;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}