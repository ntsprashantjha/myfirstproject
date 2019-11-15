package com.nts.annexvi.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;

public class GetBunkerLogDetails {

	public static JSONObject getBunkerLogDetails(int id, Connection con) {

		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			ResultSet rs1 = ps.executeQuery("SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM bunker_log_anx dta LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details "
					+ "UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id = " + id);

			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);

			ResultSet rs = ps.executeQuery("select fo.qty_added,fo.tank_name from bunker_log_anx_tnkdetails fo "
					+ "LEFT JOIN bunker_log_anx fob on fo.source_entry_id=fob.entry_id WHERE entry_id = " + id);

			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {

				JSONObject j1 = new JSONObject();

				j1.put("qty_added", rs.getDouble(1));
				j1.put("tank_name", rs.getString(2));

				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}

			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);

			// System.out.println(entryDetails);

			return entryDetails;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) throws Exception {

//		Connection con = com.nts.grb.connection.dbConnection.getConnection();
//		GetBunkerLogDetails.getBunkerLogDetails(id, con)
	}
}