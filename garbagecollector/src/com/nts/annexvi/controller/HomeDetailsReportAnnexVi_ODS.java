package com.nts.annexvi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.nts.annexvi.query.HomeDataFullReportById;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.ReportsStrtEndDate;

/**
 * Servlet implementation class HomeDetailsReport
 */
@WebServlet("/HomeDetailsReportAnnexVi_ODS")
public class HomeDetailsReportAnnexVi_ODS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeDetailsReportAnnexVi_ODS() {
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

		PrintWriter out = response.getWriter();

		Date date = new Date();
		final int MONTH = -30;

		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		String[] strtEndDtCombo = crntEnddt.split("and", 2);

		System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);

		try {
			JSONArray ac = HomeDataFullReportById.entry_id("2019-10-01", strtEndDtCombo[1],
					dbConnection.getConnection(),"ODS LOG");
			out.print(ac);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stu
		
		PrintWriter out = response.getWriter();
		Date date = new Date();
		final int MONTH = -30;

		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		String[] strtEndDtCombo = crntEnddt.split("and", 2);

		System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);

		try {

			JSONArray ac = HomeDataFullReportById.entry_id(crntEnddt, strtEndDtCombo[1],
					dbConnection.getConnection(),"ODS LOG");
			out.print(ac);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
