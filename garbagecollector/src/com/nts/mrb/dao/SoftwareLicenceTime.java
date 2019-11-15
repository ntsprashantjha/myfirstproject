package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.DateDifferentExample;
import com.nts.grb.service.GetCurrentDateTime;

public class SoftwareLicenceTime {

	String dt;
	public static int remainingTime;
	Connection connection = null;
	Statement stmnt = null;
	ResultSet rs = null;
	public static String remainDays;

	public JSONObject insertDate(String registrationDate) {
		try {
			String sql = "insert into mrb_licence_validation";
			connection = dbConnection.getConnection();
			stmnt = connection.createStatement();
			stmnt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String arg[]) {
		System.out.println("remaining days is:-" + new SoftwareLicenceTime().remainTime());
	}

	public int remainTime() {

		String currentDate = GetCurrentDateTime.onlycurrentDate();
		Connection con = dbConnection.getConnection();

		String sql = "select * from mrb_licence_validation";

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dt = rs.getString("registration_date");
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		remainDays = DateDifferentExample.dateCompare(currentDate, dt);
		int rmainsdys = Integer.parseInt(remainDays);

		System.out.println("abclslsls" + rmainsdys);

		return rmainsdys;
	}

}
