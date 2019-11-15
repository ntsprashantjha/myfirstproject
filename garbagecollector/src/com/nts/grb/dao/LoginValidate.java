package com.nts.grb.dao;

import java.sql.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.LoginAudit;

public class LoginValidate {
	private static Connection con = null;
	private static ResultSet rs = null;
	private static String name, name1, pass;
	private static JSONArray jsonArray = new JSONArray();
	private static JSONObject obj = new JSONObject();
	private static Statement stmt = null;

	public static boolean validate(String name, String pass, String macId) {
		System.out.println("mac_id************" + macId);
		boolean isValid = false;
		try {
			con = dbConnection.getConnection();
			stmt = (Statement) con.createStatement();
			String sql1 = "update user_details set user_activity=0,user_macId=' ' where user_macId='" + macId + "'";
			System.out.println(sql1);
			stmt.executeUpdate(sql1);
			String sqlquery = " update user_details set user_activity=1,user_macId='" + macId
					+ "' where user_confirm_pasword='" + pass + "' AND user_first_name='" + name + "'";
			PreparedStatement ps1 = con.prepareStatement(sqlquery);
			if (ps1.executeUpdate() != 0) {
				isValid = true;
			} else {
				isValid = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public static JSONArray validate1(String mac) {

		JSONArray isValid = new JSONArray();

		try {

			con = dbConnection.getConnection();
			String sqlquery = "update user_details set user_activity=0 where user_activity=1 AND user_macId='" + mac
					+ "'";
			PreparedStatement ps = con.prepareStatement(sqlquery);
			
			
			new LoginAudit().updatelogoutinfo(
					((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac))).get(0))
							.getString("emp_id"),
					mac, "MRB");
		
			
			if (ps.executeUpdate() != 0) {

				isValid = ReturnResponse.success_condition();
			}

			else {
				isValid = ReturnResponse.somethingWentWrong();
			}

		} catch (Exception e) {

			isValid = ReturnResponse.retrnresponse_exception_occured();
			e.printStackTrace();

		}

		return isValid;
	}

	public static JSONArray success_condition(String mac) {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		PreparedStatement ps = null;
		
		try {
		
			con = dbConnection.getConnection();
			String sql = "select user_details.user_status,user_details.user_isadmin from user_details where user_activity=1 AND user_macId='"
					+ mac + "'";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				responsejson.put("user_status", rs.getInt("user_status"));
				responsejson.put("user_isadmin", rs.getInt("user_isadmin"));
			}
			
			responsejson.put("status", true);
			responsejson.put("message", "user successfuly loged In");
			resArr.put(responsejson);
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}
}