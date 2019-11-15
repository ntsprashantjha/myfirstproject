package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class RemainDays
 */
@WebServlet("/RemainDays")
public class RemainDays extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemainDays() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//01/14/2012 09:29:58
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("remaining_day", new SoftwareLicenceTime().remainTime());
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(resArr);
		out.print(resArr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("remaining_day", new SoftwareLicenceTime().remainTime());
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(resArr);
	}

}
