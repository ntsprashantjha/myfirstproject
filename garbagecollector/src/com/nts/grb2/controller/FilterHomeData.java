package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.model.AllFilters;
import com.nts.grb.util.Dateutil;
import com.nts.grb2.query.HomeDataFullReportById;
import com.nts.grb2.service.FilterDtls_GRB2;

/**
 * Servlet implementation class FilterHomeData
 */
@WebServlet("/FilterHomeData")
public class FilterHomeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public FilterHomeData() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		AllFilters allFilters = new AllFilters();
		String[] startDate, endDate;

		PrintWriter out = response.getWriter();
		JSONObject jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("HEOM DATA FILTER:---" + jObj);

		try {

			
			allFilters.setEntrytype(jObj.optString("sea_ashore"));
			allFilters.setCategory(jObj.optString("garbage_category"));

			allFilters.setTablename1(jObj.optString("sea_ashore").replaceAll(" ", "_"));
			allFilters.setStatus(jObj.optString("approval_pending"));

			startDate = jObj.optString("start_date").split("T");
			endDate = jObj.optString("stop_date").split("T");

			/*
			 * Date ss=new Date(jObj.optString("start_date"));
			 * System.out.println("huaishxnius:==--"+ss);
			 */

			allFilters.setFordate(endDate[0]);
			allFilters.setTodate(startDate[0]);
			System.out.println(endDate[0]+"\n"+startDate[0]);
			
			JSONArray abc=HomeDataFullReportById.entry_id(startDate[0], endDate[0],dbConnection.getConnection());

			//abc = new FilterDtls_GRB2().getDtls(allFilters);
			System.out.println(abc);

			out.print(abc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
