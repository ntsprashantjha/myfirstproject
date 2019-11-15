package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
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
import com.nts.grb.query.LastEntryDetails;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.Dateutil;
import com.nts.grb.util.HibernateUtil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.dao.GetShipTimeZone;

/**
 * Servlet implementation class StopINcinerationAccidentalCaseC
 */
@WebServlet("/StopINcinerationAccidentalCaseC")
public class StopINcinerationAccidentalCaseC extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	String stringDate;
	Date date;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StopINcinerationAccidentalCaseC() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		System.out.println("from post method of Stop inc Acc servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());

		}

		else {

			try {

				String timezone = GetShipTimeZone.gettimezone();
				com.nts.grb.model.StopINcinerationAccidentalCase StopINcinerationAccCase = new com.nts.grb.model.StopINcinerationAccidentalCase();

				stringDate = jObj.getString("date");
				String entry_filled_dt[] = stringDate.split("T");

				LastEnrtyUpdation lst_Entry_Obj = new LastEnrtyUpdation();
				StopINcinerationAccCase.setEntry_id(lst_Entry_Obj.get_crnt_mstr_id());

				StopINcinerationAccCase.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));

				StopINcinerationAccCase.setGarbage_category(LastEntryDetails.lst_Strt_inc_acc_cate());
				StopINcinerationAccCase.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));

				StopINcinerationAccCase.setRemark(jObj.getString("remark1Form").toUpperCase());
				StopINcinerationAccCase.setIncineration_remark(jObj.optString("remark2Form").toUpperCase());

				StopINcinerationAccCase.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
				StopINcinerationAccCase.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

				StopINcinerationAccCase.setShip_pos_latitude_loc(jObj.optString("latSideForm"));
				StopINcinerationAccCase.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));

				StopINcinerationAccCase.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));
				StopINcinerationAccCase.setShip_pos_longitude_loc(jObj.optString("longSideForm"));

				StopINcinerationAccCase.setEntry_time(jObj.getString("time"));
				StopINcinerationAccCase.setFile_name(jObj.optString("fileNameForm"));

				StopINcinerationAccCase.setAttached_file_size(jObj.optInt("file_sizeForm"));
				StopINcinerationAccCase.setAttached_file_type(jObj.optString("file_typeForm"));

				StopINcinerationAccCase.setEnrty_timeZone(timezone);
				StopINcinerationAccCase.setOfficer_id(
						com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

				StopINcinerationAccCase.setMasterId("0");
				StopINcinerationAccCase.setMasterReId("0");

				StopINcinerationAccCase.setStrikeId("0");
				StopINcinerationAccCase.setAmendId("0");

				StopINcinerationAccCase.setAccidentalCase(1);
				Session session = HibernateUtil.buildSessionFactory().openSession();

				Transaction tran = session.beginTransaction();
				session.save(StopINcinerationAccCase);
				tran.commit();

				lst_Entry_Obj.tst(jObj.getString("date"), jObj.getString("time"), timezone, "stop_incineration_acc", 0,
						entry_filled_dt[0]);
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
