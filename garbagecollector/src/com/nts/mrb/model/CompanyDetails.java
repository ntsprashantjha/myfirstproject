package com.nts.mrb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "company_details")
@Audited
@EntityListeners(AuditClass.class)
public class CompanyDetails {
	@Id
 	private int id;
 
 	
 	public int getComp_status() {
 		return comp_status;
 	}
 
 	public void setComp_status(int comp_status) {
 		this.comp_status = comp_status;
 	}
 
 	
 	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}


	@Column(name = "comp_status", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
 	private int comp_status;
	private String owner_company_address;
	private String owner_company_name;
	private String operator_company_name;

	public String getOwner_company_address() {
		return owner_company_address;
	}

	public void setOwner_company_address(String owner_company_address) {
		this.owner_company_address = owner_company_address;
	}

	public String getOwner_company_name() {
		return owner_company_name;
	}

	public void setOwner_company_name(String owner_company_name) {
		this.owner_company_name = owner_company_name;
	}

	public String getOperator_company_name() {
		return operator_company_name;
	}

	public void setOperator_company_name(String operator_company_name) {
		this.operator_company_name = operator_company_name;
	}

	public String getOperator_company_address() {
		return operator_company_address;
	}

	public void setOperator_company_address(String operator_company_address) {
		this.operator_company_address = operator_company_address;
	}

	private String operator_company_address;

}
