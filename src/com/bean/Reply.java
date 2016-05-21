package com.bean;

import java.util.Date;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private Integer rid;
	private Posts posts;
	private String rcontent;
	private String rauthorid;
	private Date rcreatetime;
	private String rrauthorid;
	private String rurl;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Posts posts, String rcontent, String rauthorid,
			Date rcreatetime) {
		this.posts = posts;
		this.rcontent = rcontent;
		this.rauthorid = rauthorid;
		this.rcreatetime = rcreatetime;
	}

	/** full constructor */
	public Reply(Posts posts, String rcontent, String rauthorid,
			Date rcreatetime, String rrauthorid, String rurl) {
		this.posts = posts;
		this.rcontent = rcontent;
		this.rauthorid = rauthorid;
		this.rcreatetime = rcreatetime;
		this.rrauthorid = rrauthorid;
		this.rurl = rurl;
	}

	// Property accessors

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Posts getPosts() {
		return this.posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public String getRcontent() {
		return this.rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRauthorid() {
		return this.rauthorid;
	}

	public void setRauthorid(String rauthorid) {
		this.rauthorid = rauthorid;
	}

	public Date getRcreatetime() {
		return this.rcreatetime;
	}

	public void setRcreatetime(Date rcreatetime) {
		this.rcreatetime = rcreatetime;
	}

	public String getRrauthorid() {
		return this.rrauthorid;
	}

	public void setRrauthorid(String rrauthorid) {
		this.rrauthorid = rrauthorid;
	}

	public String getRurl() {
		return this.rurl;
	}

	public void setRurl(String rurl) {
		this.rurl = rurl;
	}

}