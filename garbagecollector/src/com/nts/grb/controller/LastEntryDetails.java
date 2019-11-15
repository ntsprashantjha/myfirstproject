package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class LastEntryDetails
 */
@WebServlet("/LastEntryDetails")
public class LastEntryDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject njObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LastEntryDetails() {
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
		com.nts.grb.query.LastEntryDetails enSpaceSection1 = new com.nts.grb.query.LastEntryDetails();

		try {

			njObj = new JSONObject();
			System.out.println("Obj:\n" + njObj);
			System.out.println("Last Entry Servlet");
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			out.print(enSpaceSection1.lstEntry());

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

	}

}
