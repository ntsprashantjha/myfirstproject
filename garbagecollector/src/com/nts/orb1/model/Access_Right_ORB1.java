package com.nts.orb1.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_management_orb1")
public class Access_Right_ORB1 {
	
	@Column(name = "entry_timezone", length = 10)
	private String enrty_timeZone;

	public String getEnrty_timeZone() {
		return enrty_timeZone;
	}

	public void setEnrty_timeZone(String enrty_timeZone) {
		this.enrty_timeZone = enrty_timeZone;
	}

	@Id
	@GeneratedValue
	private int rank_id;

	public int getId() {
		return rank_id;
	}

	public void setId(int rank_id) {
		this.rank_id = rank_id;
	}

	@Column(name = "make_strike_entry", length = 5)
	private String make_strike_entry;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_timestamp", length = 5)
	private java.util.Date entry_time_date;
	@Column(name = "edit_rbac", length = 5)
	private String edit_rbac;
	@Column(name = "final_approval", length = 5)
	private String final_approval;
	@Column(name = "data_entry_setting", length = 5)
	private String data_entry_setting;
	@Column(name = "digi_bio_sign", length = 5)
	private String digi_bio_sign;
	@Column(name = "grb_log_access", length = 5)
	private String grb_log_access;
	@Column(name = "add_remove_circular", length = 5)
	private String add_remove_circular;
	@Column(name = "rank", length = 50)
	private String rank;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getmake_strike_entry() {
		return make_strike_entry;
	}

	public void setmake_strike_entry(String make_strike_entry) {
		this.make_strike_entry = make_strike_entry;
	}

	public String getEdit_rbac() {
		return edit_rbac;
	}

	public void setEdit_rbac(String edit_rbac) {
		this.edit_rbac = edit_rbac;
	}

	public String getFinal_approval() {
		return final_approval;
	}

	public void setFinal_approval(String final_approval) {
		this.final_approval = final_approval;
	}

	public String getData_entry_setting() {
		return data_entry_setting;
	}

	public void setData_entry_setting(String data_entry_setting) {
		this.data_entry_setting = data_entry_setting;
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

	public String getadd_remove_circular() {
		return add_remove_circular;
	}

	public void setadd_remove_circular(String add_remove_circular) {
		this.add_remove_circular = add_remove_circular;
	}

}
