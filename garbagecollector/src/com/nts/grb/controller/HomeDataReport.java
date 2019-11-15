package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.HomeDataFullReportById;
import com.nts.grb.service.GetLast30Entries;
import com.nts.grb.service.ReportsStrtEndDate;

/**
 * Servlet implementation class HomeDataReport
 */
@WebServlet("/HomeDataReport")
public class HomeDataReport extends HttpServlet {
	JSONObject njObj, jb;
	private static final long serialVersionUID = 1L;
	boolean data_post;
	String[] strtEndDtCombo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		strtEndDtCombo = crntEnddt.split("and", 2);
		System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);
		try {
			// String add = HomeDataFullReport.getHomeRprtDtls("2019-05-07", "2019-05-01");
			/*
			 * JSONArray ac=HomeDataFullReportById.entry_id(strtEndDtCombo[0],
			 * strtEndDtCombo[1],dbConnection.getConnection()); out.print(ac);
			 */
			JSONArray ac1 = new GetLast30Entries().getEntryDtls();
			out.print(ac1);

		} catch (Exception e) {
			// System.out.println("***&&&*&*&*&*******:-"+HomeDataFullReport.getHomeRprtDtls(strtEndDtCombo[1],
			// strtEndDtCombo[0]));
			// System.out.println("arrray" + njO);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		strtEndDtCombo = crntEnddt.split("and", 2);
		// System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			// out.print(convrt(HomeDataFullReport.getHomeRprtDtls(strtEndDtCombo[1],
			// strtEndDtCombo[0])));
			/*
			 * JSONArray ac = HomeDataFullReportById.entry_id(strtEndDtCombo[0],
			 * strtEndDtCombo[1], dbConnection.getConnection()); out.print(ac);
			 */
			JSONArray ac1 = new GetLast30Entries().getEntryDtls();
			out.print(ac1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static JSONArray convrt(String data) throws JSONException {
		/******************* Json String to Json Array ******************************/

		// System.out.println("Json String: " + data);

		JSONArray jsonArr = new JSONArray(data);
		JSONArray dataArr = new JSONArray();
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);

			// System.out.println(jsonObj);
			dataArr.put(jsonObj);
		}

		// System.out.println("Data Json Array: " + dataArr);
		return dataArr;
	}

}
