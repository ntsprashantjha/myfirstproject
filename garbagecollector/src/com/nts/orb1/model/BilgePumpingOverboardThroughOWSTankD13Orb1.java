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
@Table(name = "bilge_pumping_overboard_thrw_tnk_ows_D13_orb1")
public class BilgePumpingOverboardThroughOWSTankD13Orb1 {

	// *********FrontEnd Data
	@Column(name = "start_date", length = 10)
	private String start_date;
	
	@Column(name = "start_time", length = 20)
	private String start_time;

	@Column(name = "stop_date", length = 10)
	private String stop_date;
	@Column(name = "stop_time", length = 20)
	private String stop_time;

	@Column(name = "start_latDeg", length = 20)
	private double strt_po_la_dgre;
	@Column(name = "start_latMin", length = 20)
	private double strt_po_la_min;
	@Column(name = "start_latSide", length = 20)
	private String strt_po_la_dir;
	
	@Column(name = "start_longDeg", length = 20)
	private double strt_po_lon_dgre;
	@Column(name = "start_longMin", length = 20)
	private double strt_po_lon_min;
	@Column(name = "start_longSide", length = 20)
	private String strt_po_lon_dir;

	@Column(name = "stop_latDeg", length = 20)
	private double stop_po_la_dgre;
	@Column(name = "stop_latMin", length = 20)
	private double stop_po_la_min;
	@Column(name = "stop_latSide", length = 20)
	private String stop_po_la_dir;
	
	@Column(name = "stop_longDeg", length = 20)
	private double stop_po_lon_dgre;
	@Column(name = "stop_longMin", length = 20)
	private double stop_po_lon_min;
	@Column(name = "stop_longSide", length = 20)
	private String stop_po_lon_dir;
	
	@Column(name = "qty_discharged", length = 10)
	private double quantity_discharged;
	
	@Column(name = "source_tank", length = 150)
	private String source_tank;
	@Column(name = "qty_retained", length = 10)
	private double quan_ret_sou_tank;

	public String getStrt_po_la_dir() {
		return strt_po_la_dir;
	}

	public void setStrt_po_la_dir(String strt_po_la_dir) {
		this.strt_po_la_dir = strt_po_la_dir;
	}

	public String getStrt_po_lon_dir() {
		return strt_po_lon_dir;
	}

	public void setStrt_po_lon_dir(String strt_po_lon_dir) {
		this.strt_po_lon_dir = strt_po_lon_dir;
	}

	public String getStop_po_la_dir() {
		return stop_po_la_dir;
	}

	public void setStop_po_la_dir(String stop_po_la_dir) {
		this.stop_po_la_dir = stop_po_la_dir;
	}

	public String getStop_po_lon_dir() {
		return stop_po_lon_dir;
	}

	public void setStop_po_lon_dir(String stop_po_lon_dir) {
		this.stop_po_lon_dir = stop_po_lon_dir;
	}

	

	public String getSource_tank() {
		return source_tank;
	}

	public void setSource_tank(String source_tank) {
		this.source_tank = source_tank;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getStop_date() {
		return stop_date;
	}

	public void setStop_date(String stop_date) {
		this.stop_date = stop_date;
	}

	public double getQuantity_discharged() {
		return quantity_discharged;
	}

	public void setQuantity_discharged(double quantity_discharged) {
		this.quantity_discharged = quantity_discharged;
	}

	public double getQuan_ret_sou_tank() {
		return quan_ret_sou_tank;
	}

	public void setQuan_ret_sou_tank(double quan_ret_sou_tank) {
		this.quan_ret_sou_tank = quan_ret_sou_tank;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	public double getStrt_po_la_dgre() {
		return strt_po_la_dgre;
	}

	public void setStrt_po_la_dgre(double strt_po_la_dgre) {
		this.strt_po_la_dgre = strt_po_la_dgre;
	}

	public double getStrt_po_la_min() {
		return strt_po_la_min;
	}

	public void setStrt_po_la_min(double strt_po_la_min) {
		this.strt_po_la_min = strt_po_la_min;
	}

	public double getStrt_po_lon_dgre() {
		return strt_po_lon_dgre;
	}

	public void setStrt_po_lon_dgre(double strt_po_lon_dgre) {
		this.strt_po_lon_dgre = strt_po_lon_dgre;
	}

	public double getStrt_po_lon_min() {
		return strt_po_lon_min;
	}

	public void setStrt_po_lon_min(double strt_po_lon_min) {
		this.strt_po_lon_min = strt_po_lon_min;
	}

	public double getStop_po_la_dgre() {
		return stop_po_la_dgre;
	}

	public void setStop_po_la_dgre(double stop_po_la_dgre) {
		this.stop_po_la_dgre = stop_po_la_dgre;
	}

	public double getStop_po_la_min() {
		return stop_po_la_min;
	}

	public void setStop_po_la_min(double stop_po_la_min) {
		this.stop_po_la_min = stop_po_la_min;
	}

	public double getStop_po_lon_dgre() {
		return stop_po_lon_dgre;
	}

	public void setStop_po_lon_dgre(double stop_po_lon_dgre) {
		this.stop_po_lon_dgre = stop_po_lon_dgre;
	}

	public double getStop_po_lon_min() {
		return stop_po_lon_min;
	}

	public void setStop_po_lon_min(double stop_po_lon_min) {
		this.stop_po_lon_min = stop_po_lon_min;
	}
	
	

	// <----comman and entry variable for every entry------->
	@Column(name = "master_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrAprvl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date", length = 10)
	private Date amend_time;
	@Column(name = "master_approval_done", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int mstrAprvlCond;
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
	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(100) default 'Pumping Of Bilge Water Overboard Through OWS From Tank'")
	private String entry_type;
	@Column(name = "entry_timezone", length = 10)
	private String enrty_timeZone;
	@Column(name = "officer_id", length = 10)
	private int officer_id;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_timestamp", length = 10)
	private Date entry_time_date;

	public String getEnrty_timeZone() {
		return enrty_timeZone;
	}

	public void setEnrty_timeZone(String enrty_timeZone) {
		this.enrty_timeZone = enrty_timeZone;
	}

	public Date getEntry_time_date() {
		return entry_time_date;
	}

	public void setEntry_time_date(Date entry_time_date) {
		this.entry_time_date = entry_time_date;
	}

	public int getOfficer_id() {
		return officer_id;
	}

	public void setOfficer_id(int officer_id) {
		this.officer_id = officer_id;
	}
	
	@Column(name = "entry_code", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'D'")
	private String entry_code;

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

	@Column(name = "master_id", length = 50)
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
