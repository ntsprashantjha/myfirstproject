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
@Table(name = "restoration_of_faulty_equipment_F19_20_21_orb1")
public class RestorationOfFaultyEquipmentF19_20_21Orb1 {

	@Column(name = "entry_code", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'F'")
	private String entry_code;
	
	// *********FrontEnd Data
	@Column(name = "stop_date", length = 10)
	private String stop_date;
	@Column(name = "start_date", length = 10)
	private String start_date;
	
	@Column(name = "date_of_operational", length = 50)
	private String date_of_operational;
	@Column(name = "time_of_operational", length = 30)
	private String time_of_operational;

	@Column(name = "time_of_failure", length = 20)
	private String time_of_failure;
	@Column(name = "reason_of_failure", length = 100)
	private String reason_of_failure;
	
	@Column(name = "seal_number_for_overboard_valve", length = 10)
	private String seal_number_for_overboard_valve;
	@Column(name = "seal_valve_no", length = 20)
	private String seal_valve_no;

	// <----comman and entry variable for every entry------->
	
	@Column(name = "missed_entry", length = 10)
	private String missed_entry;
	
	public String getEntry_code() {
		return entry_code;
	}

	public void setEntry_code(String entry_code) {
		this.entry_code = entry_code;
	}

	public String getMissed_entry() {
		return missed_entry;
	}

	public void setMissed_entry(String missed_entry) {
		this.missed_entry = missed_entry;
	}

	@Column(name = "master_approval", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
	private String mstrAprvl;

	public String getStop_date() {
		return stop_date;
	}

	public void setStop_date(String stop_date) {
		this.stop_date = stop_date;
	}

	public String getTime_of_failure() {
		return time_of_failure;
	}

	public void setTime_of_failure(String time_of_failure) {
		this.time_of_failure = time_of_failure;
	}

	public String getReason_of_failure() {
		return reason_of_failure;
	}

	public void setReason_of_failure(String reason_of_failure) {
		this.reason_of_failure = reason_of_failure;
	}

	

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getDate_of_operational() {
		return date_of_operational;
	}

	public void setDate_of_operational(String date_of_operational) {
		this.date_of_operational = date_of_operational;
	}

	public String getTime_of_operational() {
		return time_of_operational;
	}

	public void setTime_of_operational(String time_of_operational) {
		this.time_of_operational = time_of_operational;
	}

	public String getSeal_number_for_overboard_valve() {
		return seal_number_for_overboard_valve;
	}

	public void setSeal_number_for_overboard_valve(String seal_number_for_overboard_valve) {
		this.seal_number_for_overboard_valve = seal_number_for_overboard_valve;
	}

	public String getSeal_valve_no() {
		return seal_valve_no;
	}

	public void setSeal_valve_no(String seal_valve_no) {
		this.seal_valve_no = seal_valve_no;
	}

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
	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)'")
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
