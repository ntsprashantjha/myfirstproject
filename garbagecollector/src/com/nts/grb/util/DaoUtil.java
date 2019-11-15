package com.nts.grb.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class DaoUtil {

	static String sb;
	static String abc = "\"\",";

	public static String convertToJsonArray(ResultSet resultSet) throws Exception {

		JSONArray jsonArray = new JSONArray();

		while (resultSet.next()) {

			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();
			

			for (int i = 0; i < total_rows; i++) {

				obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
			}

			jsonArray.put(obj);
		}
		return jsonArray.toString();
	}

	public static String categoryBiseTable(ResultSet resultSet) throws Exception {

		StringBuffer jsonArray = new StringBuffer();

		while (resultSet.next()) {

			int total_rows = resultSet.getMetaData().getColumnCount();
			StringBuffer obj = new StringBuffer();

			for (int i = 0; i < total_rows; i++) {

				obj.append(resultSet.getObject(i + 1));
			}

			jsonArray.append(obj);
		}

		return jsonArray.toString();
	}

	
	public static JSONArray convertToJsonArray1(ResultSet resultSet) throws Exception {

		
		JSONArray jsonArray = new JSONArray();

		while (resultSet.next()) {

			int total_rows = resultSet.getMetaData().getColumnCount();
			JSONObject obj = new JSONObject();

			for (int i = 0; i < total_rows; i++) {

				obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getString(i + 1));
			}

			jsonArray.put(obj);
		}

		return jsonArray;
	}

	public static JSONObject convertToJsonObject(ResultSet rs) throws Exception {

		JSONObject obj = new JSONObject();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

		while (rs.next()) {

			int numColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numColumns; i++) {

				String column_name = rsmd.getColumnName(i);
				obj.put(column_name, rs.getObject(column_name));

			}
		}

		return obj;
	}

	public static String convertToJsonObject1(ResultSet rs) throws Exception {

		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

		while (rs.next()) {

			int numColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numColumns; i++) {

				String column_name = rsmd.getColumnName(i);
				obj.put(column_name, rs.getObject(column_name));
			}

			jsonArray.put(obj);

		}

		return jsonArray.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	}
}
