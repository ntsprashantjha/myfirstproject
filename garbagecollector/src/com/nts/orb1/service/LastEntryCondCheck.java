package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.nts.grb.connection.dbConnection;

public class LastEntryCondCheck {

	public int lastEntryCheck(String date, String tableName) {

		int cond = 0;
		String lastEntryDt = null;

		try {
			if (new ForFirstEntryInitialDate().countEntry("master_table_orb1")) {
				cond = 1;
				System.out.println("No Entry Available ");
			}
			else
			{
			String query = "SELECT entry_date FROM master_table_orb1 WHERE "
					+ "entry_date_only=( SELECT MAX(entry_date_only) FROM master_table_orb1 "
					+ "WHERE entry_type <> 'Daily Tank Sounding Sheet Updation' AND entry_strike_cond=0 ) "
					+ "and entry_type <> 'Daily Tank Sounding Sheet Updation' ORDER BY entry_id DESC LIMIT 1";

			Connection con = dbConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				lastEntryDt = rs.getString("entry_date");

			}
			System.out.println("Last Entry Date : -" + lastEntryDt);

			if(lastEntryDt==null) {
				lastEntryDt = new GetFirstTankAddTime().getDate();
			}
			
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");

			Date lastdate = sdfo.parse(lastEntryDt);
			System.out.println("Last Date : -" + lastdate);
			Date entrydate = sdfo.parse(date);
			System.out.println("Entry Date : -" + entrydate);

			if (entrydate.compareTo(lastdate) >= 0)
				cond = 1;
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
			return cond;

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Date Parsing Error");
			return cond;

		}
		System.out.println("Last Date" + lastEntryDt + "\n" + date + "\n" + cond);

		return cond;

	}

	public int lastEntryDailyTankSoundsheet(String date, String tableName) {

		int cond = 0;
		String start = null;
		String lastEntryDt = null;

		try {

			String query = "SELECT stop_date FROM daily_tank_sounding_sheet_orb1 where strike_value = 0 ORDER BY stop_date DESC LIMIT 1";

			Connection con = dbConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				lastEntryDt = rs.getString("stop_date");

			}
			// Decrease 1 Day From Given Date
			start = LocalDate.parse(lastEntryDt).minusDays(1).toString();

			cond = start.compareTo(date);

			if (new ForFirstEntryInitialDate().countEntry("daily_tank_sounding_sheet")) {
				cond = 0;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}
		System.out.println("date ********" + start + "\n" + lastEntryDt + "\n" + date + "\n" + cond);

		return cond;

	}

}
