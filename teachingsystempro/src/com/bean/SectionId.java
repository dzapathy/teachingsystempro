package com.bean;

/**
 * SectionId entity. @author MyEclipse Persistence Tools
 */

public class SectionId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short seid;

	// Constructors

	/** default constructor */
	public SectionId() {
	}

	/** full constructor */
	public SectionId(Integer cid, short seid) {
		this.cid = cid;
		this.seid = seid;
	}

	// Property accessors

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
		if (!(other instanceof SectionId))
			return false;
		SectionId castOther = (SectionId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getSeid() == castOther.getSeid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getSeid();
		return result;
	}

}