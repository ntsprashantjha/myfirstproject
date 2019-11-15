package com.nts.grb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.nts.mrb.model.AuditClass;

@Entity
@Table(name = "Discharge_To_Sea")
@Audited
@EntityListeners(AuditClass.class)
public class DischargeToSea {
// <----comman and entry variable for every entry------->
	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'Discharge_To_Sea'")
	private String entry_type;
	@Column(name = "entry_id", length = 10)
	private int entry_id;
	@Column(name = "entry_timezone", length = 20)
	private String enrty_timeZone;
	private String attached_file_type;
	private int attached_file_size;

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

	public String getEnrty_timeZone() {
		return enrty_timeZone;
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

	public String getAmendId() {
		return amendId;
	}

	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}

	public String getMasterReId() {
		return masterReId;
	}

	public void setMasterReId(String masterReId) {
		this.masterReId = masterReId;
	}

	public void setEnrty_timeZone(String enrty_timeZone) {
		this.enrty_timeZone = enrty_timeZone;
	}

	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
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
	@Column(name = "garbage_category", length = 20)
	private String garbage_category;
	@Column(name = "entry_remark", length = 255)
	private String remark;
	@Column(name = "lat_deg", length = 10)
	private Double ship_pos_latitude_degree;
	@Column(name = "long_deg", length = 10)
	private Double ship_pos_longitude_degree;
	@Column(name = "lat_min", length = 10)
	private Double ship_pos_latitude_min;
	@Column(name = "long_min", length = 10)
	private Double ship_pos_longitude_min;
	@Column(name = "view_file_path", columnDefinition = "longtext")
	private String file_name;
//---------different variable from other enrty------
	@Column(name = "garbage_quantity_sea", length = 10)
	private Double garbage_quantity;
//*****comn vrible for evry entry**********
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
	// here defining default value
	@Column(name = "master_approval", length = 20, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrAprvl;
	@Column(name = "amend_time", length = 20)
	private String amend_time;
	@Column(name = "master_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int mstrAprvlCond;
	@Column(name = "master_id", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default '0'")
	private String masterID;
	@Column(name = "master_time", length = 20)
	private String mstrAprvlTime;
	@Column(name = "strike_value", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int strikeValue;
	@Column(name = "strike_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
	private String strikeAprvl;
	@Column(name = "strike_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private Boolean strikeAprvlCond;
	@Column(name = "strike_id", length = 10)
	private String strikeId;
	@Column(name = "strike_time", length = 20)
	private String strikeTime;
	@Column(name = "amend_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
	private String amendAprvl;
	@Column(name = "amend_approval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private Boolean amendAprvlCond;
	@Column(name = "amend_id", length = 10)
	private String amendId;
	@Column(name = "master_reapproval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrReAprvl;
	@Column(name = "master_reapproval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
	private Boolean mstrReAprvlCond;
	@Column(name = "master_reid", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default '0'")
	private String masterReId;

	public String getMaster_comment() {
		return master_comment;
	}

	public void setMaster_comment(String master_comment) {
		this.master_comment = master_comment;
	}

	public String getMaster_recomment() {
		return master_recomment;
	}

	public void setMaster_recomment(String master_recomment) {
		this.master_recomment = master_recomment;
	}

	public String getStrike_comment() {
		return strike_comment;
	}

	public void setStrike_comment(String strike_comment) {
		this.strike_comment = strike_comment;
	}

	@Column(name = "master_retime", length = 20)
	private String masterReTime;
	@Column(name = "comment", length = 10)
	private String cmnt;
	@Column(name = "master_comment", length = 20)
	private String master_comment;
	@Column(name = "master_recomment", length = 20)
	private String master_recomment;
	@Column(name = "strike_comment", length = 20)
	private String strike_comment;

	// setter and getter method*****
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

	public Double getGarbage_quantity() {
		return garbage_quantity;
	}

	public void setGarbage_quantity(Double garbage_quantity) {
		this.garbage_quantity = garbage_quantity;
	}

}
