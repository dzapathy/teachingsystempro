package com.bean;

/**
 * PptId entity. @author MyEclipse Persistence Tools
 */

public class PptId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short pchapter;
	private Integer pid;

	// Constructors

	/** default constructor */
	public PptId() {
	}

	/** full constructor */
	public PptId(Integer cid, short pchapter, Integer pid) {
		this.cid = cid;
		this.pchapter = pchapter;
		this.pid = pid;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getPchapter() {
		return this.pchapter;
	}

	public void setPchapter(short pchapter) {
		this.pchapter = pchapter;
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
		if (!(other instanceof PptId))
			return false;
		PptId castOther = (PptId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getPchapter() == castOther.getPchapter())
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getPchapter();
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		return result;
	}

}