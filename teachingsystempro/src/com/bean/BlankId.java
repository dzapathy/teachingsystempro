package com.bean;

/**
 * BlankId entity. @author MyEclipse Persistence Tools
 */

public class BlankId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short bchapter;
	private Integer bid;

	// Constructors

	/** default constructor */
	public BlankId() {
	}

	/** full constructor */
	public BlankId(Integer cid, short bchapter, Integer bid) {
		this.cid = cid;
		this.bchapter = bchapter;
		this.bid = bid;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getBchapter() {
		return this.bchapter;
	}

	public void setBchapter(short bchapter) {
		this.bchapter = bchapter;
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BlankId))
			return false;
		BlankId castOther = (BlankId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getBchapter() == castOther.getBchapter())
				&& ((this.getBid() == castOther.getBid()) || (this.getBid() != null
						&& castOther.getBid() != null && this.getBid().equals(
						castOther.getBid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getBchapter();
		result = 37 * result
				+ (getBid() == null ? 0 : this.getBid().hashCode());
		return result;
	}

}