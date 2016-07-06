package com.bean;

import java.io.Serializable;

public class CoursePre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5733511134899837776L;
	private Integer id;
	private String name;
	private Integer pid;
	private String pname;
	
	public CoursePre() {
		super();
	}
	
	public CoursePre(Integer id, String name, Integer pid, String pname) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.pname = pname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
}
