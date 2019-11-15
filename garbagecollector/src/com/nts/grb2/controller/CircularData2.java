package com.nts.grb2.controller;

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
import com.nts.grb2.util.HibernateUtilGRB2;
import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class CircularData2
 */
@WebServlet("/CircularData2")
public class CircularData2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CircularData2() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		PrintWriter out = response.getWriter();
		JSONObject jObj = new JsonObj().get_btn_num(request, response);


		System.out.println("**json data from circular controller**" + jObj );
		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		} else {

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {
			
			out.print(ReturnResponse.retrnresponse_url());
			
		} else {

			com.nts.grb2.model.CircularData2 circularData2 = new com.nts.grb2.model.CircularData2();

			try {

				if (!jObj.getString("attach").equals(null)) {

					circularData2.setCirculerfile(jObj.getString("circular_base_code"));
					
					circularData2.setCircularfiletype(jObj.getString("circular_doc_type"));
					circularData2.setCircularsize(jObj.getInt("circular_size"));
					
					circularData2.setOfficerinfo(String.valueOf(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request))));
					circularData2.setCircularDesc(jObj.getString("circular_description").toUpperCase());

					Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();
					
					session.save(circularData2);
					tran.commit();
				}
				
				else {

					out.print(ReturnResponse.attachedCondNotMatched());
				}

			} catch (JSONException | SQLException e) {

				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_wrong_json());

			}

			out.print(ReturnResponse.success_condition());
		}
	}
	}

}
