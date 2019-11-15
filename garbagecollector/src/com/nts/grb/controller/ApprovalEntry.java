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
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.ActiveMacUsrId;
import com.nts.grb.service.GetCurrentDateTime;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class ApprovalEntry
 */
@WebServlet("/ApprovalEntry")
public class ApprovalEntry extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApprovalEntry() {
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

		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		System.out.println("from post approval servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String mac =GetUserMacId.getClientMACAddress(request);
		if (ActiveUserStatus.userstatus(mac) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());

		}

		else {

			try {

				int activeuser = ActiveMacUsrId.gettingId(mac);
				System.out.println("Active user status:--" + activeuser);

				if (activeuser == 0) {

					out.print(ReturnResponse.retrnresponse_url());

				}

				JSONArray return_statment = com.nts.grb.query.ApprovalEntry.approveUpdate(jObj.getString("entryType"),
						jObj.getInt("entryID"), activeuser, jObj.optString("comment").toUpperCase(),
						GetCurrentDateTime.currentDate());

				System.out.println("*****responce from approvalentry class*****" + return_statment);
				out.print(return_statment);

			} catch (JSONException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_wrong_json());

			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_exception_occured());
			}
			

		}
	}

}
