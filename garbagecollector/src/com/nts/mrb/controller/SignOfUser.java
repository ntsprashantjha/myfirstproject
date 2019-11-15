package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.LoginValidate;

@WebServlet("/SignOfUser")
public class SignOfUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignOfUser() {

		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		System.out.println("***--Sign Of User--***");

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj);

		try {

			// ***************For Audit****************
			// Getting User Ip Address
			String mac = GetUserMacId.getClientMACAddress(request);
			System.out.println("User MAc ID : - " + mac);

			// Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");

			if (userid == null)
				throw new Exception("Emp ID Not Available");

			System.out.println("User Is : - " + userid);

			String date = jObj.getString("date_of_leaving");
			String finalDate[] = date.split("T");

			out.print(new LoginValidate().signOfUser(jObj.getString("user_id"), Date.valueOf(finalDate[0])));

			// Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid, "Sign-Off", "SignOff User " + jObj.getString("user_id"), " ", " ", mac,
					"MRB");

		} catch (JSONException e) {

			out.print(com.nts.grb.validation.ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();

		} catch (Exception e) {

			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();

		}

	}

}