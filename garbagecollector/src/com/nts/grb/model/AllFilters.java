package com.nts.grb.model;

import java.util.Date;

public class AllFilters {

	String entrytype, category, order, status;
	String fordate, todate, tablename1, tablename2;
	int statusvalue;
	Date forDate,toDate;

	public String getTablename2() {
		return tablename2;
	}

	public void setTablename2(String tablename2) {
		this.tablename2 = tablename2;
	}

	public int getStatusvalue() {
		return statusvalue;
	}

	public void setStatusvalue(int statusvalue) {
		this.statusvalue = statusvalue;
	}

	public String getTablename1() {
		return tablename1;
	}

	public void setTablename1(String tablename1) {
		this.tablename1 = tablename1;
	}

	public String getFordate() {
		return fordate;
	}

	public void setFordate(String fordate) {
		this.fordate = fordate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getEntrytype() {
		return entrytype;
	}

	public void setEntrytype(String entrytype) {
		this.entrytype = entrytype;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}