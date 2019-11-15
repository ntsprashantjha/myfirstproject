package com.nts.grb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.nts.mrb.model.AuditClass;

@Entity
@Table(name = "start_Incineration")
@Audited
@EntityListeners(AuditClass.class)
public class StartINcineration {
	// <----comman and entry variable for every entry------->
	@Column(name = "entry_type", length = 50,insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'start_Incineration'")
	private String entry_type;
	@Column(name = "entry_Id", length = 10)
	private int entry_id;
	private String attached_file_type;
	private int attached_file_size;

	public String getAttached_file_type() {
		return attached_file_type;
	}

	public void setAttached_file_type(String attached_file_type) {
		this.attached_file_type = attached_file_type;
	}

	public int getAttached_file_size() {
		return attached_file_size;
	}

	public void setAttached_file_size(int attached_file_size) {
		this.attached_file_size = attached_file_size;
	}

	public int getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}
	@Column(name = "entry_timezone",length=20)
	private String entry_timezone;

	public String getEntry_timezone() {
		return entry_timezone;
	}

	public void setEntry_timezone(String entry_timezone) {
		this.entry_timezone = entry_timezone;
	}
		@UpdateTimestamp
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "entry_timestamp",length=20)
		private Date entry_time_date;
		@Column(name = "long_side",length=20)
		private String ship_pos_longitude_loc;
		@Column(name = "left_side",length=20)
		private String ship_pos_latitude_loc;
		@Column(name = "officer_id",length=10)
		private int officer_id;
		@Temporal(TemporalType.DATE)
		@Column(name = "entry_date",length=10)
		private Date entry_date;
		@Column(name = "entry_time",length=20)
		private String entry_time;
		@Column(name = "garbage_category",length=150)
		private String garbage_category;
		@Column(name = "entry_remark",length=255)
		private String remark;
		@Column(name = "lat_deg",length=10)
		private Double ship_pos_latitude_degree;
		@Column(name = "long_deg",length=10)
		private Double ship_pos_longitude_degree;
		public int getMasterId() {
			return masterId;
		}

		public void setMasterId(int masterId) {
			this.masterId = masterId;
		}

		public int getStrikeId() {
			return strikeId;
		}

		public void setStrikeId(int strikeId) {
			this.strikeId = strikeId;
		}

		public int getAmendId() {
			return amendId;
		}

		public void setAmendId(int amendId) {
			this.amendId = amendId;
		}

		public int getMasterReId() {
			return masterReId;
		}

		public void setMasterReId(int masterReId) {
			this.masterReId = masterReId;
		}
		@Column(name = "lat_min",length=10)
		private Double ship_pos_latitude_min;
		public Date getEntry_time_date() {
			return entry_time_date;
		}
		public void setEntry_time_date(Date entry_time_date) {
			this.entry_time_date = entry_time_date;
		}
		public String getShip_pos_longitude_loc() {
			return ship_pos_longitude_loc;
		}
		public void setShip_pos_longitude_loc(String ship_pos_longitude_loc) {
			this.ship_pos_longitude_loc = ship_pos_longitude_loc;
		}
		public String getShip_pos_latitude_loc() {
			return ship_pos_latitude_loc;
		}
		public void setShip_pos_latitude_loc(String ship_pos_latitude_loc) {
			this.ship_pos_latitude_loc = ship_pos_latitude_loc;
		}
		public int getOfficer_id() {
			return officer_id;
		}
		public void setOfficer_id(int officer_id) {
			this.officer_id = officer_id;
		}
		public Date getEntry_date() {
			return entry_date;
		}
		public void setEntry_date(Date entry_date) {
			this.entry_date = entry_date;
		}
		public String getEntry_time() {
			return entry_time;
		}
		public void setEntry_time(String entry_time) {
			this.entry_time = entry_time;
		}
		public String getGarbage_category() {
			return garbage_category;
		}
		public void setGarbage_category(String garbage_category) {
			this.garbage_category = garbage_category;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Double getShip_pos_latitude_degree() {
			return ship_pos_latitude_degree;
		}
		public void setShip_pos_latitude_degree(Double ship_pos_latitude_degree) {
			this.ship_pos_latitude_degree = ship_pos_latitude_degree;
		}
		public Double getShip_pos_longitude_degree() {
			return ship_pos_longitude_degree;
		}
		public void setShip_pos_longitude_degree(Double ship_pos_longitude_degree) {
			this.ship_pos_longitude_degree = ship_pos_longitude_degree;
		}
		public Double getShip_pos_latitude_min() {
			return ship_pos_latitude_min;
		}
		public void setShip_pos_latitude_min(Double ship_pos_latitude_min) {
			this.ship_pos_latitude_min = ship_pos_latitude_min;
		}
		public Double getShip_pos_longitude_min() {
			return ship_pos_longitude_min;
		}
		public void setShip_pos_longitude_min(Double ship_pos_longitude_min) {
			this.ship_pos_longitude_min = ship_pos_longitude_min;
		}
		public String getFile_name() {
			return file_name;
		}
		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}
		public String getIncineration_remark() {
			return incineration_remark;
		}
		public void setIncineration_remark(String incineration_remark) {
			this.incineration_remark = incineration_remark;
		}
		@Column(name = "long_min",length=10)
		private Double ship_pos_longitude_min;
		@Column(name = "view_file_path", columnDefinition = "longtext")
		private String file_name;
	//---------different variable from other enrty------
		@Column(name="incineration_remark",length=255)
		private String incineration_remark;
	//*****comn vrible for evry entry**********
		@Id
		@GeneratedValue
		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		/*
		 * private String estimatedAmountDischargedtoSea; private String
		 * estimatedAmountDischargedtoAshore;
		 */
		@Column(name = "master_approval", insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
		private String mstrAprvl;
		@Column(name = "amend_time",length=20)
		private String amend_time;
		@Column(name = "master_approval_done",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "int(2) default 0")
		private int mstrAprvlCond;
		@Column(name = "master_id",length=10)
	    private int masterId;  
		@Column(name = "master_comment",length=250)
	    private String masterComment;  
		public String getMasterComment() {
			return masterComment;
		}

		public void setMasterComment(String masterComment) {
			this.masterComment = masterComment;
		}

		public String getStrikeComment() {
			return strikeComment;
		}

		public void setStrikeComment(String strikeComment) {
			this.strikeComment = strikeComment;
		}

		public String getAmendComment() {
			return amendComment;
		}

		public void setAmendComment(String amendComment) {
			this.amendComment = amendComment;
		}
		@Column(name = "master_time",length=20)
		private String mstrAprvlTime;
		@Column(name = "strike_value",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "int(10) default 0")
		private int strikeValue;
		@Column(name = "strike_approval",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
		private String strikeAprvl;
		@Column(name = "strike_approval_done",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "int(2) default 0")
		private Boolean strikeAprvlCond;
		@Column(name = "strike_id",length=10)
	    private int strikeId; 
		@Column(name = "strike_comment",length=250)
	    private String strikeComment;  
		@Column(name = "strike_time",length=20)
		private String strikeTime;
		@Column(name = "amend_approval",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Allow'")
		private String amendAprvl;
		@Column(name = "amend_approval_done",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "int(2) default 0")
		private Boolean amendAprvlCond;
		@Column(name = "amend_id",length=10)
	    private int amendId;  
		@Column(name = "amend_comment",length=250)
	    private String amendComment;  
		@Column(name = "master_reapproval",length=10, insertable = false, updatable = false, nullable = false, columnDefinition = "varchar(10) default 'Pending'")
		private String mstrReAprvl;
		@Column(name = "master_reapproval_done", length = 10, insertable = false, updatable = false, nullable = false, columnDefinition = "int default 1")
		private int mstrReAprvlCond;
		@Column(name = "master_reid",length=10)
	    private int masterReId; 
		@Column(name = "master_recomment", length = 250)
	     private String masterReComment;
		public String getMasterReComment() {
			return masterReComment;
		}

		public void setMasterReComment(String masterReComment) {
			this.masterReComment = masterReComment;
		}
		@Column(name = "master_retime",length=20)
		private String masterReTime;
		@Column(name = "comment",length=10)
        private String cmnt;


}