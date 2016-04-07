package com.bean;

/**
 * TakesId entity. @author MyEclipse Persistence Tools
 */

public class TakesId implements java.io.Serializable {

	// Fields

	private String stid;
	private Integer cid;
	private short seid;

	// Constructors

	/** default constructor */
	public TakesId() {
	}

	/** full constructor */
	public TakesId(String stid, Integer cid, short seid) {
		this.stid = stid;
		this.cid = cid;
		this.seid = seid;
	}

	// Property accessors

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
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
		if (!(other instanceof TakesId))
			return false;
		TakesId castOther = (TakesId) other;

		return ((this.getStid() == castOther.getStid()) || (this.getStid() != null
				&& castOther.getStid() != null && this.getStid().equals(
				castOther.getStid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& (this.getSeid() == castOther.getSeid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStid() == null ? 0 : this.getStid().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getSeid();
		return result;
	}

}