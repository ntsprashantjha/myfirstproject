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

import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.HomeDataFullReport;
import com.nts.grb.util.Dateutil;

/**
 * Servlet implementation class ReportbtTwoDates
 */
@WebServlet("/ReportbtTwoDates")
public class ReportbtTwoDates extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	String[] startDate, endDate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportbtTwoDates() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("***Report bt two dates controller is called***");
		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("ghukh********"+jObj + "-:value of  json");
		try {
			startDate = jObj.getString("start_date").split("T");
			endDate = jObj.getString("stop_date").split("T");
			String garbage_category = jObj.optString("garbage_category");
			String approval_pending = jObj.optString("approval_pending");
			String range_date = jObj.optString("range_date");
			String orderBy = jObj.optString("orderBy");
			System.out.println(startDate + "\n" + endDate);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			out.print(new JSONArray(HomeDataFullReport.getHomeRprtDtls(endDate[0],startDate[0])));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
