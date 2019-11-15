package com.nts.mrb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="mrb_access_rights")
@Audited
@EntityListeners(AuditClass.class)
public class AccessRight {
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rank_id")
	private int rank_id;
	private String mrb_control;
	private String software_access_control;
	private String password_reset_access_control;
	private String add_edit_user;
	private String create_rank;
	private String sign_of_user;
	private String back_up_rights;
	private String edit_general_setting;

}
