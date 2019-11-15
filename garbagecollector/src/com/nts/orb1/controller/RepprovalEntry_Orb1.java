package com.nts.orb1.controller;

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
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class ApprovalEntry_GRB2
 */
@WebServlet("/RepprovalEntry_Orb1")
public class RepprovalEntry_Orb1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RepprovalEntry_Orb1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		System.out.println("--from Orb1 reapproval entry--");

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("json format of approval entry:--" + jObj);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {

			int activeuser = ActiveMacUsrId.gettingId(GetUserMacId.getClientMACAddress(request));
			System.out.println("Active user status:--" + activeuser);

			if (activeuser == 0) {

				out.print(ReturnResponse.retrnresponse_url());

			}

			JSONArray return_statment = com.nts.orb1.query.Approve_Entry_ORB1.reapproveUpdate(
					jObj.getString("entryType"), jObj.getInt("entryID"), activeuser,
					jObj.optString("comment").toUpperCase(), GetCurrentDateTime.currentDate());

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
