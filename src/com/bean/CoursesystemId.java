package com.bean;

/**
 * CoursesystemId entity. @author MyEclipse Persistence Tools
 */

public class CoursesystemId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;

	// Constructors

	/** default constructor */
	public CoursesystemId() {
	}

	/** minimal constructor */
	public CoursesystemId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CoursesystemId(Integer id, Integer pid) {
		this.id = id;
		this.pid = pid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CoursesystemId))
			return false;
		CoursesystemId castOther = (CoursesystemId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		return result;
	}

}