package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.UpdateAudit;
import com.nts.mrb.dao.AccessRight;
import com.nts.mrb.model.InsertTimeZoneData;
import com.nts.mrb.model.VesselDetails;

/**
 * Servlet implementation class InsertTimeZone
 */
@WebServlet("/InsertTimeZone")
public class InsertTimeZone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertTimeZone() {
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
		JSONObject njObj = new JSONObject();
		System.out.println("Obj:\n" + njObj);
		String js = njObj.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			out.print(new AccessRight().time_zone_details());
		} catch (Exception e) {
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
		String mac = GetUserMacId.getClientMACAddress(request);
		
		System.out.println("******time zone controller******");
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		
		System.out.println("**json data from add new vessel details**" + jObj + "-:value of  json");
		InsertTimeZoneData insertTimeZone = new InsertTimeZoneData();
		
		Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		
		try {
			
			System.out.println("**********from if*********" + jObj.getString("time_zone"));
			
			insertTimeZone.setTime_zone(jObj.getString("time_zone"));
			insertTimeZone.setId(1);
			
			new UpdateAudit().updateDetails(jObj.getString("time_zone"),mac,"MRB");
			
			session.saveOrUpdate(insertTimeZone);
			tran.commit();
			session.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			out.print(new ReturnResponse().retrnresponse_wrong_json());
			e.printStackTrace();
		}
		out.print(new ReturnResponse().success_condition());

	}

}
