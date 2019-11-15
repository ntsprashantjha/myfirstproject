package com.nts.grb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_details")
public class UserRegistration {
	@Column(name = "user_macId")
	private String usersys_macId;
	@Column(name = "user_status", length = 10, columnDefinition = "int default 0")
	private int userstatus;
	@Column(name = "user_isadmin", length = 10, columnDefinition = "int default 0")
	private int user_isadmin;

	public int getUser_isadmin() {
		return user_isadmin;
	}

	public void setUser_isadmin(int user_isadmin) {
		this.user_isadmin = user_isadmin;
	}

	public int getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}

	@Column(name = "user_activity", insertable = false, updatable = false, nullable = false, columnDefinition = "int default 0")
	private int user_activity;
	@Id
	@GeneratedValue
	@Column(name = "user_id", insertable = false, updatable = false, nullable = false)
	private int user_id;

	public String getUsersys_macId() {
		return usersys_macId;
	}

	public void setUsersys_macId(String usersys_macId) {
		this.usersys_macId = usersys_macId;
	}

	private int rank_id;

	public int getRank_id() {
		return rank_id;
	}

	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}

	@Column(name = "user_first_name")
	private String usrfrstName;
	@Column(name = "user_middle_name")
	private String usrmdlName;
	@Column(name = "user_last_name")
	private String usrlstName;
	@Column(name = "user_rank")
	private String usrRnk;
	@Temporal(TemporalType.DATE)
	@Column(name = "user_dob")
	private Date usrDOB;
	@Temporal(TemporalType.DATE)
	@Column(name = "user_joindate")
	private Date usrJoinDt;
	@Temporal(TemporalType.DATE)
	@Column(name = "user_leavedate")
	private Date usrLeaveDt;
	@Column(name = "emp_id")
	private String usrID;
	@Column(name = "user_country")
	private String country;
	@Column(name = "user_password")
	private String psw;

	@Override
	public String toString() {
		return "UserRegistration [usrfrstName=" + usrfrstName + ", usrmdlName=" + usrmdlName + ", usrlstName="
				+ usrlstName + ", usrRnk=" + usrRnk + ", usrDOB=" + usrDOB + ", usrJoinDt=" + usrJoinDt
				+ ", usrLeaveDt=" + usrLeaveDt + ", usrID=" + usrID + ", country=" + country + ", psw=" + psw
				+ ", cnfrmPsw=" + cnfrmPsw + ", seamanBook=" + seamanBook + "]";
	}

	@Column(name = "user_confirm_pasword")
	private String cnfrmPsw;
	@Column(name = "user_seamanBook")
	private String seamanBook;

	public String getUsrfrstName() {
		return usrfrstName;
	}

	public void setUsrfrstName(String usrfrstName) {
		this.usrfrstName = usrfrstName;
	}

	public String getUsrmdlName() {
		return usrmdlName;
	}

	public void setUsrmdlName(String usrmdlName) {
		this.usrmdlName = usrmdlName;
	}

	public String getUsrlstName() {
		return usrlstName;
	}

	public void setUsrlstName(String usrlstName) {
		this.usrlstName = usrlstName;
	}

	public String getUsrRnk() {
		return usrRnk;
	}

	public void setUsrRnk(String usrRnk) {
		this.usrRnk = usrRnk;
	}

	public Date getUsrDOB() {
		return usrDOB;
	}

	public void setUsrDOB(Date usrDOB) {
		this.usrDOB = usrDOB;
	}

	public Date getUsrJoinDt() {
		return usrJoinDt;
	}

	public void setUsrJoinDt(Date usrJoinDt) {
		this.usrJoinDt = usrJoinDt;
	}

	public Date getUsrLeaveDt() {
		return usrLeaveDt;
	}

	public void setUsrLeaveDt(Date usrLeaveDt) {
		this.usrLeaveDt = usrLeaveDt;
	}

	public String getusrID() {
		return usrID;
	}

	public void setusrID(String usrID) {
		this.usrID = usrID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getCnfrmPsw() {
		return cnfrmPsw;
	}

	public void setCnfrmPsw(String cnfrmPsw) {
		this.cnfrmPsw = cnfrmPsw;
	}

	public String getSeamanBook() {
		return seamanBook;
	}

	public void setSeamanBook(String seamanBook) {
		this.seamanBook = seamanBook;
	}

}
