package com.nts.grb2.model;

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
@Table(name = "start_deck_washing_grb2")
@Audited
@EntityListeners(AuditClass.class)
public class DischargeToSeaStartDeckWashing {
	// <----comman and entry variable for every entry------->
	@Column(name = "master_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrAprvl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date", length = 10)
	private Date amend_time;

	@Column(name = "master_approval_done", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int mstrAprvlCond;

	@Column(name = "master_id", length = 5)
	private String masterId;
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

	@Column(name = "strike_comment", length = 250)
	private String strikeComment;

	@Column(name = "strike_id", length = 5, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default '0'")
	private String strikeId;

	@Column(name = "strike_time", length = 20)
	private String strikeTime;

	@Column(name = "amend_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
	private String amendAprvl;

	@Column(name = "amend_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private Boolean amendAprvlCond;

	@Column(name = "amend_comment", length = 250)
	private String amendComment;

	@Column(name = "amend_id", length = 5, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default '0'")
	private int amendId;

	@Column(name = "amend_time", length = 20)
	private String amendTime;

	@Column(name = "master_reapproval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrReAprvl;

	@Column(name = "master_reapproval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
	private int mstrReAprvlCond;

	@Column(name = "master_reid", length = 5, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int masterReId;

	@Column(name = "master_retime", length = 20)
	private String masterReTime;

	@Column(name = "master_recomment", length = 300)
	private String masterReComment;

	@Column(name = "comment", length = 300)
	private String cmnt;

	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'start_deck_washing_grb2'")
	private String entry_type;
	@Column(name = "entry_timezone", length = 10)
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

	public Date getEntry_time_date() {
		return entry_time_date;
	}

	public void setEntry_time_date(Date entry_time_date) {
		this.entry_time_date = entry_time_date;
	}

	@Column(name = "long_side", length = 10)
	private String ship_pos_longitude_loc;
	@Column(name = "lat_side", length = 10)
	private String ship_pos_latitude_loc;
	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date", length = 10)
	private Date entry_date;
	@Column(name = "entry_time", length = 10)
	private String entry_time;
	@Column(name = "garbage_category", length = 150)
	private String garbage_category;
	@Column(name = "entry_remark", length = 255)
	private String remark;
	@Column(name = "lat_deg", length = 10)
	private double ship_pos_latitude_degree;
	@Column(name = "long_deg", length = 10)
	private double ship_pos_longitude_degree;
	@Column(name = "lat_min", length = 10)
	private double ship_pos_latitude_min;
	@Column(name = "long_min", length = 10)
	private double ship_pos_longitude_min;
	@Column(name = "view_file_path", columnDefinition = "longtext")
	private String file_name;
	private String attached_file_type;
	private double attached_file_size;

	public String getAttached_file_type() {
		return attached_file_type;
	}

	public void setAttached_file_type(String attached_file_type) {
		this.attached_file_type = attached_file_type;
	}

	public double getAttached_file_size() {
		return attached_file_size;
	}

	public void setAttached_file_size(double d) {
		this.attached_file_size = d;
	}

	public String getDeck_washing_remark() {
		return deck_washing_remark;
	}

	public void setDeck_washing_remark(String deck_washing_remark) {
		this.deck_washing_remark = deck_washing_remark;
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

	public double getShip_pos_latitude_degree() {
		return ship_pos_latitude_degree;
	}

	public void setShip_pos_latitude_degree(double d) {
		this.ship_pos_latitude_degree = d;
	}

	public double getShip_pos_longitude_degree() {
		return ship_pos_longitude_degree;
	}

	public void setShip_pos_longitude_degree(double d) {
		this.ship_pos_longitude_degree = d;
	}

	public double getShip_pos_latitude_min() {
		return ship_pos_latitude_min;
	}

	public void setShip_pos_latitude_min(double d) {
		this.ship_pos_latitude_min = d;
	}

	public double getShip_pos_longitude_min() {
		return ship_pos_longitude_min;
	}

	public void setShip_pos_longitude_min(double d) {
		this.ship_pos_longitude_min = d;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@Column(name = "deck_washing_remark", length = 255)
	private String deck_washing_remark;

	@Column(name = "garbage_quantity_sea", length = 50)
	private Double garbage_quantity;

	public Double getGarbage_quantity() {
		return garbage_quantity;
	}

	public void setGarbage_quantity(Double garbage_quantity) {
		this.garbage_quantity = garbage_quantity;
	}

	@Column(name = "officer_id", length = 10)
	private int officer_id;

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

	// ************************************other information*******************
	@Column(name = "masterId", length = 50)
	public String masterID;

	@Column(name = "entry_id", length = 10)
	public int entry_id;

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

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getMasterComment() {
		return masterComment;
	}

	public void setMasterComment(String masterComment) {
		this.masterComment = masterComment;
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

	public String getStrikeComment() {
		return strikeComment;
	}

	public void setStrikeComment(String strikeComment) {
		this.strikeComment = strikeComment;
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

	public String getAmendComment() {
		return amendComment;
	}

	public void setAmendComment(String amendComment) {
		this.amendComment = amendComment;
	}

	public int getAmendId() {
		return amendId;
	}

	public void setAmendId(int amendId) {
		this.amendId = amendId;
	}

	public String getAmendTime() {
		return amendTime;
	}

	public void setAmendTime(String amendTime) {
		this.amendTime = amendTime;
	}

	public String getMstrReAprvl() {
		return mstrReAprvl;
	}

	public void setMstrReAprvl(String mstrReAprvl) {
		this.mstrReAprvl = mstrReAprvl;
	}

	public int getMstrReAprvlCond() {
		return mstrReAprvlCond;
	}

	public void setMstrReAprvlCond(int mstrReAprvlCond) {
		this.mstrReAprvlCond = mstrReAprvlCond;
	}

	public int getMasterReId() {
		return masterReId;
	}

	public void setMasterReId(int masterReId) {
		this.masterReId = masterReId;
	}

	public String getMasterReTime() {
		return masterReTime;
	}

	public void setMasterReTime(String masterReTime) {
		this.masterReTime = masterReTime;
	}

	public String getMasterReComment() {
		return masterReComment;
	}

	public void setMasterReComment(String masterReComment) {
		this.masterReComment = masterReComment;
	}

	public String getCmnt() {
		return cmnt;
	}

	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}

	public String getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}

	public String getMasterID() {
		return masterID;
	}

	public void setMasterID(String masterID) {
		this.masterID = masterID;
	}

	public String getStrikeId() {
		return strikeId;
	}

	public void setStrikeId(String strikeId) {
		this.strikeId = strikeId;
	}

	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}

}
