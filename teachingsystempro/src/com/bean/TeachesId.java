package com.bean;

/**
 * TeachesId entity. @author MyEclipse Persistence Tools
 */

public class TeachesId implements java.io.Serializable {

	// Fields

	private String iid;
	private Integer cid;
	private short seid;

	// Constructors

	/** default constructor */
	public TeachesId() {
	}

	/** full constructor */
	public TeachesId(String iid, Integer cid, short seid) {
		this.iid = iid;
		this.cid = cid;
		this.seid = seid;
	}

	// Property accessors

	public String getIid() {
		return this.iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getSeid() {
		return this.seid;
	}

	public void setSeid(short seid) {
		this.seid = seid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TeachesId))
			return false;
		TeachesId castOther = (TeachesId) other;

		return ((this.getIid() == castOther.getIid()) || (this.getIid() != null
				&& castOther.getIid() != null && this.getIid().equals(
				castOther.getIid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& (this.getSeid() == castOther.getSeid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIid() == null ? 0 : this.getIid().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getSeid();
		return result;
	}

}