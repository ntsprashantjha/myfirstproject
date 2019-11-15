package com.nts.mrb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refrigerator_equip_details_mrb")
public class AddRefrigeratorEquipMrb {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "equipment_name", length = 50)
	private String equipment_name;
	@Column(name = "equipment_location", length = 50)
	private String equipment_location;
	@Column(name = "manufacturer", length = 50)
	private String manufacturer;
	@Column(name = "referigerant_type", length = 50)
	private String referigerant_type;
	@Column(name = "quantity", length = 10)
	private double quantity;
	@Column(name = "year_of_installation", length = 50)
	private String year_of_installation;
	@Column(name = "status", length = 1, insertable = false, updatable = false, nullable = false, columnDefinition = "int(1) default '0'")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getEquipment_location() {
		return equipment_location;
	}

	public void setEquipment_location(String equipment_location) {
		this.equipment_location = equipment_location;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getReferigerant_type() {
		return referigerant_type;
	}

	public void setReferigerant_type(String referigerant_type) {
		this.referigerant_type = referigerant_type;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getYear_of_installation() {
		return year_of_installation;
	}

	public void setYear_of_installation(String year_of_installation) {
		this.year_of_installation = year_of_installation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
