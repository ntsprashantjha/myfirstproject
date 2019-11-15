package com.nts.grb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class GetStopEntry {
	private String rprtData;
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static int rt;
	private static String tablename = "stop_incineration";
	private int activeuserid;
	private String strikecomment, currntDt;

	public void getEntry(int entryId, int activeuserid, String strikecomment, String currntDt, int approvalcondition)
			throws SQLException {
		this.strikecomment = strikecomment;
		this.activeuserid = activeuserid;
		con = dbConnection.getConnection();
		String sql = "SELECT entry_id from master_table WHERE entry_type LIKE '%stop_incineration' AND entry_id>"
				+ entryId + "LIMIT 1";
		System.out.println("******from lastEntryDetails*******" + sql);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			rt = rs.getInt("entry_id");
		}
		updateStop(rt);
	}

	public void updateStop(int stopEnrtyId) throws SQLException {
		stmt = (Statement) con.createStatement();
		String sql = "update " + tablename + ",master_table set " + tablename + ".strike_Value=1," + tablename
				+ ".strikeApprovalDone=1," + tablename + ".strikeApproval='Done'," + tablename + ".strikeTime='"
				+ currntDt + "'," + tablename + ".strikeid=" + activeuserid + "," + tablename + ".strikeComment='"
				+ strikecomment + "',master_table.incineration_condition=1,master_table.entry_strike_cond=1 where "
				+ tablename + ".id=master_table.entry_id AND " + tablename + ".entry_id=" + stopEnrtyId;
		System.out.println(sql + "----:query");
		stmt.executeUpdate(sql);
	}

}
