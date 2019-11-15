package com.nts.orb1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;

public class GetQuantity {

	public static JSONObject getTankData(int tnkid, List<String> dates) throws SQLException, JSONException {

		JSONObject tankdata = new JSONObject();
		Connection con = dbConnection.getConnection();

		for (int i = 1; i <= 7; i++) {

			PreparedStatement ps = con.prepareStatement("select quantity from daily_tank_sounding_sheet WHERE date = '"
					+ dates.get(i - 1) + "' and tank_id = ?");
			ps.setInt(1, tnkid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				// If Quantity Available At Particular Date It Will Added Into tankdata Object
				tankdata.put("date_" + i, rs.getObject(1));
			} else {

				tankdata.put("date_" + i, "0");
			}
		}
		// System.out.println(tankdata);

		// For Tank Name Corresponding Tank ID
		PreparedStatement ps = con.prepareStatement(
				"select tank_name,tank_automatic,tank_capacity,frame_number,current_quantity from tanks_entry_detalis_orb where tank_id = ?");
		ps.setInt(1, tnkid);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			tankdata.put("tank_name", rs.getString(1));
			tankdata.put("tank_automatic", rs.getString(2));
			tankdata.put("tank_capacity", rs.getObject(3));
			tankdata.put("frame_number", rs.getObject(4));
			tankdata.put("date_7", rs.getString(5));

		}
		// System.out.println(tankdata);

		return tankdata;
	}

	public static JSONArray getQuantity(String startdate) throws SQLException, JSONException {

		// For All Tank Id
		List<Integer> tankid = new ArrayList<>();
		Connection con = dbConnection.getConnection();

		PreparedStatement ps = con
				.prepareStatement("select DISTINCT tank_id from tanks_entry_detalis_orb WHERE tank_type_id <> 3 AND tank_type_id <> 5");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			tankid.add(rs.getInt(1));
		}
		// System.out.println("Tank Ids : - " + tankid);

		List<String> dates = getPreviousDates(startdate);
		// System.out.println("Dates : - " + dates);

		JSONArray alldata = new JSONArray();
		JSONObject dateobj = new JSONObject();
		JSONArray datearry = new JSONArray();

		// Creating A JSONObject Of All Dates
		for (int i = 1; i <= dates.size(); i++) {

			dateobj.put("date_" + i, dates.get(i - 1));

		}
		datearry.put(dateobj);
		// System.out.println("Dates Obj : - " + datearry);

		// Put Dates JSONObj Into All Data Array
		alldata.put(dateobj);

		for (int i = 0; i < tankid.size(); i++) {

			alldata.put(getTankData(tankid.get(i), getPreviousDates(startdate)));
		}
//		System.out.println(alldata);

		return alldata;

	}

	public static List<String> getNextDates(String startdate) {

		// Start Date And End Date Format Should Be Like 2019-12-31
		LocalDate start = LocalDate.parse(startdate);
		LocalDate end = LocalDate.parse(start.plusDays(6).toString());

		List<String> totalDates = new ArrayList<>();

		while (!start.isAfter(end)) {

			totalDates.add(start.toString());
			start = start.plusDays(1);

		}

//		System.out.println(totalDates);
		return totalDates;
	}

	public static List<String> getPreviousDates(String startdate) {

		LocalDate currentdate = LocalDate.parse(startdate);
		// Start Date And End Date Format Should Be Like 2019-12-31
		LocalDate start = LocalDate.parse(currentdate.minusDays(6).toString());
		LocalDate end = LocalDate.parse(startdate);

		List<String> totalDates = new ArrayList<>();

		while (!start.isAfter(end)) {

			totalDates.add(start.toString());
			start = start.plusDays(1);

		}

//		System.out.println(totalDates);
		return totalDates;
	}

	public static List<String> getAllDates(String startdate, int day) {

		// Start Date And End Date Format Should Be Like 2019-12-31
		LocalDate start = LocalDate.parse(startdate);
		LocalDate end = LocalDate.parse(start.plusDays(day).toString());

		List<String> totalDates = new ArrayList<>();

		while (!start.isAfter(end)) {

			totalDates.add(start.toString());
			start = start.plusDays(1);

		}

		//System.out.println(totalDates);
		return totalDates;
	}

	public static List<String> getPreviousDatesByDays(String startdate, int day) {

		LocalDate currentdate = LocalDate.parse(startdate);
		// Start Date And End Date Format Should Be Like 2019-12-31
		LocalDate start = LocalDate.parse(currentdate.minusDays(day).toString());
		LocalDate end = LocalDate.parse(startdate);

		List<String> totalDates = new ArrayList<>();

		while (!start.isAfter(end)) {

			totalDates.add(start.toString());
			start = start.plusDays(1);

		}

//		System.out.println(totalDates);
		return totalDates;
	}

	public static void main(String[] args) throws SQLException, JSONException {

		// System.out.println(getQuantity("2019-09-04"));
		// Start Date And End Date Format Should Be Like 2019-12-31
		getQuantity("2019-09-10");

	}

}