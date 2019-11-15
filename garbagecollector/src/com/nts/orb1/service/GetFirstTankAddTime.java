package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.nts.grb.connection.dbConnection;

public class GetFirstTankAddTime {

	public String getTimeStamp() {
		String timestamp = null;
		try {
			Connection con = dbConnection.getConnection();
			String query = "select min(tank_add_time) from tanks_entry_detalis_orb";
			//System.out.println(query);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				 timestamp = rs.getString(1);
				 
			}
			return timestamp;
		} catch (SQLException e) {
			e.printStackTrace();
			return timestamp;
		}

	}
	public String getDate() throws ParseException {
		String Timestamp = new GetFirstTankAddTime().getTimeStamp();
		//System.out.println(Timestamp);
		String date = "";
		for (int i = 0; i < 10; i++) {
			date += Timestamp.charAt(i);
		}
		//System.out.println(date);
		return date;

	}
	public String getTime() throws ParseException {
		String Timestamp = new GetFirstTankAddTime().getTimeStamp();
		//System.out.println(Timestamp);
		String time = "";
		for (int i = 11; i <= 15; i++) {
			time += Timestamp.charAt(i);
		}
		//System.out.println(time);
		return time;

	}
	
	
	public static void main(String[] args) throws ParseException {
		new GetFirstTankAddTime().getTime();
	}

}
