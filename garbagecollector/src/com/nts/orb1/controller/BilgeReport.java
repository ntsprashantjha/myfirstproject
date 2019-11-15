package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.orb1.service.BilgeReportData;

/**
 * Servlet implementation class SludgeReport
 */
@WebServlet("/BilgeReport")
public class BilgeReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BilgeReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// JSON Data From FrontEnd

			//JSONArray j1 = SludgeReportData.getReportData(startDate[0], endDate[0]);
			JSONArray j1;
			try {
				j1 = BilgeReportData.getReportData("2019-10-01", "2019-10-18");
				out.println(j1);
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

		PrintWriter out = response.getWriter();
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		// JSON Data From FrontEnd
		// {"fromDate":"2019-10-04T06:50:13.000Z","toDate":"2019-11-05T06:50:13.000Z"}
		System.out.println("JSON Data From FrontEnd " + jObj);

		try {

			String startDate[] = jObj.getString("fromDate").split("T");
			String endDate[] = jObj.getString("toDate").split("T");
			
			System.out.println(startDate[0]);
			System.out.println(endDate[0]);

			JSONArray j1 = BilgeReportData.getReportData(startDate[0], endDate[0]);
			//JSONArray j1 = SludgeReportData.getReportData("2019-10-01", "2019-10-10");
			
			out.println(j1);
			
			System.out.println(j1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
