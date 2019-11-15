package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

import testing.QueryTsting;

public class ApprovalEntry {
	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;
	static String[] tablesName = {"discharge_to_ashore", "start_incineration", "start_incineration_acc",
			"discharge_to_sea_acc" };

	public static JSONArray approveUpdate(String tablename, int entryId, int activeuserid, String approvecomment,
			String crtdt) {
		
		con = dbConnection.getConnection();
		
		try {
			
			stmt = (Statement) con.createStatement();
		
		String sql = "update " + tablename + " set " + tablename + ".master_approval_done=1," + tablename
				+ ".master_approval_done=1," + tablename + ".master_time='" + crtdt + "'," + tablename
				+ ".master_approval='Done'," + tablename + ".master_id=" + activeuserid + "," + tablename
				+ ".master_comment='" + approvecomment + "' where entry_Id=" + entryId;
		
		System.out.println("Query = " + sql);
		
		stmt.executeUpdate(sql);
		
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong();
			
		}
		
		return ReturnResponse.success_condition();

	}

	public static int approveUpdate1(String tablename, int entryId, int activeuserid, String approvecomment,
			String crtdt) throws SQLException {
		
		con = dbConnection.getConnection();
		stmt = (Statement) con.createStatement();
		
		String sql = "update " + tablename + "," + tablesName[0] + "," + tablesName[1] + "," + tablesName[2] + ","
				+ tablesName[3] + " set " + tablename + ".master_approval_done=1," + tablename
				+ ".master_approval_done=1," + tablename + ".master_time='" + crtdt + "'," + tablename
				+ ".master_approval='Done'," + tablename + ".master_id=" + activeuserid + "," + tablename
				+ ".master_comment='" + approvecomment + "'," + tablesName[0] + ".master_approval_done=1,"
				+ tablesName[0] + ".master_approval_done=1," + tablesName[0] + ".master_time='" + crtdt + "',"
				+ tablesName[0] + ".master_approval='Done'," + tablesName[0] + ".master_id=" + activeuserid + ","
				+ tablesName[0] + ".master_comment='" + approvecomment + "'," + tablesName[1]
				+ ".master_approval_done=1," + tablesName[1] + ".master_approval_done=1," + tablesName[1]
				+ ".master_time='" + crtdt + "'," + tablesName[1] + ".master_approval='Done'," + tablesName[1]
				+ ".master_id=" + activeuserid + "," + tablesName[1] + ".master_comment='" + approvecomment + "',"
				+ tablesName[2] + ".master_approval_done=1," + tablesName[2] + ".master_approval_done=1,"
				+ tablesName[2] + ".master_time='" + crtdt + "'," + tablesName[2] + ".master_approval='Done',"
				+ tablesName[2] + ".master_id=" + activeuserid + "," + tablesName[2] + ".master_comment='"
				+ approvecomment + "' where " + tablename + ".entry_Id<=" + entryId;
		
		System.out.println(sql + "----:query");
		return stmt.executeUpdate(sql);

	}

	/*
	 * public static void main(String arg[]) throws SQLException {
	 * QueryTsting.approveUpdate1("discharge_to_sea", 22, 1, "pl",
	 * "2019-05-06 14:22:04"); }
	 */
}
