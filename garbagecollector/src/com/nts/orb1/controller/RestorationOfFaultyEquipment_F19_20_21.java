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
import com.nts.orb1.model.MissedEntryOrb1;
import com.nts.orb1.model.RestorationOfFaultyEquipmentF19_20_21Orb1;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

/**
 * Servlet implementation class FailureOfOilFilterEquipment_F19_20_21
 */
@WebServlet("/RestorationOfFaultyEquipment_F19_20_21")
public class RestorationOfFaultyEquipment_F19_20_21 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RestorationOfFaultyEquipment_F19_20_21.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestorationOfFaultyEquipment_F19_20_21() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from Restoration Oil Filter controller**" + jObj);

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

					System.out.println("User Is : - " + userid);

					String timezone = GetShipTimeZone.gettimezone();

					com.nts.orb1.model.RestorationOfFaultyEquipmentF19_20_21Orb1 obj = new RestorationOfFaultyEquipmentF19_20_21Orb1();
					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(stopdate[0], " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								boolean missed = false;

								if (!jObj.getString("missed_entry_type").equalsIgnoreCase("no")) {
									missed = true;
									obj.setMissed_entry("YES");
								} else {
									obj.setMissed_entry("NO");
								}

								obj.setStop_date(stopdate[0]);
								obj.setStart_date(stopdate[0]);

								obj.setDate_of_operational(jObj.getString("date_of_operational"));
								obj.setTime_of_operational(jObj.getString("time_of_operational"));

								obj.setTime_of_failure(jObj.getString("time_of_failure"));
								obj.setReason_of_failure(jObj.optString("reason_of_failure"));

								obj.setSeal_number_for_overboard_valve(
										jObj.getString("seal_number_for_overboard_valve"));
								obj.setSeal_valve_no(jObj.getString("seal_valve_no"));

								MasterTable mstrObj = new MasterTable();
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								if (missed) {

									MissedEntryOrb1 obj2 = new MissedEntryOrb1();

									String[] misseddate = jObj.getString("missed_date").split("T");

									obj2.setMissed_entry_type("RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)");
									obj2.setMissed_date(misseddate[0]);

									obj2.setMissed_user_name(jObj.getString("missed_user_name"));
									obj2.setMissed_user_rank(jObj.getString("missed_user_rank"));

									obj2.setSource_entryid(obj.getEntry_id());

									Session session2 = HibernateUtil.buildSessionFactory().openSession();
									Transaction tran2 = session2.beginTransaction();

									session2.saveOrUpdate(obj2);
									tran2.commit();

									mstrObj.lastEntryUpdation("MISSED ENTRY", jObj.getString("stop_date"), "", 0,
											timezone, stopdate[0], 0, 0);

								} else {
									
									mstrObj.lastEntryUpdation("RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)",
											jObj.getString("stop_date"), "", 0, timezone, stopdate[0], 0, 0);
								}

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Oil Filtering",
										"RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)", " ", " ", mac, "ORB1");

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

				}

				catch (SQLException e) {

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
