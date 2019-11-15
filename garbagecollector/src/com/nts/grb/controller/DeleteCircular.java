package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class DeleteCircular
 */
@WebServlet("/DeleteCircular")
public class DeleteCircular extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCircular() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mac="dev";
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		if (ActiveUserStatus.userstatus(mac) == 0) {
			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());
		} else {
			try {
				System.out.println("******"+jObj);
				new com.nts.grb.dao.ReturnCircularData().deleteCircularInfo(jObj.getInt("circular_id"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print(ReturnResponse.retrnresponse_wrong_json());
				e.printStackTrace();
			}
			out.print(ReturnResponse.success_condition());

		}

	}

}
