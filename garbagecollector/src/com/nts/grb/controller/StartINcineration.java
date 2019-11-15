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
 * Servlet implementation class StartINcineration
 */
@WebServlet("/StartINcineration")
public class StartINcineration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	
	String stringDate;
	Date date;
	
	int isIncAlw = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartINcineration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);

		System.out.println(jObj + "-:value of  json");

		if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

			System.out.println("****when user is not looged in***");
			out.print(ReturnResponse.retrnresponse_url());

		}

		else {

			try {

				String timezone = GetShipTimeZone.gettimezone();
				com.nts.grb.model.StartINcineration strt_incineration = new com.nts.grb.model.StartINcineration();

				stringDate = jObj.getString("date");
				String entry_filled_dt[] = stringDate.split("T");
				
				LastEnrtyUpdation lstEntry_Ob = new LastEnrtyUpdation();
				strt_incineration.setEntry_id(lstEntry_Ob.get_crnt_mstr_id());
				
				strt_incineration.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
				strt_incineration.setGarbage_category(jObj.getString("garbageCategoryForm"));

				strt_incineration.setRemark(jObj.getString("remark2Form").toUpperCase());
				strt_incineration.setIncineration_remark(jObj.getString("remark1Form").toUpperCase());

				strt_incineration.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
				strt_incineration.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

				strt_incineration.setShip_pos_latitude_loc(jObj.optString("latSideForm"));
				strt_incineration.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));

				strt_incineration.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));
				strt_incineration.setShip_pos_longitude_loc(jObj.optString("longSideForm"));

				strt_incineration.setEntry_time(jObj.getString("time"));
				strt_incineration.setFile_name(jObj.optString("fileNameForm"));

				strt_incineration.setAttached_file_size(jObj.optInt("file_sizeForm"));
				strt_incineration.setAttached_file_type(jObj.optString("file_typeForm"));

				strt_incineration.setEntry_timezone(timezone);
				strt_incineration.setOfficer_id(
						com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

				strt_incineration.setMasterId(0);
				strt_incineration.setMasterReId(0);

				strt_incineration.setStrikeId(0);
				strt_incineration.setAmendId(0);

				Session session = HibernateUtil.buildSessionFactory().openSession();
				Transaction tran = session.beginTransaction();

				session.save(strt_incineration);
				tran.commit();
				lstEntry_Ob.strt_inc(jObj.getString("date"),
						jObj.getString("time"), timezone, "start_Incineration", 1,jObj.getString("garbageCategoryForm"),entry_filled_dt[0]);


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
