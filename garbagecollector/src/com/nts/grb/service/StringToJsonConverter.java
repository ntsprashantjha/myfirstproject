package com.nts.grb.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StringToJsonConverter {
	public static JSONArray convrt(String data) throws JSONException {
		/******************* Json String to Json Array ******************************/
		
		JSONArray jsonArr = new JSONArray(data);
		JSONArray dataArr = new JSONArray();
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			dataArr.put(jsonObj);
		}
System.out.println(dataArr);
		return dataArr;
	}

	public static void main(String arg[]) throws JSONException {
		JSONObject abc = new JSONObject();
		JSONArray dataArr1 = new JSONArray();
		abc.put("abc", "acac");
		dataArr1.put(abc);
		String abc1 = abc.toString();
		System.out.println(StringToJsonConverter.convrt(dataArr1.toString()));
	}

}
