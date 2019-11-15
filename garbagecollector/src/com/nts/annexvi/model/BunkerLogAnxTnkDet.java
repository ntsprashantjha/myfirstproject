package com.nts.annexvi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bunker_log_anx_tnkdetails")
public class BunkerLogAnxTnkDet {

	// ********* FrontEnd Columns

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "qty_added", length = 10)
	private double qty_added;
	
	public String getTank_name() {
		return tank_name;
	}

	public void setTank_name(String tank_name) {
		this.tank_name = tank_name;
	}

	public double getQty_added() {
		return qty_added;
	}

	public void setQty_added(double qty_added) {
		this.qty_added = qty_added;
	}

	@Column(name = "tank_name", length = 30)
	private String tank_name;
	
	@Column(name = "source_entry_id", length = 10)
	private double source_entry_id;

	public double getSource_entry_id() {
		return source_entry_id;
	}

	public void setSource_entry_id(double source_entry_id) {
		this.source_entry_id = source_entry_id;
	}
	

	
}
