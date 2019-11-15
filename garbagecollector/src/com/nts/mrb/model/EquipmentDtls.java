package com.nts.mrb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_details")
public class EquipmentDtls {
	@Id
	private int id;
	@Column(name="incinerator_maker",length=100)
	private String incinerator_maker;
	@Column(length=100)
	private String oliywater_maker;
	@Column(length=100)
	private String bilge_maker;
	@Column(length=100)
	private String incinerator_type;
	@Column(length=100)
	private String oliywater_type;
	@Column(length=100)
	private String bilge_type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIncinerator_maker() {
		return incinerator_maker;
	}

	public void setIncinerator_maker(String incinerator_maker) {
		this.incinerator_maker = incinerator_maker;
	}

	public String getOliywater_maker() {
		return oliywater_maker;
	}

	public void setOliywater_maker(String oliywater_maker) {
		this.oliywater_maker = oliywater_maker;
	}

	public String getBilge_maker() {
		return bilge_maker;
	}

	public void setBilge_maker(String bilge_maker) {
		this.bilge_maker = bilge_maker;
	}

	public String getIncinerator_type() {
		return incinerator_type;
	}

	public void setIncinerator_type(String incinerator_type) {
		this.incinerator_type = incinerator_type;
	}

	public String getOliywater_type() {
		return oliywater_type;
	}

	public void setOliywater_type(String oliywater_type) {
		this.oliywater_type = oliywater_type;
	}

	public String getBilge_type() {
		return bilge_type;
	}

	public void setBilge_type(String bilge_type) {
		this.bilge_type = bilge_type;
	}

	public Double getIncinerator_capacity() {
		return incinerator_capacity;
	}

	public void setIncinerator_capacity(Double incinerator_capacity) {
		this.incinerator_capacity = incinerator_capacity;
	}

	public Double getOliywater_capacity() {
		return oliywater_capacity;
	}

	public void setOliywater_capacity(Double oliywater_capacity) {
		this.oliywater_capacity = oliywater_capacity;
	}

	public Double getBilge_capacity() {
		return bilge_capacity;
	}

	public void setBilge_capacity(Double bilge_capacity) {
		this.bilge_capacity = bilge_capacity;
	}
	@Column(length=10)
	private Double incinerator_capacity;
	@Column(length=10)
	private Double oliywater_capacity;
	@Column(length=10)
	private Double bilge_capacity;
	private int plastic_burn_approval;

	public int getPlastic_burn_approval() {
		return plastic_burn_approval;
	}

	public void setPlastic_burn_approval(int plastic_burn_approval) {
		this.plastic_burn_approval = plastic_burn_approval;
	}
}
