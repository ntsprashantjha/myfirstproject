package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class GetDifferEntryDetails {

	public static JSONObject getFuelBunkeringDetails(int id, Connection con) {

		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			String query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank "
					+ "FROM fuel_oil_bunkerig_h26 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id "
					+ "LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details "
					+ "UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id " + "WHERE dta.entry_id =" + id
					+ " and missed_entry = 'NO' ORDER BY start_date DESC";

			ResultSet rs1 = ps.executeQuery(query);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			ResultSet rs = ps.executeQuery(
					"select fo.qty_added,fo.qty_retained,fo.tank_name from fuel_oil_bunkerig_tankdetails_h26 fo "
							+ "LEFT JOIN fuel_oil_bunkerig_h26 fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static JSONObject getBulkLubricateDetails(int id, Connection con) {
		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			String query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank "
					+ "FROM bulk_lubricating_oil_bunkerig_h26 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id "
					+ "LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details "
					+ "UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id " + "WHERE dta.entry_id =" + id
					+ " and missed_entry = 'NO' ORDER BY start_date DESC";

			ResultSet rs1 = ps.executeQuery(query);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			ResultSet rs = ps.executeQuery(
					"select fo.qty_added,fo.qty_retained,fo.tank_name from bulk_lubricating_oil_bunkerig_tankdetails_h26 fo "
							+ "LEFT JOIN bulk_lubricating_oil_bunkerig_h26 fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static JSONObject getde_bunkering_of_fuel_oil(int id, Connection con) {

		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			String query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank "
					+ "FROM de_bunkering_of_fuel_oil dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id "
					+ "LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details "
					+ "UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id " + "WHERE dta.entry_id =" + id
					+ " and missed_entry = 'NO' ORDER BY start_date DESC";

			ResultSet rs1 = ps.executeQuery(query);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			System.out.println(entryDetails);
			ResultSet rs = ps.executeQuery(
					"select fo.qty_added,fo.qty_retained,fo.tank_name from de_bunkring_of_fuel_oil_tnk_details fo "
							+ "LEFT JOIN de_bunkering_of_fuel_oil fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static JSONObject getMissedEntry(int id, Connection con) {

		JSONObject entryDetails = new JSONObject();

		try {
			Statement ps = con.createStatement();

			ResultSet rs1 = ps.executeQuery(
					"select missed_entry_type,missed_date,missed_user_name,missed_user_rank from missed_entry_orb1 WHERE source_entryid = "
							+ id);
			String entry_type = "";

			if (rs1.next()) {

				entry_type = rs1.getString(1);
			}

			String sql_query = GetTableName.getQueries(entry_type, "YES");

			PreparedStatement pst = con.prepareStatement(sql_query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs).get(0);

			entryDetails.put("missed_date", rs1.getString(2));
			entryDetails.put("missed_user_name", rs1.getString(3));
			entryDetails.put("missed_user_rank", rs1.getString(4));

			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static void main(String[] args) throws Exception {

		Connection con = dbConnection.getConnection();
		System.out.println(GetDifferEntryDetails.getMissedEntry(79, con));
	}
}