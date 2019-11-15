package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import com.nts.grb.util.HibernateUtil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class CircularData
 */
@WebServlet("/CircularData")
public class CircularData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CircularData() {
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
		String mac=		GetUserMacId.getClientMACAddress(request);
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from circular controller**" + jObj + "-:value of  json");
		
		if (ActiveUserStatus.userstatus(mac) == 0) {
			out.print(ReturnResponse.retrnresponse_url());
		} 
		
		else {
		
			com.nts.grb.model.CircularData circularData = new com.nts.grb.model.CircularData();
			
			try {
			
				if (!jObj.getString("attach").equals(null)) {
				
					circularData.setCirculerfile(jObj.getString("circular_base_code"));
					circularData.setCircularfiletype(jObj.getString("circular_doc_type"));
					
					circularData.setCircularsize(jObj.getInt("circular_size"));
					circularData.setOfficerinfo(String.valueOf(com.nts.grb.query.ActiveUserInfo.activeUserId(mac)));
					
					circularData.setCircularDesc(jObj.getString("circular_description").toUpperCase());
					Session session = HibernateUtil.buildSessionFactory().openSession();
					
					Transaction tran = session.beginTransaction();
					session.save(circularData);
					
					tran.commit();
				}
				
				else {

					out.print(ReturnResponse.attachedCondNotMatched());
				
				}
			} 
			
			catch (JSONException | SQLException e) {
				
				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_wrong_json());

			}

			out.print(ReturnResponse.success_condition());

		}
	}
	// doGet(request, response);

}
