package com.nts.mrb.auditrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.StringToJsonConverter;

public class LoginAudit {

	public static void updateloginInfo(String user_id, String user_ip, String software)
			throws JSONException, Exception {

		String updationQuery = "INSERT INTO audit_details_info (user_id,action,description,pre_value,next_value,user_system,software_name) values(?,?,?,?,?,?,?)";
		Connection con = dbConnection.getConnection();

		PreparedStatement ps = con.prepareStatement(updationQuery);
		String user_id1 = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(user_ip)))
				.get(0)).getString("emp_id");

		ps.setString(1, user_id1);
		ps.setString(2, "Logged in");

		ps.setString(3, "user " + user_id1 + " is logged in");
		ps.setString(4, "");

		ps.setString(5, "");
		ps.setString(6, user_ip);
		ps.setString(7, software);

		System.out.println(updationQuery);
		ps.executeUpdate();
	}

	public void updatelogoutinfo(String user_id, String user_ip, String software) {

		String updationQuery = "INSERT INTO audit_details_info (user_id,action,description,pre_value,next_value,user_system,software_name) values(?,?,?,?,?,?,?)";
		Connection con = dbConnection.getConnection();

		PreparedStatement ps;

		try {

			ps = con.prepareStatement(updationQuery);

			ps.setString(1, user_id);
			ps.setString(2, "Logged out");

			ps.setString(3, "user " + user_id + " is logged out");
			ps.setString(4, "");

			ps.setString(5, "");
			ps.setString(6, user_ip);
			ps.setString(7, software);

			System.out.println(updationQuery);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws InstantiationException, IllegalAccessException, SQLException {

	}

}
