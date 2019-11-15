package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.model.AllFilters;
import com.nts.grb.service.DataAccordingTotype;
import com.nts.grb.util.Dateutil;

import testing.FilterDtls;

/**
 * Servlet implementation class HomeReportByFilter
 */
@WebServlet("/HomeReportByFilter")
public class HomeReportByFilter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	JSONArray obj = new JSONArray();
	JSONArray obj1 = new JSONArray();

	AllFilters allFilters = new AllFilters();

	String grbentrytype = "Sea/Ashore";

	String GarbageCat = "G", todate = "", fordate = "", status = "", tablename2 = "Discharge_To_Sea",
			tablename1 = "Discharge_To_Ashore";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public HomeReportByFilter() {
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

		System.out.println("**home dtls filter wise **");
		
		PrintWriter out = response.getWriter();
		JSONObject njObj, jb;

		boolean data_post;
		String[] strtEndDtCombo;

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("home dtls json formt:---s"+jObj);

		try {
			
			allFilters.setEntrytype(tablename1);
			allFilters.setCategory(GarbageCat);

			allFilters.setStatus(status);
			allFilters.setFordate(fordate);

			allFilters.setTodate(todate);
			allFilters.setTablename1(tablename1);

			JSONArray abc = new FilterDtls().getDtls(allFilters);
			out.print(abc);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] startDate, endDate;

		PrintWriter out = response.getWriter();
		JSONObject njObj, jb;

		boolean data_post;
		String[] strtEndDtCombo;

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("json value:--"+jObj);

		try {

			allFilters.setEntrytype(jObj.optString("sea_ashore"));
			allFilters.setCategory(jObj.optString("garbage_category"));

			allFilters.setTablename1(jObj.optString("sea_ashore").replaceAll(" ", "_"));
			allFilters.setStatus(jObj.optString("approval_pending"));

			startDate = jObj.optString("start_date").split("T");
			endDate = jObj.optString("stop_date").split("T");

			allFilters.setFordate(endDate[0]);
			allFilters.setTodate(startDate[0]);

			JSONArray abc = new FilterDtls().getDtls(allFilters);
			System.out.println("********************" + abc);

			out.print(abc);

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
