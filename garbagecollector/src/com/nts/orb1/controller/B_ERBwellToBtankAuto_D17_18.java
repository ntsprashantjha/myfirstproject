package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.orb1.dao.SoftwareLicenceTime;
import com.nts.orb1.model.BligeERBwellToBtankAutoD17_18Orb1;
import com.nts.orb1.service.AutomaticCheck;
import com.nts.orb1.service.AutomaticEntryGeneration;
import com.nts.orb1.service.CurrentQuanityUpdation;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

@WebServlet("/B_ERBwellToBtankAuto_D17_18")
public class B_ERBwellToBtankAuto_D17_18 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(B_ERBwellToBtankAuto_D17_18.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public B_ERBwellToBtankAuto_D17_18() {
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
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from BligeERBwellToBtankAutoD17_18Orb1 controller**" + jObj);

		// String stringDate = null;
		PrintWriter out = response.getWriter();

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

					// ***************For Audit****************
					// Getting User Ip Address
					String mac = GetUserMacId.getClientMACAddress(request);
					System.out.println("User MAc ID : - " + mac);

					// Getting User Employee Id
					String userid = ((JSONObject) (StringToJsonConverter
							.convrt(new ActiveUserInfo().activateUser1(mac))).get(0)).getString("emp_id");

					if (userid == null)
						throw new Exception("Emp ID Not Available");

					String timezone = GetShipTimeZone.gettimezone();

					BligeERBwellToBtankAutoD17_18Orb1 obj = new BligeERBwellToBtankAutoD17_18Orb1();

					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(stopdate[0], " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								obj.setStart_time(jObj.getString("start_time"));

								obj.setStop_date(stopdate[0]);
								obj.setStop_time(jObj.getString("stop_time"));

								obj.setDestination_tank(jObj.getString("destination_tank"));

								obj.setQty_transfered(jObj.getDouble("qty_transfered"));

								obj.setQty_retained(jObj.getDouble("qty_retained"));

								MasterTable mstrObj = new MasterTable();

								int entry_id = mstrObj.lastEntry();

								if (AutomaticCheck.isAutomatic(jObj.getString("destination_tank"))) {
									entry_id += 1;
									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankDes(mac,
											jObj.getString("stop_date"), jObj.getString("destination_tank"),
											jObj.getDouble("qty_retained"), jObj.getDouble("qty_transfered"), userid,
											entry_id);
								}

								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								mstrObj.lastEntryUpdation("Engine Room Bilge Wells To Bilge Tank Automatic D17,D18",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0], 0, 0);

								new CurrentQuanityUpdation().currentQuantity(jObj.getString("destination_tank"),
										jObj.getDouble("qty_retained"));

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid,
										"Engine Room Bilge Wells To Bilge Tank Automatic D17,D18",
										"Entry Created In Engine Room Bilge Wells To Bilge Tank D17,18", " ", " ", mac,
										"ORB1");

								out.print(ReturnResponse.success_condition());

							} else {
								out.print(ReturnResponse.entry_date_less_then_DTS_lst_dt());

							}

						}

						else {

							out.print(ReturnResponse.entry_date_less());
						}

					}

					else {

						// Decrease 1 Day From Given Date
						LocalDate start = LocalDate.parse(stopdate[0]).minusDays(1);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");

						String date = formatter.format(start);

						System.out.println(ReturnResponse.previous_data_notavailable(date));
						out.print(ReturnResponse.previous_data_notavailable(date));
					}

				} catch (SQLException e) {

					logger.error(e.getMessage());
					e.printStackTrace();
					out.print(ReturnResponse.retrnresponse_db_error());
				}

				catch (JSONException e) {

					logger.error(e.getMessage());
					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();

				} catch (Exception e) {

					out.print(ReturnResponse.somethingWentWrong());
					e.printStackTrace();
				}
			}
		}
	}

}
