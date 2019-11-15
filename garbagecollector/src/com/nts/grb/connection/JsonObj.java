package com.nts.grb.connection;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonObj {
	JSONObject jObj;

	public JSONObject get_btn_num(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);

		}
		System.out.println("main jsoin data:-------"+sb);
		try {
			String abc = sb.toString().replaceAll("\\[|\\]", "");
			jObj = new JSONObject(abc);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jObj;

	}

	public JSONObject get_btn_num1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);

		}
		System.out.println("main jsoin data:-------"+sb);
		try {
			String abc = sb.toString();
			jObj = new JSONObject(abc);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jObj;

	}

}
