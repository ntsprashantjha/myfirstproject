package com.nts.orb1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "de_bunkring_of_fuel_oil_tnk_details")
public class DeBunkringOfFuelOilTnkDet {

	// ********* FrontEnd Columns
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "tank_name", length = 100)
	private String tank_name;
	@Column(name = "qty_added", length = 10)
	private double qty_added;

	@Column(name = "qty_retained", length = 10)
	private double qty_retained;
	@Column(name = "source_entryid", length = 10)
	private double source_entryid;

	public int getid() {
		return id;
	}

	public void setid(int e_id) {
		this.id = e_id;
	}

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

	public double getQty_retained() {
		return qty_retained;
	}

	public void setQty_retained(double qty_retained) {
		this.qty_retained = qty_retained;
	}

	public double getSource_entryid() {
		return source_entryid;
	}

	public void setSource_entryid(double source_entryid) {
		this.source_entryid = source_entryid;
	}

}
