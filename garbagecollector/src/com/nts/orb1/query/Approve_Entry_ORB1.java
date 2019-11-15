package com.nts.orb1.query;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;
import com.nts.orb1.service.GetTableName;

public class Approve_Entry_ORB1 {

	private static Connection con = null;
	private static Statement stmt = null;

	public static JSONArray approveUpdate(String tblename, int entryId, int activeuserid, String approvecomment,
			String crtdt) {

		con = dbConnection.getConnection();

		try {

			stmt = (Statement) con.createStatement();
			System.out.println("tblename" + tblename);
			String tablename = GetTableName.getDbTableName(tblename);
			System.out.println("DbtableName" + tablename);

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

	public static JSONArray reapproveUpdate(String tblename, int entryId, int activeuserid, String approvecomment,
			String datetime) {

		con = dbConnection.getConnection();

		try {

			stmt = (Statement) con.createStatement();
			System.out.println("tblename" + tblename);
			String tablename = GetTableName.getDbTableName(tblename);
			System.out.println("DbtableName" + tablename);

			String sql = "update " + tablename + " set " + tablename + ".master_reapproval_done=0," + tablename
					+ ".master_reapproval='Done'," + tablename + ".master_retime='" + datetime + "'," + tablename
					+ ".master_reid=" + activeuserid + "," + tablename + ".master_recomment='" + approvecomment
					+ "' where entry_Id=" + entryId;

			System.out.println(sql);
			stmt.executeUpdate(sql);

		}

		catch (SQLException e) {
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong();

		}

		return ReturnResponse.success_condition();

	}
}
