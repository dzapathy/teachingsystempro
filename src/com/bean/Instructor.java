package com.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Instructor entity. @author MyEclipse Persistence Tools
 */

public class Instructor implements java.io.Serializable {

	// Fields

	private String iid;
	private String iname;
	private String ipassword;
	private String iemail;
	private String iphone;
	private String iurl;
	private String iintroduction;
	private short istatus;
	private Set teacheses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Instructor() {
	}

	/** minimal constructor */
	public Instructor(String iid, String iname, String ipassword, String iemail) {
		this.iid = iid;
		this.iname = iname;
		this.ipassword = ipassword;
		this.iemail = iemail;
	}

	/** full constructor */
	public Instructor(String iid, String iname, String ipassword,
			String iemail, String iphone, String iurl, String iintroduction,
			short istatus, Set teacheses) {
		this.iid = iid;
		this.iname = iname;
		this.ipassword = ipassword;
		this.iemail = iemail;
		this.iphone = iphone;
		this.iurl = iurl;
		this.iintroduction = iintroduction;
		this.istatus = istatus;
		this.teacheses = teacheses;
	}

	// Property accessors

	public String getIid() {
		return this.iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getIname() {
		return this.iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIpassword() {
		return this.ipassword;
	}

	public void setIpassword(String ipassword) {
		this.ipassword = ipassword;
	}

	public String getIemail() {
		return this.iemail;
	}

	public void setIemail(String iemail) {
		this.iemail = iemail;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getIurl() {
		return this.iurl;
	}

	public void setIurl(String iurl) {
		this.iurl = iurl;
	}

	public String getIintroduction() {
		return this.iintroduction;
	}

	public void setIintroduction(String iintroduction) {
		this.iintroduction = iintroduction;
	}

	public short getIstatus() {
		return this.istatus;
	}

	public void setIstatus(short istatus) {
		this.istatus = istatus;
	}

	public Set getTeacheses() {
		return this.teacheses;
	}

	public void setTeacheses(Set teacheses) {
		this.teacheses = teacheses;
	}

}