package com.bean;

import java.util.Date;

/**
 * StudentDetailId entity. @author MyEclipse Persistence Tools
 */

public class StudentDetailId implements java.io.Serializable {

	// Fields

	private String stid;
	private String stclass;
	private String stphone;
	private Date stbirthday;
	private String stsex;

	// Constructors

	/** default constructor */
	public StudentDetailId() {
	}

	/** minimal constructor */
	public StudentDetailId(String stid) {
		this.stid = stid;
	}

	/** full constructor */
	public StudentDetailId(String stid, String stclass, String stphone,
			Date stbirthday, String stsex) {
		this.stid = stid;
		this.stclass = stclass;
		this.stphone = stphone;
		this.stbirthday = stbirthday;
		this.stsex = stsex;
	}

	// Property accessors

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getStclass() {
		return this.stclass;
	}

	public void setStclass(String stclass) {
		this.stclass = stclass;
	}

	public String getStphone() {
		return this.stphone;
	}

	public void setStphone(String stphone) {
		this.stphone = stphone;
	}

	public Date getStbirthday() {
		return this.stbirthday;
	}

	public void setStbirthday(Date stbirthday) {
		this.stbirthday = stbirthday;
	}

	public String getStsex() {
		return this.stsex;
	}

	public void setStsex(String stsex) {
		this.stsex = stsex;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StudentDetailId))
			return false;
		StudentDetailId castOther = (StudentDetailId) other;

		return ((this.getStid() == castOther.getStid()) || (this.getStid() != null
				&& castOther.getStid() != null && this.getStid().equals(
				castOther.getStid())))
				&& ((this.getStclass() == castOther.getStclass()) || (this
						.getStclass() != null && castOther.getStclass() != null && this
						.getStclass().equals(castOther.getStclass())))
				&& ((this.getStphone() == castOther.getStphone()) || (this
						.getStphone() != null && castOther.getStphone() != null && this
						.getStphone().equals(castOther.getStphone())))
				&& ((this.getStbirthday() == castOther.getStbirthday()) || (this
						.getStbirthday() != null
						&& castOther.getStbirthday() != null && this
						.getStbirthday().equals(castOther.getStbirthday())))
				&& ((this.getStsex() == castOther.getStsex()) || (this
						.getStsex() != null && castOther.getStsex() != null && this
						.getStsex().equals(castOther.getStsex())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStid() == null ? 0 : this.getStid().hashCode());
		result = 37 * result
				+ (getStclass() == null ? 0 : this.getStclass().hashCode());
		result = 37 * result
				+ (getStphone() == null ? 0 : this.getStphone().hashCode());
		result = 37
				* result
				+ (getStbirthday() == null ? 0 : this.getStbirthday()
						.hashCode());
		result = 37 * result
				+ (getStsex() == null ? 0 : this.getStsex().hashCode());
		return result;
	}

}