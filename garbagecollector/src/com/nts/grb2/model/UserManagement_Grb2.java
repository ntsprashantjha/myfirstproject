package com.nts.grb2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_management_Grb2")
public class UserManagement_Grb2 {
	@Column(name="entry_timestamp",length=10)
	private String entry_timeZone;

	public String getEntry_timeZone() {
		return entry_timeZone;
	}

	public void setEntry_timeZone(String entry_timeZone) {
		this.entry_timeZone = entry_timeZone;
			
	}
	@Id
	@GeneratedValue
	private int rank_id;
	public int getRank_id() {
		return rank_id;
	}

	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}
	@Column(name="make_strike_entry",length= 5)
	private String make_entry;
	
	@Column(name="data_entry_setting",length=5)
	private String edit_grb_sit ;
	
	@Column(name="final_approval",length=5)
	private String final_approval ;
	
	@Column(name="digi_bio_sign",length=5)
	private String digi_bio_sign;
	
	@Column(name="grb_log_access",length=5)
	private String grb_log_access;
	
	@Column(name="edit_rbac",length=5)
	private String report_view_access;
	
	@Column(name="add_remove_circular",length=5)
	private String circular_access;
	
	@Column(name="rank",length=5)
	private String rank;
	public String getMake_entry() {
		return make_entry;
	}

	public void setMake_entry(String make_entry) {
		this.make_entry = make_entry;
	}
	public String getEdit_grb_sit() {
		return edit_grb_sit;
	}

	public void setEdit_grb_sit(String edit_grb_sit) {
		this.edit_grb_sit = edit_grb_sit;
	}

	public String getFinal_approval() {
		return final_approval;
	}

	public void setFinal_approval(String final_approval) {
		this.final_approval = final_approval;
	}

	public String getDigi_bio_sign() {
		return digi_bio_sign;
	}

	public void setDigi_bio_sign(String digi_bio_sign) {
		this.digi_bio_sign = digi_bio_sign;
	}

	public String getGrb_log_access() {
		return grb_log_access;
	}

	public void setGrb_log_access(String grb_log_access) {
		this.grb_log_access = grb_log_access;
	}

	public String getReport_view_access() {
		return report_view_access;
	}

	public void setReport_view_access(String report_view_access) {
		this.report_view_access = report_view_access;
	}

	public String getCircular_access() {
		return circular_access;
	}

	public void setCircular_access(String circular_access) {
		this.circular_access = circular_access;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
