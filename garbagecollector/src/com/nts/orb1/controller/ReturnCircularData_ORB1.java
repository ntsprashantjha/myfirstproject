package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnCircularData_GRB2
 */
@WebServlet("/ReturnCircularData_ORB1")
public class ReturnCircularData_ORB1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnCircularData_ORB1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("uafienceijc");
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try {
		
			out.print(new com.nts.orb1.service.ReturnCircularDataOrb1().circularDetails());
			System.out.println(new com.nts.orb1.service.ReturnCircularDataOrb1().circularDetails());
		
		} catch (Exception e) {
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("uafienceijc");
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try {
		
			out.print(new com.nts.orb1.service.ReturnCircularDataOrb1().circularDetails());
		
		} catch (Exception e) {
		}
	}

}
