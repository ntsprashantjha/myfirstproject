package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;

import com.nts.orb1.service.GetDifferEntryDetails;

public class Getting_homeData {

	int entry_id;
	String table_name = null;

	public JSONArray getDBDtls(int entry_id, Connection con) throws Exception {

		JSONArray js = null;

		String entry_type = new Getting_homeData().entry_table_name(entry_id, con);

//		System.out.println("Entry Type: - "+entry_type);
//		System.out.println("Entry Id: - "+entry_id);

		if (entry_type.equalsIgnoreCase("FUEL OIL BUNKERING (H 26.1,26.2,26.3)")) {

			JSONObject fueloilbunker = GetDifferEntryDetails.getFuelBunkeringDetails(entry_id, con);
			JSONArray js1 = new JSONArray();

			if (fueloilbunker != null) {
				js1.put(fueloilbunker);
			}

			// System.out.println(js1);

			return js1;
		}

		else if (entry_type.equalsIgnoreCase("LUB OIL BUNKERING (H 26.1,26.2,26.4)")) {

			JSONObject luboilbunker = GetDifferEntryDetails.getBulkLubricateDetails(entry_id, con);
			JSONArray js1 = new JSONArray();

			if (luboilbunker != null) {
				js1.put(luboilbunker);
			}

			// System.out.println(js1);

			return js1;
		} else if (entry_type.equalsIgnoreCase("de_bunkering_of_fuel_oil")) {

			JSONObject fueloildebunker = GetDifferEntryDetails.getde_bunkering_of_fuel_oil(entry_id, con);
			JSONArray js1 = new JSONArray();

			if (fueloildebunker != null) {
				js1.put(fueloildebunker);
			}

			// System.out.println(js1);

			return js1;
		} else if (entry_type.equalsIgnoreCase("MISSED ENTRY")) {

			JSONObject missedEntryData = GetDifferEntryDetails.getMissedEntry(entry_id, con);
			JSONArray js1 = new JSONArray();

			if (missedEntryData != null) {
				js1.put(missedEntryData);
			}

			// System.out.println(js1);

			return js1;
		}

		this.entry_id = entry_id;
		// System.out.println("Entry Id - "+entry_id);

		String sql_query = GetTableName.getQueries(entry_type, "NO");

//		System.out.println("Entry Type - "+entry_type);
//		System.out.println(sql_query);

		PreparedStatement ps = con.prepareStatement(sql_query);

		ps.setInt(1, entry_id);
		// System.out.println(sql_query);

		ResultSet rs = ps.executeQuery();

		js = DaoUtil.convertToJsonArray1(rs);
//		for (int i = 0; i < js.length(); i++) {
//			
//			System.out.println(js.get(i).toString() + "\n");
//		}

		return js;
	}

	private String entry_table_name(int entry_id, Connection con) throws SQLException {

		String sql_query = "select entry_type from master_table_orb1 where entry_id=" + entry_id;

		// System.out.println(sql_query);
		PreparedStatement ps = con.prepareStatement(sql_query);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			table_name = rs.getString("entry_type");
		}

		return table_name;

	}

	public static void main(String[] args) throws Exception {
		// System.out.println(new Getting_homeData().getDBDtls(11));
	}
}
