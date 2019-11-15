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
import com.nts.grb.dao.LoginValidate;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.LoginAudit;

/**
 * Servlet implementation class LoginAccess
 */
@WebServlet("/LoginAccess")
public class LoginAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject njObj, jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("Hello Java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		System.out.println("**from login servlet**");

		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("user details for login:---" + jObj);
		// if (CheckTime.check("2019-08-22T06:31:32.393Z")) {

		try {

			njObj = new JSONObject();
			System.out.println("Obj:\n" + njObj);
			System.out.println("Login Servlet");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String mac = GetUserMacId.getClientMACAddress(request);

			if (LoginValidate.validate(jObj.getString("userid"), jObj.getString("userpass"),

					mac) == true) {

				if (mac.length() == 0) {

					out.print(ReturnResponse.retrnresponse_url());
				}

				out.print(LoginValidate.success_condition(mac));
				LoginAudit.updateloginInfo(jObj.getString("userid"), mac, "MRB");

			}

			else {

				System.out.println("from else");
				out.print(ReturnResponse.wrong_username_pass());
			}

		}

		catch (Exception e) {

			out.print(ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
		}
		/*
		 * } else { ReturnResponse.somethingWentWrong(); }
		 */
	}
}
