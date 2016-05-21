package com.bean;

/**
 * MediaId entity. @author MyEclipse Persistence Tools
 */

public class MediaId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private short mchapter;
	private Integer mid;

	// Constructors

	/** default constructor */
	public MediaId() {
	}

	/** full constructor */
	public MediaId(Integer cid, short mchapter, Integer mid) {
		this.cid = cid;
		this.mchapter = mchapter;
		this.mid = mid;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public short getMchapter() {
		return this.mchapter;
	}

	public void setMchapter(short mchapter) {
		this.mchapter = mchapter;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MediaId))
			return false;
		MediaId castOther = (MediaId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& (this.getMchapter() == castOther.getMchapter())
				&& ((this.getMid() == castOther.getMid()) || (this.getMid() != null
						&& castOther.getMid() != null && this.getMid().equals(
						castOther.getMid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result + this.getMchapter();
		result = 37 * result
				+ (getMid() == null ? 0 : this.getMid().hashCode());
		return result;
	}

}