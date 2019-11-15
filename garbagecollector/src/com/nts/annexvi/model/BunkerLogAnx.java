package com.nts.annexvi.model;

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
@Table(name = "bunker_log_anx")
public class BunkerLogAnx {

	// ********* FrontEnd Columns

	@Column(name = "date", length = 10)
	private String date;

	@Column(name = "dbn_ref_no", length = 30)
	private String dbn_ref_no;

	@Column(name = "port", length = 10)
	private String port;

	@Column(name = "bunker_supplier", length = 30)
	private String bunker_supplier;
	
	@Column(name = "deliver_by", length = 100)
	private String deliver_by;
	
	@Column(name = "fuel_oil_grade", length = 30)
	private String fuel_oil_grade;

	@Column(name = "total_quantity", length = 10)
	private double total_quantity;
	
	@Column(name = "bdn_sulphur", length = 10)
	private double bdn_sulphur;
	
	@Column(name = "marpol_sample_seal_number", length = 30)
	private String marpol_sample_seal_number;
	
	@Column(name = "bdn_compliant", length = 10)
	private String bdn_compliant;
	
	@Column(name = "marpol_sample_complaint", length = 10)
	private String marpol_sample_complaint;
	
	@Column(name = "lop_issued", length = 10)
	private String lop_issued;
	
	@Column(name = "lop_issue_remark", length = 30)
	private String lop_issue_remark;
	
	@Column(name = "sample_sent_for_analysis", length = 5, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(5) default 'NA'")
	private String sample_sent_for_analysis;
	
	@Column(name = "reason", length = 100)
	private String reason;
	
	@Column(name = "sample_sent_date", length = 10)
	private String sample_sent_date;
	
	@Column(name = "view_file_path", columnDefinition = "longtext")
	private String fileNameForm;
	private String file_typeForm;
	private double file_sizeForm;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDbn_ref_no() {
		return dbn_ref_no;
	}

	public void setDbn_ref_no(String dbn_ref_no) {
		this.dbn_ref_no = dbn_ref_no;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getBunker_supplier() {
		return bunker_supplier;
	}

	public void setBunker_supplier(String bunker_supplier) {
		this.bunker_supplier = bunker_supplier;
	}

	public String getDeliver_by() {
		return deliver_by;
	}

	public void setDeliver_by(String deliver_by) {
		this.deliver_by = deliver_by;
	}

	public String getFuel_oil_grade() {
		return fuel_oil_grade;
	}

	public void setFuel_oil_grade(String fuel_oil_grade) {
		this.fuel_oil_grade = fuel_oil_grade;
	}

	public double getBdn_sulphur() {
		return bdn_sulphur;
	}

	public void setBdn_sulphur(double bdn_sulphur) {
		this.bdn_sulphur = bdn_sulphur;
	}

	public String getMarpol_sample_seal_number() {
		return marpol_sample_seal_number;
	}

	public void setMarpol_sample_seal_number(String marpol_sample_seal_number) {
		this.marpol_sample_seal_number = marpol_sample_seal_number;
	}

	public String getBdn_compliant() {
		return bdn_compliant;
	}

	public void setBdn_compliant(String bdn_compliant) {
		this.bdn_compliant = bdn_compliant;
	}

	public String getMarpol_sample_complaint() {
		return marpol_sample_complaint;
	}

	public void setMarpol_sample_complaint(String marpol_sample_complaint) {
		this.marpol_sample_complaint = marpol_sample_complaint;
	}

	public String getLop_issued() {
		return lop_issued;
	}

	public void setLop_issued(String lop_issued) {
		this.lop_issued = lop_issued;
	}

	public double getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(double total_quantity) {
		this.total_quantity = total_quantity;
	}

	public String getLop_issue_remark() {
		return lop_issue_remark;
	}

	public void setLop_issue_remark(String lop_issue_remark) {
		this.lop_issue_remark = lop_issue_remark;
	}

	public String getSample_sent_for_analysis() {
		return sample_sent_for_analysis;
	}

	public void setSample_sent_for_analysis(String sample_sent_for_analysis) {
		this.sample_sent_for_analysis = sample_sent_for_analysis;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSample_sent_date() {
		return sample_sent_date;
	}

	public void setSample_sent_date(String sample_sent_date) {
		this.sample_sent_date = sample_sent_date;
	}

	public String getFileNameForm() {
		return fileNameForm;
	}

	public void setFileNameForm(String fileNameForm) {
		this.fileNameForm = fileNameForm;
	}

	public String getFile_typeForm() {
		return file_typeForm;
	}

	public void setFile_typeForm(String file_typeForm) {
		this.file_typeForm = file_typeForm;
	}

	public double getFile_sizeForm() {
		return file_sizeForm;
	}

	public void setFile_sizeForm(double file_sizeForm) {
		this.file_sizeForm = file_sizeForm;
	}

	public String getIndependent_analysis_freetext() {
		return independent_analysis_freetext;
	}

	public void setIndependent_analysis_freetext(String independent_analysis_freetext) {
		this.independent_analysis_freetext = independent_analysis_freetext;
	}

	public String getDestroy_date() {
		return destroy_date;
	}

	public void setDestroy_date(String destroy_date) {
		this.destroy_date = destroy_date;
	}

		@Column(name = "independent_analysis_freetext", length = 150)
	private String independent_analysis_freetext;
	
	@Column(name = "destroy_date", length = 100)
	private String destroy_date;


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
	@Column(name = "entry_type", length = 50, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'BUNKER LOG'")
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
