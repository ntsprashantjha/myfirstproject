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

import org.apache.log4j.Logger;
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
import com.nts.mrb.dao.SoftwareLicenceTime;

/*
 * Servlet implementation class DischargeToSea
 */
@WebServlet("/DischargeToSea")
public class DischargeToSea extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	private static final Logger logger = Logger.getLogger(DischargeToSea.class);
	Date entryDate;
	String enrtyTime;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargeToSea() {
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

		System.out.println("**json data from discharge to sea controller**" + jObj + "-:value of  json");

		// cond check is lic is expire
		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		}

		else {

			
			  if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) ==
			  0) {
			  
			  System.out.println("****when user is not looged in***");
			  out.print(ReturnResponse.retrnresponse_url());
			  
			  }
			   
			  else {
			 
			String mac = "dev";

			try {
				// creating ob of entity class********
				com.nts.grb.model.DischargeToSea dischrgetosea = new com.nts.grb.model.DischargeToSea();
				String timezone = GetShipTimeZone.gettimezone();

				String stringDate = jObj.getString("date");
				String entry_filled_dt[] = stringDate.split("T");

				dischrgetosea.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
				System.out.println(Dateutil.parseDateTime(jObj.getString("date")));

				dischrgetosea.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));
				dischrgetosea.setGarbage_category(jObj.getString("garbageCategoryForm"));

				dischrgetosea.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));
				dischrgetosea.setRemark(jObj.getString("remarkForm").toUpperCase());

				dischrgetosea.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
				dischrgetosea.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

				dischrgetosea.setShip_pos_latitude_loc(jObj.optString("latSideForm"));
				dischrgetosea.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));

				dischrgetosea.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));
				dischrgetosea.setShip_pos_longitude_loc(jObj.optString("longSideForm"));

				dischrgetosea.setEntry_time(jObj.getString("time"));
				dischrgetosea.setFile_name(jObj.optString("fileNameForm"));

				dischrgetosea.setEnrty_timeZone(timezone);
				dischrgetosea.setAttached_file_size(jObj.optInt("file_sizeForm"));

				dischrgetosea.setAttached_file_type(jObj.optString("file_typeForm"));
				dischrgetosea.setMasterID("0");

				dischrgetosea.setMasterReId("0");
				dischrgetosea.setStrikeId("0");

				dischrgetosea.setAmendId("0");
				LastEnrtyUpdation lst = new LastEnrtyUpdation();
				dischrgetosea.setEntry_id(lst.get_crnt_mstr_id());

				Session session = HibernateUtil.buildSessionFactory().openSession();
				Transaction tran = session.beginTransaction();

				session.save(dischrgetosea);
				tran.commit();
				lst.tst(jObj.getString("date"), jObj.getString("time"), timezone, "Discharge_To_Sea", 0,
						entry_filled_dt[0]);

			} catch (JSONException e) {
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

}
