package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.controller.DischargeToSea;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.Dateutil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.grb2.query.LastEnrtyUpdation_GRB2;
import com.nts.grb2.util.HibernateUtilGRB2;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class DischargeToSeaStartDeckWashing
 */
@WebServlet("/DischargeToSeaStartDeckWashing")
public class DischargeToSeaStartDeckWashing extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DischargeToSeaStartDeckWashing.class);


	JSONObject jObj;
	String stringDate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargeToSeaStartDeckWashing() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		logger.setLevel(Level.ALL);

		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("**json data from discharge to sea start deck washing controller**" + jObj);

		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		} else {

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

				System.out.println("***** when user is not looged in ****");

				out.print(ReturnResponse.retrnresponse_url());
			}

			else {

				try {

					stringDate = jObj.getString("date");
					System.out.println(stringDate);

					String entry_filled_dt[] = stringDate.split("T");
					com.nts.grb2.model.DischargeToSeaStartDeckWashing dischtoseasrtdkwsh = new com.nts.grb2.model.DischargeToSeaStartDeckWashing();

					String timezone = GetShipTimeZone.gettimezone();

					dischtoseasrtdkwsh.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));

					// gettting active user id
					dischtoseasrtdkwsh.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					dischtoseasrtdkwsh.setEnrty_timeZone(timezone);

					dischtoseasrtdkwsh.setEntry_time(jObj.getString("time"));
					dischtoseasrtdkwsh.setFile_name(jObj.optString("fileNameForm"));

					dischtoseasrtdkwsh.setGarbage_category(jObj.getString("garbageCategoryForm"));
					dischtoseasrtdkwsh.setRemark(jObj.getString("remarkForm").toUpperCase());

					dischtoseasrtdkwsh.setShip_pos_latitude_degree(jObj.getDouble("latDegForm"));
					dischtoseasrtdkwsh.setShip_pos_latitude_loc(jObj.getString("latSideForm"));
					dischtoseasrtdkwsh.setShip_pos_latitude_min(jObj.getDouble("latMinForm"));

					dischtoseasrtdkwsh.setShip_pos_longitude_degree(jObj.getDouble("longDegForm"));
					dischtoseasrtdkwsh.setShip_pos_longitude_loc(jObj.getString("longSideForm"));
					dischtoseasrtdkwsh.setShip_pos_longitude_min(jObj.getDouble("longMinForm"));

					dischtoseasrtdkwsh.setAttached_file_type(jObj.optString("file_typeForm"));
					dischtoseasrtdkwsh.setAttached_file_size(jObj.optInt("file_sizeForm"));

					dischtoseasrtdkwsh.setMasterID("0");
					dischtoseasrtdkwsh.setMasterReId(0);
					dischtoseasrtdkwsh.setStrikeId("0");

					LastEnrtyUpdation_GRB2 lsEntryUpdt = new LastEnrtyUpdation_GRB2();

					dischtoseasrtdkwsh.setEntry_id(lsEntryUpdt.get_crnt_mstr_id());

					Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();
					session.save(dischtoseasrtdkwsh);

					tran.commit();
					lsEntryUpdt.strt_deck_wash(jObj.getString("date"), jObj.getString("time"), timezone,
							"start_deck_washing_grb2", 1, jObj.getString("garbageCategoryForm"), entry_filled_dt[0]);
					
					Thread.sleep(500);
					out.print(ReturnResponse.success_condition());

				} catch (JSONException e) {

					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();

				} catch (SQLException e) {
					out.print(ReturnResponse.retrnresponse_db_error());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

}
