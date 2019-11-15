package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

public class LoginValidate {
	private static Connection con = null;
	private static ResultSet rs = null;
	private static JSONArray jsonArray = new JSONArray();
	private static JSONObject obj = new JSONObject();
	private static Statement stmt = null;
	private PreparedStatement ps = null;
	private static String user_name, user_pass, user_macId;

	public JSONObject user_access_right(String name, String pass, String macId) throws SQLException, JSONException {
		this.user_name = name;
		this.user_pass = pass;
		this.user_macId = macId;

		if (is_user_valid() > 0) {
			con = dbConnection.getConnection();
			String sql = "SELECT * FROM `mrb_module_access_right` WHERE user_id=(select user_details.user_id FROM user_details WHERE user_confirm_pasword='"
					+ pass + "' AND user_first_name='" + name + "')";
			System.out.println("query:----" + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			obj = DataDao.getAccessRight(rs);
			obj.put("user_name", user_name);
			System.out.println("----details from login validsate---" + obj);

		}
		return obj;

	}

	public static int is_user_valid() {
		int isValid = 0;
		try {
			con = dbConnection.getConnection();
			String sqlquery = " update user_details set user_activity=1,user_macId='" + user_macId
					+ "' where user_confirm_pasword='" + user_pass + "' AND user_first_name='" + user_name + "'";
			PreparedStatement ps1 = con.prepareStatement(sqlquery);
			isValid = ps1.executeUpdate();
			System.out.println("jnfe:=---" + isValid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public JSONArray signOfUser(String emp_id, Date leave_date) {
		/*
		 * Connection con = dbConnection.getConnection(); Statement stmt; String sql =
		 * "select rank_id from user_details where emp_id='"+emp_id+"'";
		 * System.out.println(sql); ResultSet rs = stmt.executeQuery(sql); rs.next();
		 * int b=rs.getInt(1); if(b==1) { String sql2 =
		 * "select count(rank_id) from user_details where rank_id='1'";
		 * System.out.println(sql); ResultSet rs2 = stmt.executeQuery(sql); rs.next();
		 * int b2=rs.getInt(1); }
		 * 
		 * }
		 * 
		 * 
		 * try {
		 * 
		 * 
		 * stmt = (Statement) con.createStatement();
		 * 
		 * String sql = "update user_details set user_confirm_pasword='" + null +
		 * "',user_status=" + 2 + ", user_leavedate='" + leave_date + "' where emp_id='"
		 * + emp_id + "'"; System.out.println(sql); stmt.executeUpdate(sql); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * return com.nts.grb.validation.ReturnResponse.retrnresponse_db_error(); }
		 * return com.nts.grb.validation.ReturnResponse.success_condition(); }
		 */

		try {

			Connection con = dbConnection.getConnection();
			Statement stmt = (Statement) con.createStatement();

			String sql = "select rank_id from user_details where emp_id='" + emp_id + "'";
			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			int captain_rank = rs.getInt("rank_id");
			System.out.println(captain_rank);

			if (captain_rank == 1) {

				String sql2 = "select count(rank_id) from user_details where rank_id='1' and user_status='1'";
				System.out.println(sql2);

				ResultSet rs2 = stmt.executeQuery(sql2);
				rs2.next();

				int b2 = rs2.getInt(1);

				if (b2 == 1) {
					return ReturnResponse.extracaptain_not_available();
				}
			}
			String sql3 = "update user_details set user_confirm_pasword='" + null + "',user_status=" + 2
					+ ", user_leavedate='" + leave_date + "' where emp_id='" + emp_id + "'";
			System.out.println(sql3);
			stmt.executeUpdate(sql3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return com.nts.grb.validation.ReturnResponse.retrnresponse_db_error();
		}
		return com.nts.grb.validation.ReturnResponse.success_condition();
	}

}
