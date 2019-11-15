package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.GarbageCategoryData;
import com.nts.grb2.query.GarbageCategoryData_grb2;

/**
 * Servlet implementation class QuantityTableDtls
 */
@WebServlet("/QuantityTableDtls")
public class QuantityTableDtls extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuantityTableDtls() {
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
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8"); 
		System.out.println(new GarbageCategoryData_grb2().bs("20-07-2019", "01-08-2019"));
		out.print(new GarbageCategoryData_grb2().bs("20-07-2019", "04-08-2019"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		System.out.println("from post garbageQuantityData servlet");

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("value of  json:---" + jObj);

		try {

			String a[] = jObj.getString("fromDate").substring(0, 10).split("-");
			String b[] = jObj.getString("toDate").substring(0, 10).split("-");
			String aa = a[2] + "-" + a[1] + "-" + a[0];
			String bb = b[2] + "-" + b[1] + "-" + b[0];
			System.out.println("date for getting data:---" + aa + "\n" + bb);
			String rtrn_value = new GarbageCategoryData_grb2().bs(aa, bb);
			System.out.println("return value:----" + rtrn_value);
			out.print(rtrn_value);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
