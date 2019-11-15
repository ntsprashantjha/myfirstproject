package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;

import com.nts.grb.connection.dbConnection;

public class GetEntryDetails {

	public static String lastDateEntryOfDTS() {
		String lastDate = null;

		try {

			Connection con = dbConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT stop_date FROM daily_tank_sounding_sheet_orb1 where strike_value = 0 ORDER BY stop_date DESC LIMIT 1");
			while (rs.next()) {
				lastDate = rs.getString(1);
				
				String nextdate = LocalDate.parse(lastDate).plusDays(1).toString();
				return nextdate;
				//System.out.println("Last Filled Date : - " + lastDate);
			}
			if(lastDate==null)
				lastDate = new GetFirstTankAddTime().getDate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastDate;

	}

}
