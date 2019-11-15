package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
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
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.Dateutil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.grb2.query.LastEnrtyUpdation_GRB2;
import com.nts.grb2.util.HibernateUtilGRB2;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class DischargeToAshore2
 */
@WebServlet("/DischargeToAshore2")
public class DischargeToAshore2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DischargeToAshore2.class);

	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargeToAshore2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jObj;

		String stringDate;
		Date date;

		logger.setLevel(Level.ERROR.FATAL.INFO.WARN);
		PrintWriter out = response.getWriter();

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("from DischargeToAshore3 controller");

		System.out.println("json value" + jObj);

		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		}

		else {

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

				System.out.println("****when user is not looged in***");
				out.print(ReturnResponse.retrnresponse_url());

			}

			else {

				try {

					String timezone = GetShipTimeZone.gettimezone();
					stringDate = jObj.getString("date");

					System.out.println(stringDate);
					String entry_filled_dt[] = stringDate.split("T");

					com.nts.grb2.model.DischargeToAshore2 dischrgetoAshore = new com.nts.grb2.model.DischargeToAshore2();

					dischrgetoAshore.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					dischrgetoAshore.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
					dischrgetoAshore.setGarbage_category(jObj.getString("garbageCategoryForm"));

					dischrgetoAshore.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));
					dischrgetoAshore.setRemark(jObj.getString("remarkForm").toUpperCase());

					if (jObj.optDouble("latDegForm") != 0 || jObj.optString("longSideForm").equals(null)) {

						dischrgetoAshore.setShip_pos_latitude_degree(0);
						dischrgetoAshore.setShip_pos_latitude_min(0);

						dischrgetoAshore.setShip_pos_latitude_loc(null);
						dischrgetoAshore.setShip_pos_longitude_degree(0);

						dischrgetoAshore.setShip_pos_longitude_min(0);
						dischrgetoAshore.setShip_pos_longitude_loc(null);
					} else {

						dischrgetoAshore.setShip_pos_latitude_degree(jObj.optDouble("latDegForm"));
						dischrgetoAshore.setShip_pos_latitude_min(jObj.optDouble("latMinForm"));

						dischrgetoAshore.setShip_pos_latitude_loc(jObj.optString("latSideForm"));
						dischrgetoAshore.setShip_pos_longitude_degree(jObj.optDouble("longDegForm"));

						dischrgetoAshore.setShip_pos_longitude_min(jObj.optDouble("longMinForm"));
						dischrgetoAshore.setShip_pos_longitude_loc(jObj.optString("longSideForm"));
					}

					dischrgetoAshore.setEntry_time(jObj.getString("time"));
					dischrgetoAshore.setFile_name(jObj.optString("fileNameForm"));
					
					dischrgetoAshore.setAttached_file_size(jObj.optInt("file_sizeForm"));
					dischrgetoAshore.setAttached_file_type(jObj.optString("file_typeForm"));

					dischrgetoAshore.setPort_name(jObj.getString("portForm").toUpperCase());

					dischrgetoAshore.setMasterId("0");
					dischrgetoAshore.setMasterReId(0);
					dischrgetoAshore.setStrikeId("0");

					LastEnrtyUpdation_GRB2 lsEntryUpdt = new LastEnrtyUpdation_GRB2();

					dischrgetoAshore.setEntry_id(lsEntryUpdt.get_crnt_mstr_id());

					dischrgetoAshore.setEntry_timezone(timezone);

					Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.save(dischrgetoAshore);
					tran.commit();

					lsEntryUpdt.updt(jObj.getString("date"), jObj.getString("time"), timezone,
							"discharge_to_ashore_grb2", 0, entry_filled_dt[0]);

				} catch (SQLException e) {

					logger.error(e.getMessage());
					e.printStackTrace();
					out.print(ReturnResponse.retrnresponse_db_error());
				}

				catch (JSONException e) {

					logger.error(e.getMessage());
					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();
					
				}
				out.print(ReturnResponse.success_condition());
			}
		}
	}
}
