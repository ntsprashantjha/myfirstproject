package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.validation.ReturnResponse;

public class EntryDataForReport {

	public static JSONObject getSludgeEntryData(String entryName, List<String> dates, Connection con) {

		JSONObject tankdata = new JSONObject();
		try {
			String query = GetTableName.getSludgeEntryQuery(entryName);

			for (int i = 1; i <= dates.size(); i++) {

				String final_query = query + "'" + dates.get(i - 1) + "'";
				// System.out.println(final_query);

				PreparedStatement ps = con.prepareStatement(final_query);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					// If Quantity Available At Particular Date It Will Added Into tankdata Object
					tankdata.put("date_" + i, rs.getDouble(1));

				} else {

					tankdata.put("date_" + i, "0");

				}
			}

			// System.out.println(tankdata);
			return tankdata;

		} catch (SQLException e) {
			e.printStackTrace();

			tankdata = null;

			ReturnResponse.retrnresponse_db_error();
		} catch (JSONException e) {
			e.printStackTrace();

			tankdata = null;

			ReturnResponse.retrnresponse_wrong_json();
		}
		return tankdata;
	}

	public static double getSludgeTotalAvgData(String entryName, String startDate, String endDate, Connection con) {

		double total = 0;
		try {
			String query = GetTableName.getSludgeTotalAvgQuery(entryName);

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getDouble(1);
			}
			// System.out.println(tankdata);
			return total;

		} catch (SQLException e) {
			e.printStackTrace();
			ReturnResponse.retrnresponse_db_error();
		}
		return total;
	}
	
	public static double getBilgeTotalAvgData(String entryName, String startDate, String endDate, Connection con) {

		double total = 0;
		try {
			String query = GetTableName.getBilgeTotalAvgQuery(entryName);

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getDouble(1);
			}
			// System.out.println(tankdata);
			return total;

		} catch (SQLException e) {
			e.printStackTrace();
			ReturnResponse.retrnresponse_db_error();
		}
		return total;
	}

	public static JSONObject getBilgeEntryData(String entryName, List<String> dates, Connection con) {

		JSONObject tankdata = new JSONObject();
		try {
			String query = GetTableName.getBilgeEntryQuery(entryName);

			for (int i = 1; i <= dates.size(); i++) {

				String final_query = query + "'" + dates.get(i - 1) + "'";
				// System.out.println(final_query);

				PreparedStatement ps = con.prepareStatement(final_query);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					// If Quantity Available At Particular Date It Will Added Into tankdata Object
					tankdata.put("date_" + i, rs.getDouble(1));

				} else {

					tankdata.put("date_" + i, "0");

				}
			}

			// System.out.println(tankdata);
			return tankdata;

		} catch (SQLException e) {
			e.printStackTrace();

			tankdata = null;

			ReturnResponse.retrnresponse_db_error();
		} catch (JSONException e) {
			e.printStackTrace();

			tankdata = null;

			ReturnResponse.retrnresponse_wrong_json();
		}
		return tankdata;
	}

	public static void main(String[] args) throws SQLException, JSONException {
//		Connection con = dbConnection.getConnection();
//		List<String> dates = GetQuantity.getNextDates("2019-10-01");
////		SludgeEntryDataForReport.getEntryData("Incineration of sludge C 12.3", dates,con);
//		System.out.println(EntryDataForReport.getSludgeTotalAvgData("Sludge Burning In Boiler C_12.4", "2019-10-01",
//				"2019-10-10", con));
	}
}
