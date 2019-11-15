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

/**
 * Servlet implementation class DischargeToAshore
 */

@WebServlet("/DischargeToAshore")
public class DischargeToAshore extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DischargeToAshore.class);
	

	JSONObject jObj;

	String stringDate;
	Date date;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargeToAshore() {
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
		String mac="dev";
		System.out.println("*****from discharge to ashore controller*****");

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
				stringDate = jObj.getString("date");

				String entry_filled_dt[] = stringDate.split("T");
				com.nts.grb.model.DischargeToAshore dischrgetoAshore = new com.nts.grb.model.DischargeToAshore();

				dischrgetoAshore.setOfficer_id(
						com.nts.grb.query.ActiveUserInfo.activeUserId(mac));
				
				LastEnrtyUpdation lstEntryOb = new LastEnrtyUpdation();
				dischrgetoAshore.setEntry_id(lstEntryOb.get_crnt_mstr_id());
					
				dischrgetoAshore.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
				dischrgetoAshore.setGarbage_category(jObj.getString("garbageCategoryForm"));

				dischrgetoAshore.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));
				dischrgetoAshore.setRemark(jObj.getString("remarkForm").toUpperCase());

				if (jObj.optDouble("latDegForm") != 0 || jObj.optString("longSideForm").equals(null)) {

					dischrgetoAshore.setShip_pos_latitude_degree(null);
					dischrgetoAshore.setShip_pos_latitude_min(null);

					dischrgetoAshore.setShip_pos_longitude_loc(jObj.optString("longSideForm"));
					dischrgetoAshore.setShip_pos_latitude_loc(jObj.optString("latSideForm"));

					dischrgetoAshore.setShip_pos_longitude_degree(null);
					dischrgetoAshore.setShip_pos_longitude_min(null);

				}

				else {

					dischrgetoAshore.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
					dischrgetoAshore.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

					dischrgetoAshore.setShip_pos_longitude_loc(jObj.optString("longSideForm"));
					dischrgetoAshore.setShip_pos_latitude_loc(jObj.optString("latSideForm"));

					dischrgetoAshore.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));
					dischrgetoAshore.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));

				}
				
				dischrgetoAshore.setEntry_time(jObj.getString("time"));
				dischrgetoAshore.setFile_name(jObj.optString("fileNameForm"));

				dischrgetoAshore.setAttached_file_size(jObj.optInt("file_sizeForm"));
				dischrgetoAshore.setAttached_file_type(jObj.optString("file_typeForm"));

				dischrgetoAshore.setPort_name(jObj.optString("portForm").toUpperCase());
				dischrgetoAshore.setMasterId("0");

				dischrgetoAshore.setMasterReId(0);
				dischrgetoAshore.setStrikeId("0");

				dischrgetoAshore.setAmendId(0);
				dischrgetoAshore.setEntry_timezone(timezone);

				Session session = HibernateUtil.buildSessionFactory().openSession();
				Transaction tran = session.beginTransaction();

				session.save(dischrgetoAshore);
				tran.commit();
				
				lstEntryOb.tst(jObj.getString("date"), jObj.getString("time"),
						timezone, "Discharge_To_Ashore", 0,entry_filled_dt[0]);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				out.print(ReturnResponse.retrnresponse_wrong_json());
				e.printStackTrace();

			}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print(ReturnResponse.retrnresponse_db_error());

			}

			out.print(ReturnResponse.success_condition());

		}
	}
}
