package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class ShipCompTimeDtls {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static JSONArray getDtls() throws SQLException, JSONException {
		JSONArray js = new JSONArray();
		js.put(ShipCompTimeDtls.company_dtls_status());
		js.put(ShipCompTimeDtls.ship_dtls_status());
		js.put(ShipCompTimeDtls.time_dtls_status());
		return js;

	}

	public static JSONObject ship_dtls_status() throws SQLException, JSONException {
		con = dbConnection.getConnection();
		String sql = "select comp_status from company_details";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DataDao.getdtlsJSonOb(rs);
	}

	public static JSONObject company_dtls_status() throws SQLException, JSONException {
		con = dbConnection.getConnection();
		String sql = "select vessel_status from vessel_details";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DataDao.getdtlsJSonOb(rs);
	}

	public static JSONObject time_dtls_status() throws SQLException, JSONException {
		con = dbConnection.getConnection();
		String sql = "select id from time_zone";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DataDao.getdtlsJSonOb(rs);
	}

}
