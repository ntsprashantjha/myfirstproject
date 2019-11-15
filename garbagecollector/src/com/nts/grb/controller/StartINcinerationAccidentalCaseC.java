package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

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
import com.nts.grb.query.LastEnrtyUpdation;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.Dateutil;
import com.nts.grb.util.HibernateUtil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.dao.GetShipTimeZone;

/**
 * Servlet implementation class StartINcinerationAccidentalCaseC
 */
@WebServlet("/StartINcinerationAccidentalCaseC")
public class StartINcinerationAccidentalCaseC extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	String stringDate;
	Date date;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartINcinerationAccidentalCaseC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stubs
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("from post method of dischargetosea servlet");

		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("**json data from discharge to sea controller**" + jObj);

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());

		}

		else {

			try {

				String timezone = GetShipTimeZone.gettimezone();
				com.nts.grb.model.StartINcinerationAccidentalCase StartINcinerationAccCase = new com.nts.grb.model.StartINcinerationAccidentalCase();

				stringDate = jObj.getString("date");
				String entry_filled_dt[] = stringDate.split("T");
				
				LastEnrtyUpdation lst_Entry_Obj = new LastEnrtyUpdation();
				StartINcinerationAccCase.setEntry_id(lst_Entry_Obj.get_crnt_mstr_id());

				StartINcinerationAccCase.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
				StartINcinerationAccCase.setGarbage_category(jObj.getString("garbageCategoryForm"));

				StartINcinerationAccCase.setRemark(jObj.getString("remark2Form").toUpperCase());
				StartINcinerationAccCase.setIncineration_remark(jObj.optString("remark1Form").toUpperCase());

				StartINcinerationAccCase.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
				StartINcinerationAccCase.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

				StartINcinerationAccCase.setShip_pos_latitude_loc(jObj.optString("latSideForm"));
				StartINcinerationAccCase.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));

				StartINcinerationAccCase.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));
				StartINcinerationAccCase.setShip_pos_longitude_loc(jObj.optString("longSideForm"));

				StartINcinerationAccCase.setEntry_time(jObj.getString("time"));
				StartINcinerationAccCase.setFile_name(jObj.optString("fileNameForm"));

				StartINcinerationAccCase.setAttached_file_size(jObj.optInt("file_sizeForm"));
				StartINcinerationAccCase.setAttached_file_type(jObj.optString("file_typeForm"));

				StartINcinerationAccCase.setEnrty_timeZone(timezone);
				StartINcinerationAccCase.setOfficer_id(
						com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

				StartINcinerationAccCase.setMasterId(0);
				StartINcinerationAccCase.setMasterReId(0);

				StartINcinerationAccCase.setStrikeId(0);
				StartINcinerationAccCase.setAmendId(0);

				StartINcinerationAccCase.setAccidentalCase(1);
				Session session = HibernateUtil.buildSessionFactory().openSession();

				Transaction tran = session.beginTransaction();
				session.save(StartINcinerationAccCase);
				tran.commit();

				lst_Entry_Obj.strt_inc(jObj.getString("date"), jObj.getString("time"), timezone,
						"Start_Incineration_Acc", 1, jObj.getString("garbageCategoryForm"), entry_filled_dt[0]);
			}

			catch (JSONException e) {

				out.print(ReturnResponse.retrnresponse_wrong_json());
				e.printStackTrace();

			}

			catch (SQLException e) {

				out.print(ReturnResponse.retrnresponse_db_error());

			}
			out.print(ReturnResponse.success_condition());

		}
	}

}
