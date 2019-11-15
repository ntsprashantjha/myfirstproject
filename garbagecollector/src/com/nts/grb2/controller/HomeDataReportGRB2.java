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

import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.ReportsStrtEndDate;
import com.nts.grb2.query.HomeDataFullReportById;

/**
 * Servlet implementation class HomeDataReportGRB2
 */
@WebServlet("/HomeDataReportGRB2")
public class HomeDataReportGRB2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject njObj, jb;
	boolean data_post;
	String[] strtEndDtCombo;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeDataReportGRB2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		strtEndDtCombo = crntEnddt.split("and", 2);
		System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);
		try {
			// String add = HomeDataFullReport.getHomeRprtDtls("2019-05-07", "2019-05-01");
			JSONArray ac=HomeDataFullReportById.entry_id(strtEndDtCombo[0], strtEndDtCombo[1],dbConnection.getConnection());
			System.out.println("jnvakjevna"+ac);
			out.print(ac);
			
		} catch (Exception e) {
			// System.out.println("***&&&*&*&*&*******:-"+HomeDataFullReport.getHomeRprtDtls(strtEndDtCombo[1],
			// strtEndDtCombo[0]));
			// System.out.println("arrray" + njO);
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		strtEndDtCombo = crntEnddt.split("and", 2);
		 System.out.println(strtEndDtCombo[0] + "\n" + strtEndDtCombo[1]);

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			//out.print(convrt(HomeDataFullReport.getHomeRprtDtls(strtEndDtCombo[1], strtEndDtCombo[0])));
			JSONArray ac=HomeDataFullReportById.entry_id(strtEndDtCombo[0], strtEndDtCombo[1],dbConnection.getConnection());
			out.print(ac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
