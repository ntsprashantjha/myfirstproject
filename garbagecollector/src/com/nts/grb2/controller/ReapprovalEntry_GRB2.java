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
import com.nts.grb.service.GetCurrentDateTime;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class ReapprovalEntry_GRB2
 */
@WebServlet("/ReapprovalEntry_GRB2")
public class ReapprovalEntry_GRB2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReapprovalEntry_GRB2() {
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
		JSONArray return_statment = com.nts.grb2.query.Approve_Entry_GRB2.reapproveUpdate("stop_deck_washing_grb2",
				28, 40, "annan",
				GetCurrentDateTime.currentDate());	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		System.out.println("from grb2 reapproval entry");

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("json format of grb2 reapprval entries container:---"+jObj);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try {

			int activeuser = ActiveMacUsrId.gettingId(GetUserMacId.getClientMACAddress(request));
			System.out.println("**Active user status:-**" + activeuser);

			if (activeuser == 0) {
				
				out.print(ReturnResponse.retrnresponse_url());
			}

			JSONArray rtrn_dtls = com.nts.grb2.query.Approve_Entry_GRB2.reapproveUpdate(jObj.getString("entryType"),
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
