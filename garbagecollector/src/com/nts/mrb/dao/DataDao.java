package com.nts.mrb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataDao {
	public static JSONObject getAccessRight(ResultSet rs) throws SQLException, JSONException {
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		while (rs.next()) {
			int total_rows = rs.getMetaData().getColumnCount();
			for (int i = 0; i < total_rows; i++) {
				obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
			}
			jsonArray.put(obj);
		}
		return obj;
	}
	public static JSONObject getdtlsJSonOb(ResultSet rs) throws SQLException, JSONException {
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		while (rs.next()) {
			int total_rows = rs.getMetaData().getColumnCount();
			for (int i = 0; i < total_rows; i++) {
				obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
			}
			jsonArray.put(obj);
		}
		return obj;
	}

}
