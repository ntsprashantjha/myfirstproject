package com.nts.grb2.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

public class Approve_Entry_GRB2 {

	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;

	public static JSONArray approveUpdate(String tablename, int entryId, int activeuserid, String approvecomment,
			String crtdt) {

		con = dbConnection.getConnection();

		try {

			stmt = (Statement) con.createStatement();

			String sql = "update " + tablename + " set " + tablename + ".master_approval_done=1," + tablename
					+ ".master_approval_done=1," + tablename + ".master_time='" + crtdt + "'," + tablename
					+ ".master_approval='Done'," + tablename + ".master_id=" + activeuserid + "," + tablename
					+ ".master_comment='" + approvecomment + "' where entry_Id=" + entryId;

			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong();
		}
		return ReturnResponse.success_condition();

	}

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
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong();

		}

		return ReturnResponse.success_condition();

	}
}
