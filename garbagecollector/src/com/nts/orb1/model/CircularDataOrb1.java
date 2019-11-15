package com.nts.orb1.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "circular_details_orb1")
public class CircularDataOrb1 {
	@Id
	@GeneratedValue
	private int id;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 10)
	private Date updatedate;
	@Column(name = "circular_description", length = 255)
	private String circularDesc;
	@Column(name = "circular_base_code", columnDefinition = "longtext")
	private String circulerfile;
	@Column(name = "circular_doc_type", length = 50)
	private String circularfiletype;
	@Column(name = "circular_size", length = 50)
	private int circularsize;
	@Column(name = "officer_info", length = 150)
	private String Officerinfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getCircularDesc() {
		return circularDesc;
	}

	public void setCircularDesc(String circularDesc) {
		this.circularDesc = circularDesc;
	}

	public String getCirculerfile() {
		return circulerfile;
	}

	public void setCirculerfile(String circulerfile) {
		this.circulerfile = circulerfile;
	}

	public String getCircularfiletype() {
		return circularfiletype;
	}

	public void setCircularfiletype(String circularfiletype) {
		this.circularfiletype = circularfiletype;
	}

	public int getCircularsize() {
		return circularsize;
	}

	public void setCircularsize(int circularsize) {
		this.circularsize = circularsize;
	}

	public String getOfficerinfo() {
		return Officerinfo;
	}

	public void setOfficerinfo(String officerinfo) {
		this.Officerinfo = officerinfo;
	}

}
