package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.service.CreateFullBookPdfDocument;
import com.nts.grb.service.CreatePdfDocument;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class FullBookRecord
 */
@WebServlet("/FullBookRecord")
public class FullBookRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FullBookRecord() {
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

		System.out.println("***Report controller is called***");
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			System.out.println("****calling createPdfDocument for getting report****");
			JSONArray ss = CreateFullBookPdfDocument.filegnrate();
			System.out.println(ss);
			out.print(ss);

			System.out.println("*****report value is returnd*****");
			System.out.println();
		}

		catch (Exception e) {
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

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			System.out.println("****calling createPdfDocument for getting  report****");
			out.print(CreateFullBookPdfDocument.filegnrate());
			
			System.out.println("*****report value is returnd*****");
			System.out.println();

		}

		catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			out.print(ReturnResponse.somethingWentWrong());

		}
	}
}
