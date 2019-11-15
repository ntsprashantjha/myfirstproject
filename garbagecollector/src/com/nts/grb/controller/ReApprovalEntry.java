package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
 * Servlet implementation class ReApprovalEntry
 */
@WebServlet("/ReApprovalEntry")
public class ReApprovalEntry extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReApprovalEntry() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		System.out.println("from post Reapproval servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());
		}

		else {

			try {

				int activeuser = ActiveMacUsrId.gettingId(GetUserMacId.getClientMACAddress(request));
				System.out.println("**Active user status:-**" + activeuser);

				if (activeuser == 0) {

					out.print(ReturnResponse.retrnresponse_url());

				}

				JSONArray rtrn_dtls = com.nts.grb.query.ReApprovalEntry.reapproveUpdate(jObj.getString("entryType"),
						jObj.getInt("entryID"), activeuser, jObj.optString("comment").toUpperCase(),
						GetCurrentDateTime.currentDate());

				out.print(rtrn_dtls);
			}

			catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_wrong_json());

			}

			catch (JSONException e) {

				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_wrong_json());

			}

		}
	}
}
