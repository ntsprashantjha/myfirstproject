package com.nts.mrb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vessel_details")
public class VesselDetails {
	@Id
	private int id;

	public int getComp_status() {
		return vessel_status;
	}

	public void setComp_status(int comp_status) {
		this.vessel_status = comp_status;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public int getVessel_status() {
		return vessel_status;
	}

	public void setVessel_status(int vessel_status) {
		this.vessel_status = vessel_status;
	}

	@Column(name = "vessel_status", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
	private int vessel_status;
	@Column(name = "vessel_name", length = 50)
	private String vessel_name;
	@Column(name = "vessel_type", length = 50)
	private String vessel_type;
	@Column(name = "length_bw_perpandicular", length = 50)
	private float length_bw_perpandicular;
	@Column(name = "moduled_depth", length = 50)
	private float moduled_depth;
	@Column(name = "call_sign", length = 50)
	private String call_sign;
	@Column(name = "flag", length = 50)
	private String flag;
	@Column(name = "gross_tonnage", length = 50)
	private float gross_tonnage;
	@Column(name = "hull_no", length = 50)
	private int hull_no;
	@Column(name = "ship_yard", length = 50)
	private float ship_yard;

	public String getVessel_name() {
		return vessel_name;
	}

	public void setVessel_name(String vessel_name) {
		this.vessel_name = vessel_name;
	}

	public String getVessel_type() {
		return vessel_type;
	}

	public void setVessel_type(String vessel_type) {
		this.vessel_type = vessel_type;
	}

	public float getLength_bw_perpandicular() {
		return length_bw_perpandicular;
	}

	public void setLength_bw_perpandicular(float length_bw_perpandicular) {
		this.length_bw_perpandicular = length_bw_perpandicular;
	}

	public float getModuled_depth() {
		return moduled_depth;
	}

	public void setModuled_depth(float moduled_depth) {
		this.moduled_depth = moduled_depth;
	}

	public String getCall_sign() {
		return call_sign;
	}

	public void setCall_sign(String call_sign) {
		this.call_sign = call_sign;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public float getGross_tonnage() {
		return gross_tonnage;
	}

	public void setGross_tonnage(float gross_tonnage) {
		this.gross_tonnage = gross_tonnage;
	}

	public int getHull_no() {
		return hull_no;
	}

	public void setHull_no(int hull_no) {
		this.hull_no = hull_no;
	}

	public float getShip_yard() {
		return ship_yard;
	}

	public void setShip_yard(float ship_yard) {
		this.ship_yard = ship_yard;
	}

	public float getLength_overall() {
		return length_overall;
	}

	public void setLength_overall(float length_overall) {
		this.length_overall = length_overall;
	}

	public float getModuled_breath() {
		return moduled_breath;
	}

	public void setModuled_breath(float moduled_breath) {
		this.moduled_breath = moduled_breath;
	}

	public float getModuled_draft() {
		return moduled_draft;
	}

	public void setModuled_draft(float moduled_draft) {
		this.moduled_draft = moduled_draft;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getPort_of_registry() {
		return port_of_registry;
	}

	public void setPort_of_registry(String port_of_registry) {
		this.port_of_registry = port_of_registry;
	}

	public String getDwt() {
		return dwt;
	}

	public void setDwt(String dwt) {
		this.dwt = dwt;
	}

	public String getLoa() {
		return loa;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate_delivered() {
		return Date_delivered;
	}

	public void setDate_delivered(String date_delivered) {
		Date_delivered = date_delivered;
	}

	public void setLoa(String loa) {
		this.loa = loa;
	}

	@Column(name = "date_delivered", length = 50)
	private String Date_delivered;
	@Column(name = "vessel_imo", length = 50)
	private String vessel_imo;

	public String getVessel_imo() {
		return vessel_imo;
	}

	public void setVessel_imo(String vessel_imo) {
		this.vessel_imo = vessel_imo;
	}

	@Column(name = "date_keel_laid", length = 50)
	private String date_keel_laid;
	@Column(name = "length_over_all", length = 50)
	private float length_overall;

	

	public String getDate_keel_laid() {
		return date_keel_laid;
	}

	public void setDate_keel_laid(String date_keel_laid) {
		this.date_keel_laid = date_keel_laid;
	}

	@Column(name = "moduled_breath", length = 50)
	private float moduled_breath;
	@Column(name = "incinerator_capacity", length = 50)
	private float incinerator_capacity;

	public float getIncinerator_capacity() {
		return incinerator_capacity;
	}

	public void setIncinerator_capacity(float incinerator_capacity) {
		this.incinerator_capacity = incinerator_capacity;
	}

	@Column(name = "moduled_draft", length = 50)
	private float moduled_draft;
	@Column(name = "class", length = 50)
	private String classes;
	@Column(name = "port_of_registry", length = 50)
	private String port_of_registry;
	@Column(name = "dwt", length = 50)
	private String dwt;
	@Column(name = "loa", length = 50)
	private String loa;

}