package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

public class ReApprovalEntry {
	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;

	public static JSONArray reapproveUpdate(String tablename, int entryId, int activeuserid, String approvecomment,
			String datetime) {

		con = dbConnection.getConnection();

		try {

			stmt = (Statement) con.createStatement();

			String sql = "update " + tablename + " set " + tablename + ".master_reapproval_done=0," + tablename
					+ ".master_reapproval='Done'," + tablename + ".master_retime='" + datetime + "'," + tablename
					+ ".master_reid=" + activeuserid + "," + tablename + ".master_recomment='" + approvecomment
					+ "' where entry_Id=" + entryId;

			System.out.println(sql + "----:query");
			stmt.executeUpdate(sql);

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong();

		}

		return ReturnResponse.success_condition();

	}

}
