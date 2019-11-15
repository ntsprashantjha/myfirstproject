package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import com.nts.mrb.dao.GetDetails;
import com.nts.mrb.dao.NewUsrModuleAccess;

@WebServlet("/ModuleAccessDetailsUpdate")
public class ModuleAccessDetailsUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ModuleAccessDetailsUpdate() {

		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("***access details servlet is called***");
		
		PrintWriter out = response.getWriter();
		JSONObject jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("*************" + jObj);

		try {

			// ***************For Audit****************
			// Getting User Ip Address
			String mac = GetUserMacId.getClientMACAddress(request);
			System.out.println("User MAc ID : - " + mac);

			String oldaccdet = GetDetails.getModuleAccessRightsByUser(Integer.toString(jObj.getInt("rank_id")));

			// Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");

			if (userid == null)
				throw new Exception("Emp ID Not Available");

			System.out.println("User Is : - " + userid);

			new NewUsrModuleAccess().updateRankndAccess(jObj.getInt("rank_id"), jObj.getString("grb_part1"),
					jObj.getString("grb_part2"), jObj.getString("orb_part1"), jObj.getString("orb_part2"),
					jObj.getString("crb"), jObj.getString("annex6"));

			String newaccdet = GetDetails.getModuleAccessRightsByUser(Integer.toString(jObj.getInt("rank_id")));

			// Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid, "EDIT",
					"Module Access Details Updated Rank ID :- " + Integer.toString(jObj.getInt("rank_id")), oldaccdet,
					newaccdet, mac, "MRB");

			out.print(ReturnResponse.success_condition());

		} catch (SQLException | JSONException e) {

			e.printStackTrace();
			out.print(ReturnResponse.retrnresponse_wrong_json());

		} catch (Exception e) {

			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();

		}

		out.print(ReturnResponse.success_condition());
	}

}