package com.nts.grb2.controller;

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
import com.nts.grb.query.ApprovalCondtionCheck;
import com.nts.grb.service.GetCurrentDateTime;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ReturnResponse;
import com.nts.grb2.query.Strike_Through_GRB2;

/**
 * Servlet implementation class StrikeThrough_GRB2
 */
@WebServlet("/StrikeThrough_GRB2")
public class StrikeThrough_GRB2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StrikeThrough_GRB2() {
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

		int activeuser;
		try {

			activeuser = ActiveMacUsrId.gettingId(GetUserMacId.getClientMACAddress(request));

			JSONArray as = Strike_Through_GRB2.strikeUpdate("discharge_to_ashore_grb2", 15, activeuser,
					"comment".toUpperCase(), GetCurrentDateTime.currentDate(),
					ApprovalCondtionCheck.approvalCondtion("discharge_to_ashore_grb2", 15));

		}

		catch (SQLException e) {
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

		PrintWriter out = response.getWriter();
		System.out.println("from grb2 strike through entry");

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("json format of grb2 strike through entries controller:---" + jObj);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		int activeuser;
		
		try {

			activeuser = ActiveMacUsrId.gettingId(GetUserMacId.getClientMACAddress(request));

			JSONArray as = Strike_Through_GRB2.strikeUpdate(jObj.getString("entryType"), jObj.getInt("entryID"),
					activeuser, jObj.getString("comment").toUpperCase(), GetCurrentDateTime.currentDate(),
					ApprovalCondtionCheck.approvalCondtion(jObj.getString("entryType"), jObj.getInt("entryID")));
			
			out.print(as);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			out.print(ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print(ReturnResponse.retrnresponse_db_error());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print(ReturnResponse.retrnresponse_exception_occured());

		}

	}

}
