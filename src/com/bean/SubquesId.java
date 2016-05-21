package com.bean;

/**
 * SubquesId entity. @author MyEclipse Persistence Tools
 */

public class SubquesId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short suchapter;
	private Integer suid;

	// Constructors

	/** default constructor */
	public SubquesId() {
	}

	/** full constructor */
	public SubquesId(Integer cid, short suchapter, Integer suid) {
		this.cid = cid;
		this.suchapter = suchapter;
		this.suid = suid;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getSuchapter() {
		return this.suchapter;
	}

	public void setSuchapter(short suchapter) {
		this.suchapter = suchapter;
	}

	public Integer getSuid() {
		return this.suid;
	}

	public void setSuid(Integer suid) {
		this.suid = suid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SubquesId))
			return false;
		SubquesId castOther = (SubquesId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getSuchapter() == castOther.getSuchapter())
				&& ((this.getSuid() == castOther.getSuid()) || (this.getSuid() != null
						&& castOther.getSuid() != null && this.getSuid()
						.equals(castOther.getSuid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getSuchapter();
		result = 37 * result
				+ (getSuid() == null ? 0 : this.getSuid().hashCode());
		return result;
	}

}