package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.grb.validation.ReturnResponse;
import com.nts.orb1.service.CreateFullPdfBook;

/**
 * Servlet implementation class FullPdfReportOrb1
 */
@WebServlet("/FullPdfReportOrb1")
public class FullPdfReportOrb1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FullPdfReportOrb1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***Report controller is called***");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			System.out.println("****calling createPdfDocument for getting report****");
			
			out.print(CreateFullPdfBook.orb1FileGnrt());

			System.out.println("*****report value is returnd*****");
			System.out.println();

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print(ReturnResponse.somethingWentWrong());

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
	}

}