package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.service.CreatePdfDocument;

/**
 * Servlet implementation class DownloadReport
 */
@WebServlet("/DownloadReport")
public class DownloadReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	String[] startDate, endDate;

	/**
	 * 
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadReport() {
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
		try {
			System.out.println(CreatePdfDocument.filegnrate("2019-05-20", "2019-04-20"));
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
		// TODO Auto-generated method stub
		System.out.println("***Report controller is called***");
		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:***value of  json from report controller***");
		try {
			startDate = jObj.getString("fromDate").split("T");
			endDate = jObj.getString("toDate").split("T");
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			System.out.println("****calling createPdfDocument for getting report****");
			out.print(CreatePdfDocument.filegnrate(endDate[0], startDate[0]));
			System.out.println("*****report value is returnd*****");
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
