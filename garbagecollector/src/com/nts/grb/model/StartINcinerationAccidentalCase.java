package com.nts.grb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.nts.mrb.model.AuditClass;

@Entity
@Table(name = "Start_Incineration_Acc")
@Audited
@EntityListeners(AuditClass.class)
public class StartINcinerationAccidentalCase {
	// <----comman and entry variable for every entry------->
	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'Start_Incineration_Acc'")
	private String entry_type;

	public String getAttached_file_type() {
		return attached_file_type;
	}

	public void setAttached_file_type(String attached_file_type) {
		this.attached_file_type = attached_file_type;
	}

	public int getAttached_file_size() {
		return attached_file_size;
	}

	public void setAttached_file_size(int attached_file_size) {
		this.attached_file_size = attached_file_size;
	}

	@Column(name = "entry_Id", length = 10)
	private int entry_id;
	private String attached_file_type;
	private int attached_file_size;

	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}

	@Column(name = "entry_timezone", length = 20)
	private String enrty_timeZone;

	public String getEnrty_timeZone() {
		return enrty_timeZone;
	}

	public void setEnrty_timeZone(String enrty_timeZone) {
		this.enrty_timeZone = enrty_timeZone;
	}

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_timestamp", length = 10)
	private Date entry_time_date;
	@Column(name = "long_side", length = 10)
	private String ship_pos_longitude_loc;
	@Column(name = "left_side", length = 10)
	private String ship_pos_latitude_loc;
	@Column(name = "officer_id", length = 10)
	private int officer_id;
	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date", length = 10)
	private Date entry_date;
	@Column(name = "entry_time", length = 20)
	private String entry_time;
	@Column(name = "garbage_category", length = 150)
	private String garbage_category;
	@Column(name = "entry_remark", length = 255)
	private String remark;
	@Column(name = "lat_deg", length = 10)
	private Double ship_pos_latitude_degree;
	@Column(name = "long_deg", length = 10)
	private Double ship_pos_longitude_degree;
	@Column(name = "lat_min", length = 10)
	private Double ship_pos_latitude_min;

	public Date getEntry_time_date() {
		return entry_time_date;
	}

	public void setEntry_time_date(Date entry_time_date) {
		this.entry_time_date = entry_time_date;
	}

	public String getShip_pos_longitude_loc() {
		return ship_pos_longitude_loc;
	}

	public void setShip_pos_longitude_loc(String ship_pos_longitude_loc) {
		this.ship_pos_longitude_loc = ship_pos_longitude_loc;
	}

	public String getShip_pos_latitude_loc() {
		return ship_pos_latitude_loc;
	}

	public void setShip_pos_latitude_loc(String ship_pos_latitude_loc) {
		this.ship_pos_latitude_loc = ship_pos_latitude_loc;
	}

	public int getOfficer_id() {
		return officer_id;
	}

	public void setOfficer_id(int officer_id) {
		this.officer_id = officer_id;
	}

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public String getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}

	public String getGarbage_category() {
		return garbage_category;
	}

	public void setGarbage_category(String garbage_category) {
		this.garbage_category = garbage_category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getShip_pos_latitude_degree() {
		return ship_pos_latitude_degree;
	}

	public void setShip_pos_latitude_degree(Double ship_pos_latitude_degree) {
		this.ship_pos_latitude_degree = ship_pos_latitude_degree;
	}

	public Double getShip_pos_longitude_degree() {
		return ship_pos_longitude_degree;
	}

	public void setShip_pos_longitude_degree(Double ship_pos_longitude_degree) {
		this.ship_pos_longitude_degree = ship_pos_longitude_degree;
	}

	public Double getShip_pos_latitude_min() {
		return ship_pos_latitude_min;
	}

	public void setShip_pos_latitude_min(Double ship_pos_latitude_min) {
		this.ship_pos_latitude_min = ship_pos_latitude_min;
	}

	public Double getShip_pos_longitude_min() {
		return ship_pos_longitude_min;
	}

	public void setShip_pos_longitude_min(Double ship_pos_longitude_min) {
		this.ship_pos_longitude_min = ship_pos_longitude_min;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getIncineration_remark() {
		return incineration_remark;
	}

	public void setIncineration_remark(String incineration_remark) {
		this.incineration_remark = incineration_remark;
	}

	@Column(name = "long_min", length = 10)
	private Double ship_pos_longitude_min;
	@Column(name = "view_file_path", columnDefinition = "longtext")
	private String file_name;
	// ---------different variable from other enrty------
	@Column(name = "incineration_remark", length = 255)
	private String incineration_remark;
	@Column(name = "accidental_case", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
	private int accidentalCase = 1;
	// *****comn vrible for evry entry**********
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * private String estimatedAmountDischargedtoSea; private String
	 * estimatedAmountDischargedtoAshore;
	 */
	@Column(name = "master_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(20) default 'Pending'")
	private String mstrAprvl;
	@Column(name = "amend_omment", length = 250)
	private String amendComment;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "amend_time", length = 10)
	private Date amend_time;
	@Column(name = "master_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int mstrAprvlCond;
	@Column(name = "master_id", length = 10)
	private int masterId;
	@Column(name = "master_comment", length = 250)
	private String masterComment;
	@Column(name = "master_time", length = 20)
	private String mstrAprvlTime;
	@Column(name = "strike_value", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int strikeValue;
	@Column(name = "strike_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
	private String strikeAprvl;
	@Column(name = "strike_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private Boolean strikeAprvlCond;
	@Column(name = "strike_id", length = 10)
	private int strikeId;
	@Column(name = "strike_comment", length = 250)
	private String strikeComment;
	@Column(name = "strike_time", length = 20)
	private String strikeTime;
	@Column(name = "amend_approval", length = 20, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(20) default 'Allow'")
	private String amendAprvl;
	@Column(name = "amend_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private Boolean amendAprvlCond;
	@Column(name = "amend_id", length = 10)
	private int amendId;
	@Column(name = "master_reapproval", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'Pending'")
	private String mstrReAprvl;
	@Column(name = "master_reapproval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
	private int mstrReAprvlCond;
	@Column(name = "master_reid", length = 10)
	private int masterReId;
	@Column(name = "master_recomment", length = 250)
	private String masterReComment;

	public String getAmendComment() {
		return amendComment;
	}

	public String getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}

	public int getAccidentalCase() {
		return accidentalCase;
	}

	public void setAccidentalCase(int accidentalCase) {
		this.accidentalCase = accidentalCase;
	}

	public String getMstrAprvl() {
		return mstrAprvl;
	}

	public void setMstrAprvl(String mstrAprvl) {
		this.mstrAprvl = mstrAprvl;
	}

	public Date getAmend_time() {
		return amend_time;
	}

	public void setAmend_time(Date amend_time) {
		this.amend_time = amend_time;
	}

	public int getMstrAprvlCond() {
		return mstrAprvlCond;
	}

	public void setMstrAprvlCond(int mstrAprvlCond) {
		this.mstrAprvlCond = mstrAprvlCond;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getMstrAprvlTime() {
		return mstrAprvlTime;
	}

	public void setMstrAprvlTime(String mstrAprvlTime) {
		this.mstrAprvlTime = mstrAprvlTime;
	}

	public int getStrikeValue() {
		return strikeValue;
	}

	public void setStrikeValue(int strikeValue) {
		this.strikeValue = strikeValue;
	}

	public String getStrikeAprvl() {
		return strikeAprvl;
	}

	public void setStrikeAprvl(String strikeAprvl) {
		this.strikeAprvl = strikeAprvl;
	}

	public Boolean getStrikeAprvlCond() {
		return strikeAprvlCond;
	}

	public void setStrikeAprvlCond(Boolean strikeAprvlCond) {
		this.strikeAprvlCond = strikeAprvlCond;
	}

	public int getStrikeId() {
		return strikeId;
	}

	public void setStrikeId(int strikeId) {
		this.strikeId = strikeId;
	}

	public String getStrikeTime() {
		return strikeTime;
	}

	public void setStrikeTime(String strikeTime) {
		this.strikeTime = strikeTime;
	}

	public String getAmendAprvl() {
		return amendAprvl;
	}

	public void setAmendAprvl(String amendAprvl) {
		this.amendAprvl = amendAprvl;
	}

	public Boolean getAmendAprvlCond() {
		return amendAprvlCond;
	}

	public void setAmendAprvlCond(Boolean amendAprvlCond) {
		this.amendAprvlCond = amendAprvlCond;
	}

	public int getAmendId() {
		return amendId;
	}

	public void setAmendId(int amendId) {
		this.amendId = amendId;
	}

	public String getMstrReAprvl() {
		return mstrReAprvl;
	}

	public void setMstrReAprvl(String mstrReAprvl) {
		this.mstrReAprvl = mstrReAprvl;
	}

	

	public int getMasterReId() {
		return masterReId;
	}

	public void setMasterReId(int masterReId) {
		this.masterReId = masterReId;
	}

	public Date getMasterReTime() {
		return masterReTime;
	}

	public void setMasterReTime(Date masterReTime) {
		this.masterReTime = masterReTime;
	}

	public String getCmnt() {
		return cmnt;
	}

	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}

	public void setAmendComment(String amendComment) {
		this.amendComment = amendComment;
	}

	public String getMasterComment() {
		return masterComment;
	}

	public void setMasterComment(String masterComment) {
		this.masterComment = masterComment;
	}

	public String getStrikeComment() {
		return strikeComment;
	}

	public void setStrikeComment(String strikeComment) {
		this.strikeComment = strikeComment;
	}

	public String getMasterReComment() {
		return masterReComment;
	}

	public void setMasterReComment(String masterReComment) {
		this.masterReComment = masterReComment;
	}

	@Column(name = "master_retime", length = 10)
	private Date masterReTime;
	@Column(name = "comment", length = 255)
	private String cmnt;
}
