package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class ApprovalCondtionCheck {
	private String rprtData;
	private static Connection con = null;
	private Statement stmt = null;
	private static ResultSet rs = null;
	static int appcond = 0;
	public static int approvalCondtion(String tableName, int idd) throws SQLException {
		con = dbConnection.getConnection();
		String sql = "SELECT master_approval_done FROM " + tableName + " where entry_id=" + idd;
		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			appcond = rs.getInt("master_approval_done");
		}
		System.out.println("ijoijf" + appcond);
		return appcond;
	}

	public static void main(String arg[]) throws SQLException {
		System.out.println(ApprovalCondtionCheck.approvalCondtion("discharge_to_sea", 15));
	}

}
