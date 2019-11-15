package com.nts.orb1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class CommonOrbDtlsDao {

	public static JSONArray tankData(int i) {
		JSONArray quntityData = new JSONArray();

		try {

			String query = "select tnk.* from tanks_entry_detalis_orb tnk where tnk.tank_type_id=" + i;
			Connection con = dbConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			quntityData = DaoUtil.convertToJsonArray1(rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(quntityData);
		return quntityData;

	}
}
