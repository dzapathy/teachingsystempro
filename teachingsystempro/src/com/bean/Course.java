package com.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private short cchapter;
	private short ccredit;
	private String ccontent;
	private String curl;
	private Set ppts = new HashSet(0);
	private Set medias = new HashSet(0);
	private Set blanks = new HashSet(0);
	private Set choices = new HashSet(0);
	private Set paperforms = new HashSet(0);
	private Set postses = new HashSet(0);
	private Set sections = new HashSet(0);
	private Set subqueses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(String cname, short cchapter, short ccredit) {
		this.cname = cname;
		this.cchapter = cchapter;
		this.ccredit = ccredit;
	}

	/** full constructor */
	public Course(String cname, short cchapter, short ccredit, String ccontent,
			String curl, Set ppts, Set medias, Set blanks, Set choices,
			Set paperforms, Set postses, Set sections, Set subqueses) {
		this.cname = cname;
		this.cchapter = cchapter;
		this.ccredit = ccredit;
		this.ccontent = ccontent;
		this.curl = curl;
		this.ppts = ppts;
		this.medias = medias;
		this.blanks = blanks;
		this.choices = choices;
		this.paperforms = paperforms;
		this.postses = postses;
		this.sections = sections;
		this.subqueses = subqueses;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public short getCchapter() {
		return this.cchapter;
	}

	public void setCchapter(short cchapter) {
		this.cchapter = cchapter;
	}

	public short getCcredit() {
		return this.ccredit;
	}

	public void setCcredit(short ccredit) {
		this.ccredit = ccredit;
	}

	public String getCcontent() {
		return this.ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public Set getPpts() {
		return this.ppts;
	}

	public void setPpts(Set ppts) {
		this.ppts = ppts;
	}

	public Set getMedias() {
		return this.medias;
	}

	public void setMedias(Set medias) {
		this.medias = medias;
	}

	public Set getBlanks() {
		return this.blanks;
	}

	public void setBlanks(Set blanks) {
		this.blanks = blanks;
	}

	public Set getChoices() {
		return this.choices;
	}

	public void setChoices(Set choices) {
		this.choices = choices;
	}

	public Set getPaperforms() {
		return this.paperforms;
	}

	public void setPaperforms(Set paperforms) {
		this.paperforms = paperforms;
	}

	public Set getPostses() {
		return this.postses;
	}

	public void setPostses(Set postses) {
		this.postses = postses;
	}

	public Set getSections() {
		return this.sections;
	}

	public void setSections(Set sections) {
		this.sections = sections;
	}

	public Set getSubqueses() {
		return this.subqueses;
	}

	public void setSubqueses(Set subqueses) {
		this.subqueses = subqueses;
	}

}