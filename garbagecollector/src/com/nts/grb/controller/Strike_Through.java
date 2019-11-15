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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.model.DischargeToSea;
import com.nts.grb.model.DischargeToAshore;
import com.nts.grb.model.DischargeToSeaAccidentalCase;
import com.nts.grb.model.StopINcineration;
import com.nts.grb.model.StopINcinerationAccidentalCase;
import com.nts.grb.model.StartINcineration;
import com.nts.grb.model.StartINcinerationAccidentalCase;
import com.nts.grb.query.ActiveMacUsrId;
import com.nts.grb.query.ApprovalCondtionCheck;
import com.nts.grb.query.StrikeThroughUpdation;
import com.nts.grb.service.GetCurrentDateTime;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.HibernateUtil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class Strike_Through
 */
@WebServlet("/Strike_Through")
public class Strike_Through extends HttpServlet {
	JSONObject jObj;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Strike_Through() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stu

		System.out.println("***strike_through controller is called***");
		PrintWriter out = response.getWriter();
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
				System.out.println("active user id:--" + activeuser);
				StrikeThroughUpdation strke_thro_updtion_ob = new StrikeThroughUpdation();
				Session session = HibernateUtil.buildSessionFactory().openSession();
				session.beginTransaction();
				// ******************************************************************************************************

				if (jObj.getString("entryType").equals("discharge_to_sea")) {

					DischargeToSea dis_Chrge_C = new DischargeToSea();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (DischargeToSea) session.get(DischargeToSea.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrike_comment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId("1");

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("discharge_to_ashore")) {

					DischargeToAshore dis_Chrge_C = new DischargeToAshore();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (DischargeToAshore) session.get(DischargeToAshore.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId("1");

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("discharge_to_sea_acc")) {

					DischargeToSeaAccidentalCase dis_Chrge_C = new DischargeToSeaAccidentalCase();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (DischargeToSeaAccidentalCase) session.get(DischargeToSeaAccidentalCase.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId(1);

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("stop_incineration_acc")) {

					StopINcinerationAccidentalCase dis_Chrge_C = new StopINcinerationAccidentalCase();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (StopINcinerationAccidentalCase) session.get(StopINcinerationAccidentalCase.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId("1");

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("stop_incineration")) {

					StopINcineration dis_Chrge_C = new StopINcineration();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (StopINcineration) session.get(StopINcineration.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId("1");

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("start_incineration")) {

					StartINcineration dis_Chrge_C = new StartINcineration();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (StartINcineration) session.get(StartINcineration.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId(1);

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				} else if (jObj.getString("entryType").equals("start_incineration_acc")) {

					StartINcinerationAccidentalCase dis_Chrge_C = new StartINcinerationAccidentalCase();

					dis_Chrge_C.setEntry_id(jObj.getInt("entryID"));
					dis_Chrge_C = (StartINcinerationAccidentalCase) session.get(StartINcinerationAccidentalCase.class,
							strke_thro_updtion_ob.get_primary_key_of_entry());

					dis_Chrge_C.setStrikeComment(jObj.getString("comment").toUpperCase());
					dis_Chrge_C.setStrikeId(1);

					session.update(dis_Chrge_C);
					session.getTransaction().commit();
					session.close();

				}

				JSONArray as = StrikeThroughUpdation.strikeUpdate(jObj.getString("entryType"), jObj.getInt("entryID"),
						activeuser, jObj.getString("comment").toUpperCase(), GetCurrentDateTime.currentDate(),
						ApprovalCondtionCheck.approvalCondtion(jObj.getString("entryType"), jObj.getInt("entryID")));

				out.print(as);

			} catch (JSONException e) { // TODO Auto-generated catch block
				out.print(ReturnResponse.retrnresponse_wrong_json());
				e.printStackTrace();
			}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print(ReturnResponse.retrnresponse_db_error());
				e.printStackTrace();
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print(ReturnResponse.retrnresponse_exception_occured());
			}
		}
	}

}
