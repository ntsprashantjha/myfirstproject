package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class LastEntryDetails_GRB2
 */
@WebServlet("/LastEntryDetails_GRB2")
public class LastEntryDetails_GRB2 extends HttpServlet {

	JSONObject njObj;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LastEntryDetails_GRB2() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		
		PrintWriter out = response.getWriter();
		com.nts.grb2.query.LastEntryDetails_GRB2 enSpaceSection1 = new com.nts.grb2.query.LastEntryDetails_GRB2();
			njObj = new JSONObject();
			System.out.println("Obj:\n" + njObj);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			out.print(enSpaceSection1.lstEntry());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("**-GRB2 last entry dtls -**");

	}

}
