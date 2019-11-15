package com.nts.mrb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "mrb_access_rights")
@Audited
@EntityListeners(AuditClass.class)
public class AddNewRankndAccess {
	@Id
	private int rank_id;
	private String mrb_control;
	private String software_access_control;
	private String password_reset_access_control;
	private String add_edit_user;
	private String create_rank;
	private String sign_of_user;
	public int getRank_id() {
		return rank_id;
	}
	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}
	public String getMrb_control() {
		return mrb_control;
	}
	public void setMrb_control(String mrb_control) {
		this.mrb_control = mrb_control;
	}
	public String getSoftware_access_control() {
		return software_access_control;
	}
	public void setSoftware_access_control(String software_access_control) {
		this.software_access_control = software_access_control;
	}
	public String getPassword_reset_access_control() {
		return password_reset_access_control;
	}
	public void setPassword_reset_access_control(String password_reset_access_control) {
		this.password_reset_access_control = password_reset_access_control;
	}
	public String getAdd_edit_user() {
		return add_edit_user;
	}
	public void setAdd_edit_user(String add_edit_user) {
		this.add_edit_user = add_edit_user;
	}
	public String getCreate_rank() {
		return create_rank;
	}
	public void setCreate_rank(String create_rank) {
		this.create_rank = create_rank;
	}
	public String getSign_of_user() {
		return sign_of_user;
	}
	public void setSign_of_user(String sign_of_user) {
		this.sign_of_user = sign_of_user;
	}
	public String getBack_up_rights() {
		return back_up_rights;
	}
	public void setBack_up_rights(String back_up_rights) {
		this.back_up_rights = back_up_rights;
	}
	public String getEdit_general_setting() {
		return edit_general_setting;
	}
	public void setEdit_general_setting(String edit_general_setting) {
		this.edit_general_setting = edit_general_setting;
	}
	private String back_up_rights;
	private String edit_general_setting;
	@Column(name="user_status",length=10,columnDefinition = "int default 0")
	private int isDeletable;
	public int getisDeletable() {
		return isDeletable;
	}
	public void setisDeletable(int isDeletable) {
		this.isDeletable = isDeletable;
	}

}
