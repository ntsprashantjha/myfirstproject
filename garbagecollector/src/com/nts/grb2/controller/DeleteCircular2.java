package com.nts.grb2.controller;

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
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class DeleteCircular2
 */
@WebServlet("/DeleteCircular2")
public class DeleteCircular2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCircular2() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);

		// ********************** check the licence time************
		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		} else {

			// ************* check user is active or not *****************
			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

				System.out.println("****when user is not looged in***");
				out.print(ReturnResponse.retrnresponse_url());

			} else {

				try {

					System.out.println("***** Delete Circular info *****" + jObj);
					JSONArray rr = new com.nts.grb2.dao.ReturnCircularData()
							.deleteCircularInfo(jObj.getInt("circular_id"));

				}

				catch (Exception e) {
					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();
				}
				out.print(ReturnResponse.success_condition());
			}
		}
	}
}
