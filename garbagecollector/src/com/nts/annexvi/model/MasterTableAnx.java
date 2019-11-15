package com.nts.annexvi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "master_table_annexvi")
public class MasterTableAnx {
	
	@Id
	@GeneratedValue
	private int entry_id;
	
	@Column(name = "entry_type", length = 100)
	private String entry_type;
	
	@Column(name = "entry_date", length = 30)
	private String entry_date;
	
	@Column(name = "entry_date_only", length = 10)
	private String entry_date_only;
	
	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}

	public String getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(String entry_type) {
		this.entry_type = entry_type;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

	public String getEntry_date_only() {
		return entry_date_only;
	}

	public void setEntry_date_only(String entry_date_only) {
		this.entry_date_only = entry_date_only;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public int getEntry_strike_cond() {
		return entry_strike_cond;
	}

	public void setEntry_strike_cond(int entry_strike_cond) {
		this.entry_strike_cond = entry_strike_cond;
	}

	@Column(name = "timezone", length = 10)
	private String timezone;

	@Column(name = "entry_strike_cond", length = 5, insertable = false, updatable = false, nullable = false, columnDefinition = "int(5) default '0'")
	private int entry_strike_cond;
	
	

	
	

}
