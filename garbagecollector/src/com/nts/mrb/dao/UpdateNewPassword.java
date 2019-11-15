package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class UpdateNewPassword {
	public JSONArray updatePass(int user_id, String oldpass, String newpass) {

		Connection con = dbConnection.getConnection();
		Statement stmt;

		try {
			stmt = (Statement) con.createStatement();
			
			String sql = "update user_details set user_confirm_pasword='" + newpass + "',user_status=" + 1
					+ " where user_id=" + user_id + " AND user_confirm_pasword='" + oldpass + "'";

			int isdone = stmt.executeUpdate(sql);

			if (isdone > 0)
				return com.nts.grb.validation.ReturnResponse.success_condition();
			else
				return com.nts.grb.validation.ReturnResponse.somethingWentWrong();

		} 
		
		catch (SQLException e) {

			return com.nts.grb.validation.ReturnResponse.retrnresponse_db_error();
		}

	}

	public JSONArray forgatPassword(String user_id, String newpass) {
		Connection con = dbConnection.getConnection();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();

			String sql = "update user_details set user_confirm_pasword='" + newpass + "',user_status=" + 0
					+ " where emp_id='" + user_id + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return com.nts.grb.validation.ReturnResponse.retrnresponse_db_error();
		}
		return com.nts.grb.validation.ReturnResponse.success_condition();
	}

}
