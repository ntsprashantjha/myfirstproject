package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;

public class checkPreviousDateData {

	public static boolean checkDate(String date) {

		try {

			Connection con = dbConnection.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select date from daily_tank_sounding_sheet where date ='" + date + "'");

			ResultSet rs = ps.executeQuery();
			rs.next();
			String dbpdate = rs.getString(1);

			if (dbpdate.equals(date)) {
				System.out.println("Previous Date Data Available");
				return true;
			}
			else if(new ForFirstEntryInitialDate().countEntry("daily_tank_sounding_sheet")) {
					return true;
			}
			else {
				System.out.println("Previous Date Data Not Available Else");
				return false;
			}
			

		} catch (SQLException e) {

			System.out.println("Previous Date Data Not Available Exp");
			e.printStackTrace();
			return false;

		}

	}

	public static void main(String[] args) throws SQLException {
		checkDate("2019-09-09");
	}

}

