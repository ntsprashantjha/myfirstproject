package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class AccessRight_GRB2
 */
@WebServlet("/AccessRight_ORB1")
public class AccessRight_ORB1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccessRight_ORB1() {
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
		com.nts.orb1.query.AccessRight_dtls usermngmnetinfo = new com.nts.orb1.query.AccessRight_dtls();

		JSONObject njObj = new JSONObject();
		System.out.println("Obj:\n" + njObj);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			out.print(usermngmnetinfo.userManagmentDetails_orb1());
		}

		catch (Exception e) {
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

	}

}
