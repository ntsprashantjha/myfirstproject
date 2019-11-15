package com.nts.grb.dao;

import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaoUtil2 {

	public static JSONArray convertToJsonArray(ResultSet resultSet) throws Exception {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
			}
			jsonArray.put(obj);
		}
		return jsonArray;
	}//SELECT DISTINCT * from stop_incineration,discharge_to_sea_acc,dischargetosea,dischargetoashor

}
