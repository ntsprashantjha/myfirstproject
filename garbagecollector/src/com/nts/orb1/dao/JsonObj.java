package com.nts.orb1.dao;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObj {
	JSONArray jObj;

	public JSONArray get_btn_num(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);

		}
		System.out.println("sjknjkfs"+sb);
		try {
			String abc = sb.toString();
			jObj = new JSONArray(abc);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jObj;

	}



public static JSONArray capacityExceed() {
		JSONObject responsejson = new JSONObject();

		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message",
					"Tank Capacity Exceeded");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}
}

