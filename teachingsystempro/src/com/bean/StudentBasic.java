package com.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * StudentBasic entity. @author MyEclipse Persistence Tools
 */

public class StudentBasic implements java.io.Serializable {

	// Fields

	private String stid;
	private String stname;
	private String stpassword;
	private String stemail;
	private String sturl;
	private Set paperdetails = new HashSet(0);
	private Set postses = new HashSet(0);
	private Set studentDetails = new HashSet(0);
	private Set grades = new HashSet(0);
	private Set takeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public StudentBasic() {
	}

	/** minimal constructor */
	public StudentBasic(String stid, String stname, String stpassword,
			String stemail) {
		this.stid = stid;
		this.stname = stname;
		this.stpassword = stpassword;
		this.stemail = stemail;
	}

	/** full constructor */
	public StudentBasic(String stid, String stname, String stpassword,
			String stemail, String sturl, Set paperdetails, Set postses,
			Set studentDetails, Set grades, Set takeses) {
		this.stid = stid;
		this.stname = stname;
		this.stpassword = stpassword;
		this.stemail = stemail;
		this.sturl = sturl;
		this.paperdetails = paperdetails;
		this.postses = postses;
		this.studentDetails = studentDetails;
		this.grades = grades;
		this.takeses = takeses;
	}

	// Property accessors

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getStname() {
		return this.stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getStpassword() {
		return this.stpassword;
	}

	public void setStpassword(String stpassword) {
		this.stpassword = stpassword;
	}

	public String getStemail() {
		return this.stemail;
	}

	public void setStemail(String stemail) {
		this.stemail = stemail;
	}

	public String getSturl() {
		return this.sturl;
	}

	public void setSturl(String sturl) {
		this.sturl = sturl;
	}

	public Set getPaperdetails() {
		return this.paperdetails;
	}

	public void setPaperdetails(Set paperdetails) {
		this.paperdetails = paperdetails;
	}

	public Set getPostses() {
		return this.postses;
	}

	public void setPostses(Set postses) {
		this.postses = postses;
	}

	public Set getStudentDetails() {
		return this.studentDetails;
	}

	public void setStudentDetails(Set studentDetails) {
		this.studentDetails = studentDetails;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public Set getTakeses() {
		return this.takeses;
	}

	public void setTakeses(Set takeses) {
		this.takeses = takeses;
	}

}