package com.nts.mrb.auditrecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.StringToJsonConverter;

public class UpdateAudit {

	public static void updateDetails(String curntZone, String user_ip, String software) {

		String updationQuery = "INSERT INTO audit_details_info (user_id,action,description,pre_value,next_value,user_system,software_name) values(?,?,?,?,?,?,?)";
		Connection con = dbConnection.getConnection();

		PreparedStatement ps;

		try {

			String preTimezone = getPreTimeZone();

			ps = con.prepareStatement(updationQuery);

			String user_id = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(user_ip)))
					.get(0)).getString("emp_id");

			ps.setString(1, user_id);
			ps.setString(2, "Updation");

			ps.setString(3, "TimeZone updated  " + curntZone + " to " + preTimezone);
			ps.setString(4, preTimezone);

			ps.setString(5, curntZone);
			ps.setString(6, user_ip);

			ps.setString(7, software);
			System.out.println(updationQuery);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getPreTimeZone() {

		String timezone = null;
		Connection con = dbConnection.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement("select time_zone from time_zone");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				timezone = rs.getString("time_zone");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timezone;
	}

}
