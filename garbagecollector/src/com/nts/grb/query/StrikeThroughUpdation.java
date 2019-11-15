package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

public class StrikeThroughUpdation {
	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;
	private static int rt;

	public static JSONArray strikeUpdate(String tablename, int entryId, int activeuserid, String strikecomment,
			String currntDt, int approvalcondition) {

		JSONArray return_stmt = new JSONArray();
		return_stmt = ReturnResponse.success_condition();

		System.out.println("entryytpoe:----" + tablename + "" + approvalcondition);

		if (tablename.equals("stop_incineration")) {

			System.out.println("***********************&&&&&&&&&&& from stop condition &&&*&*&*&*&*&*&*&*&*&*&*&**&");

			try {

				con = dbConnection.getConnection();
				stmt = (Statement) con.createStatement();

				String sql = "update " + tablename + ",master_table set " + tablename + ".strike_Value=1," + tablename
						+ ".strike_approval_done=1," + tablename + ".strike_approval='Done'," + tablename
						+ ".strike_time='" + currntDt + "'," + tablename + ".strike_id=" + activeuserid + ","
						+ tablename + ".strike_comment='" + strikecomment
						+ "',master_table.incineration_condition=1,master_table.entry_strike_cond=1 where " + tablename
						+ ".id=master_table.entry_id AND " + tablename + ".entry_id=" + entryId;

				System.out.println(sql + "----:query");
				stmt.executeUpdate(sql);

			}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return_stmt = ReturnResponse.retrnresponse_db_error();
			}
			return return_stmt;
		}

		else if (tablename.equals("start_incineration")) {

			System.out.println("***********************&&&&&&&&&&& from start condition &&&*&*&*&*&*&*&*&*&*&*&*&**&");

			try {

				stmt = (Statement) con.createStatement();

				String sql = "update " + tablename + ",master_table set " + tablename + ".strike_Value=1," + tablename
						+ ".strike_approval_done=1," + tablename + ".strike_approval='Done'," + tablename
						+ ".strike_time='" + currntDt + "'," + tablename + ".strike_id=" + activeuserid + ","
						+ tablename + ".strike_comment='" + strikecomment
						+ "',master_table.incineration_condition=0,master_table.entry_strike_cond=1 where " + tablename
						+ ".id=master_table.entry_id AND " + tablename + ".entry_id=" + entryId;

				stmt.executeUpdate(sql);
			}

			catch (SQLException sqlex) {

				return_stmt = ReturnResponse.retrnresponse_db_error();
			}

		}

		else {
			if (approvalcondition == 1) {
				try {
					con = dbConnection.getConnection();
					System.out.println(
							"***********************&&&&&&&&&&& from else condition &&&*&*&*&*&*&*&*&*&*&*&*&**&");
					stmt = (Statement) con.createStatement();
					String sql1 = "update " + tablename + ",master_table set " + tablename + ".strike_Value=1,"
							+ tablename + ".strike_approval_done=1," + tablename + ".strike_approval='Done',"
							+ tablename + ".master_reapproval_done=1," + tablename + ".strike_id=" + activeuserid + ","
							+ tablename + ".strike_time='" + currntDt + "'," + tablename + ".strike_comment='"
							+ strikecomment + "',master_table.entry_strike_cond=1 where " + tablename
							+ ".entry_Id=master_table.entry_id AND " + tablename + ".entry_id=" + entryId;
					System.out.println(sql1 + "----:query");
					/* rt = stmt.executeUpdate(sql1); */

					stmt.executeUpdate(sql1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return_stmt = ReturnResponse.retrnresponse_db_error();
				}
			}

			else {
				con = dbConnection.getConnection();
				System.out.println(
						"***********************&&&&&&&&&&& from else22 condition &&&*&*&*&*&*&*&*&*&*&*&*&**&");
				try {
					stmt = (Statement) con.createStatement();

					String sql1 = "update " + tablename + ",master_table set " + tablename + ".strike_Value=1,"
							+ tablename + ".strike_approval_done=1," + tablename + ".strike_approval='Done',"
							+ tablename + ".strike_id=" + activeuserid + "," + tablename + ".master_reapproval_done=1,"
							+ tablename + ".strike_time='" + currntDt + "'," + tablename + ".strike_comment='"
							+ strikecomment + "',master_table.entry_strike_cond=1 where " + tablename
							+ ".entry_Id=master_table.entry_id AND " + tablename + ".entry_id=" + entryId;
					System.out.println(sql1 + "----:query");
					rt = stmt.executeUpdate(sql1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return_stmt = ReturnResponse.retrnresponse_db_error();
				}
			}

		}
		return return_stmt;
	}

	public int get_primary_key_of_entry() {
		int rtrn_prmry_key = 0;

		return rtrn_prmry_key;
	}
}
