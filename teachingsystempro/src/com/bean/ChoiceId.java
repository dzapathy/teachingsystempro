package com.bean;

/**
 * ChoiceId entity. @author MyEclipse Persistence Tools
 */

public class ChoiceId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short chchapter;
	private Integer chid;

	// Constructors

	/** default constructor */
	public ChoiceId() {
	}

	/** full constructor */
	public ChoiceId(Integer cid, short chchapter, Integer chid) {
		this.cid = cid;
		this.chchapter = chchapter;
		this.chid = chid;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getChchapter() {
		return this.chchapter;
	}

	public void setChchapter(short chchapter) {
		this.chchapter = chchapter;
	}

	public Integer getChid() {
		return this.chid;
	}

	public void setChid(Integer chid) {
		this.chid = chid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChoiceId))
			return false;
		ChoiceId castOther = (ChoiceId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getChchapter() == castOther.getChchapter())
				&& ((this.getChid() == castOther.getChid()) || (this.getChid() != null
						&& castOther.getChid() != null && this.getChid()
						.equals(castOther.getChid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getChchapter();
		result = 37 * result
				+ (getChid() == null ? 0 : this.getChid().hashCode());
		return result;
	}

}