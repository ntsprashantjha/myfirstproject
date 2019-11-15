package com.nts.annexvi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;

import com.nts.annexvi.service.GetBunkerLogDetails;

public class Getting_homeData {

	int entry_id;
	String table_name = null;

	public JSONArray getDBDtls(int entry_id, Connection con) throws Exception {

		JSONArray js = null;

		String entry_type = new Getting_homeData().getEntry_type(entry_id, con);

//		System.out.println("Entry Type: - "+entry_type);
//		System.out.println("Entry Id: - "+entry_id);

		if (entry_type.equalsIgnoreCase("BUNKER LOG")) {

			JSONObject bunkerLog = GetBunkerLogDetails.getBunkerLogDetails(entry_id, con);
			// System.out.println(bunkerLog);
			JSONArray js1 = new JSONArray();

			if (bunkerLog != null) {
				js1.put(bunkerLog);
			}

			// System.out.println(js1);

			return js1;
		}

		this.entry_id = entry_id;
		String sql_query = GetTableName.getQueries(entry_type);
		// System.out.println("sql query:----" + sql_query);

		PreparedStatement ps = con.prepareStatement(sql_query);

		// System.out.println(entry_id);
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

	private String getEntry_type(int entry_id, Connection con) throws SQLException {

		String sql_query = "select entry_type from master_table_annexvi where entry_id=" + entry_id;

//		System.out.println(sql_query);
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
