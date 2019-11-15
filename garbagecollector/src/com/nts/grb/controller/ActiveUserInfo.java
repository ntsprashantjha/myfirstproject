package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;

/**
 * Servlet implementation class ActiveUserInfo
 */
@WebServlet("/ActiveUserInfo")
public class ActiveUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject njObj;
	
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActiveUserInfo() {
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
		com.nts.grb.query.ActiveUserInfo activeUserInfo = new com.nts.grb.query.ActiveUserInfo();

		njObj = new JSONObject();
		System.out.println("Obj:\n" + njObj);
		System.out.println("Active User");
		//String js = njObj.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			
			

			String mac = GetUserMacId.getClientMACAddress(request);

			String detail = activeUserInfo.activateUser(mac);
			System.out.println("---active user mac id---" + detail);

			out.print(StringToJsonConverter.convrt(detail));

		}

		catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doGet(request, response);
		//
		// TODO Auto-generated catch block e.printStackTrace(); }

	}

}
