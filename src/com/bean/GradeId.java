package com.bean;

/**
 * GradeId entity. @author MyEclipse Persistence Tools
 */

public class GradeId implements java.io.Serializable {

	// Fields

	private String stid;
	private Integer pfid;

	// Constructors

	/** default constructor */
	public GradeId() {
	}

	/** full constructor */
	public GradeId(String stid, Integer pfid) {
		this.stid = stid;
		this.pfid = pfid;
	}

	// Property accessors

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public Integer getPfid() {
		return this.pfid;
	}

	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GradeId))
			return false;
		GradeId castOther = (GradeId) other;

		return ((this.getStid() == castOther.getStid()) || (this.getStid() != null
				&& castOther.getStid() != null && this.getStid().equals(
				castOther.getStid())))
				&& ((this.getPfid() == castOther.getPfid()) || (this.getPfid() != null
						&& castOther.getPfid() != null && this.getPfid()
						.equals(castOther.getPfid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStid() == null ? 0 : this.getStid().hashCode());
		result = 37 * result
				+ (getPfid() == null ? 0 : this.getPfid().hashCode());
		return result;
	}

}