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
 * Servlet implementation class StopINcinerationC
 */
@WebServlet("/StopINcinerationC")
public class StopINcinerationC extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	String stringDate;
	Date date;
	int isIncAlw = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StopINcinerationC() {
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

		System.out.println("from get");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		System.out.println("from post method of StopINcinerationC servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());

		}

		else {

			try {
				String timezone = GetShipTimeZone.gettimezone();
				com.nts.grb.model.StopINcineration stop_incineration = new com.nts.grb.model.StopINcineration();

				stringDate = jObj.getString("date");
				String entry_filled_dt[] = stringDate.split("T");

				LastEnrtyUpdation lst_Entry_Obj = new LastEnrtyUpdation();
				stop_incineration.setEntry_id(lst_Entry_Obj.get_crnt_mstr_id());

				stop_incineration.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
				stop_incineration.setGarbage_category(LastEntryDetails.lst_Strt_inc_cate());

				stop_incineration.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));
				stop_incineration.setRemark(jObj.getString("remark2Form").toUpperCase());

				stop_incineration.setIncineration_remark(jObj.getString("remark1Form").toUpperCase());
				stop_incineration.setShip_pos_latitude_degree(jObj.getDouble("latDegForm"));

				stop_incineration.setShip_pos_latitude_min(jObj.getDouble("latMinForm"));
				stop_incineration.setShip_pos_latitude_loc(jObj.getString("latSideForm"));

				stop_incineration.setShip_pos_longitude_degree(jObj.getDouble("longDegForm"));
				stop_incineration.setShip_pos_longitude_min(jObj.getDouble("longMinForm"));

				stop_incineration.setShip_pos_longitude_loc(jObj.getString("longSideForm"));
				stop_incineration.setEntry_time(jObj.getString("time"));

				stop_incineration.setFile_name(jObj.optString("fileNameForm"));
				stop_incineration.setAttached_file_size(jObj.optInt("file_sizeForm"));

				stop_incineration.setAttached_file_type(jObj.optString("file_typeForm"));
				stop_incineration.setEnrty_timeZone(timezone);

				stop_incineration.setOfficer_id(
						com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));
				stop_incineration.setMasterId("0");

				stop_incineration.setMasterReId("0");
				stop_incineration.setStrikeId("0");

				stop_incineration.setAmendId("0");

				Session session = HibernateUtil.buildSessionFactory().openSession();
				Transaction tran = session.beginTransaction();

				session.save(stop_incineration);
				tran.commit();
				lst_Entry_Obj.tst(jObj.getString("date"), jObj.getString("time"), timezone, "stop_incineration", 0,
						entry_filled_dt[0]);
			}

			catch (JSONException e) {

				// TODO Auto-generated catch block
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
