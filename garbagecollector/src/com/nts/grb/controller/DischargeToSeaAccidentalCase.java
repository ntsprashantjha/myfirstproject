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
 * Servlet implementation class DischargeToSeaAccidentalCase
 */
@WebServlet("/DischargeToSeaAccidentalCase")
public class DischargeToSeaAccidentalCase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	String stringDate;
	Date date;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargeToSeaAccidentalCase() {
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

		PrintWriter out = response.getWriter();
		System.out.println("from post method of dischargetosea servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		
		  if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) ==
		  0) {
		  
		  System.out.println("****when user is not looged in***");
		  out.print(ReturnResponse.retrnresponse_url()); }
		  
		  else {
		 

		try {

			String timezone = GetShipTimeZone.gettimezone();
			com.nts.grb.model.DischargeToSeaAccidentalCase DischargeToSeaAccCase = new com.nts.grb.model.DischargeToSeaAccidentalCase();

			stringDate = jObj.getString("date");
			String entry_filled_dt[] = stringDate.split("T");

			DischargeToSeaAccCase.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
		
			DischargeToSeaAccCase.setGarbage_category(jObj.getString("garbageCategoryForm"));
			DischargeToSeaAccCase.setGarbage_qty_sea_acc(jObj.getDouble("garbageQtyForm"));

			DischargeToSeaAccCase.setRemark(jObj.getString("remarkForm").toUpperCase());
			DischargeToSeaAccCase.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));

			DischargeToSeaAccCase.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));
			DischargeToSeaAccCase.setShip_pos_latitude_loc(jObj.optString("latSideForm"));

			DischargeToSeaAccCase.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));
			DischargeToSeaAccCase.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));

			DischargeToSeaAccCase.setShip_pos_longitude_loc(jObj.optString("longSideForm"));
			DischargeToSeaAccCase.setEntry_time(jObj.getString("time"));

			DischargeToSeaAccCase.setWater_depth(jObj.optLong("depthForm"));
			DischargeToSeaAccCase.setPrecaution(jObj.getString("precautionForm"));

			DischargeToSeaAccCase.setOfficer_id(
					com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));
			DischargeToSeaAccCase.setFile_name(jObj.optString("fileNameForm"));

			DischargeToSeaAccCase.setAttached_file_size(jObj.optInt("file_sizeForm"));
			DischargeToSeaAccCase.setAttached_file_type(jObj.optString("file_typeForm"));

			DischargeToSeaAccCase.setEnrty_timeZone(timezone);
			DischargeToSeaAccCase.setMasterId(0);

			DischargeToSeaAccCase.setMasterReId(0);
			DischargeToSeaAccCase.setStrikeId(0);

			DischargeToSeaAccCase.setAmendId(0);
			LastEnrtyUpdation lstEntry_Ob = new LastEnrtyUpdation();

			DischargeToSeaAccCase.setEntry_id(lstEntry_Ob.get_crnt_mstr_id());
			Session session = HibernateUtil.buildSessionFactory().openSession();

			Transaction tran = session.beginTransaction();
			session.save(DischargeToSeaAccCase);

			tran.commit();
			lstEntry_Ob.tst(jObj.getString("date"),
					jObj.getString("time"), timezone, "discharge_to_sea_acc", 0, entry_filled_dt[0]);

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
