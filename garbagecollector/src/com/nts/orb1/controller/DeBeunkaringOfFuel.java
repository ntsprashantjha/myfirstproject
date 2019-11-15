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
import org.json.JSONArray;
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
import com.nts.orb1.model.DeBunkringOfFuelOilTnkDet;
import com.nts.orb1.model.MissedEntryOrb1;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

/**
 * Servlet implementation class DeBeunkaringOfFuel
 */
@WebServlet("/DeBeunkaringOfFuel")
public class DeBeunkaringOfFuel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeBeunkaringOfFuel.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeBeunkaringOfFuel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JSONObject jObj = new JsonObj().get_btn_num1(request, response);
		System.out.println("**json data from getde_bunkering_of_fuel_oil controller**" + jObj);

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

					com.nts.orb1.model.DeBunkeringOfFuelOil obj = new com.nts.orb1.model.DeBunkeringOfFuelOil();
					DeBunkringOfFuelOilTnkDet fOil = null;

					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(stopdate[0], " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								boolean missed = false;

								if (!jObj.getString("missed_entry_type").equalsIgnoreCase("no")) {
									missed = true;
									System.out.println("Missed Entry Equipmet");
									obj.setMissed_entry("YES");
								} else {
									obj.setMissed_entry("NO");
								}

								double total_quantity = 0;

								obj.setStart_date(startdate[0]);
								obj.setStop_date(stopdate[0]);

								obj.setStart_time(jObj.getString("start_time"));
								obj.setStop_time(jObj.getString("stop_time"));

								obj.setPort(jObj.getString("port"));
								obj.setIdentity_of_sludge_receiver(
										jObj.getString("identity_sludge_receiver").toUpperCase());
								obj.setGrade_of_oil(jObj.getString("grade_of_oil"));

								obj.setTotal_quantity(total_quantity);
								obj.setPercentage_sulphur(jObj.getDouble("percentage_sulphur"));

								obj.setFile_name(jObj.getString("file_name"));
								obj.setAttached_file_size(jObj.getInt("file_size"));
								obj.setAttached_file_type(jObj.getString("file_type"));
								obj.setAttach(jObj.getString("attach"));

								MasterTable mstrObj = new MasterTable();
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.saveOrUpdate(obj);
								tran.commit();

								Session session1 = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran1 = session1.beginTransaction();

								JSONArray jsa = jObj.getJSONArray("tank_details");
								for (int i = 0; i < jsa.length(); i++) {

									fOil = new DeBunkringOfFuelOilTnkDet();
									fOil.setSource_entryid(obj.getEntry_id());

									fOil.setQty_added(jsa.getJSONObject(i).getDouble("qty_added"));
									total_quantity = total_quantity + jsa.getJSONObject(i).getDouble("qty_added");

									fOil.setQty_retained(jsa.getJSONObject(i).getDouble("qty_retained"));
									fOil.setTank_name(jsa.getJSONObject(i).getString("tank_name"));

									session.saveOrUpdate(fOil);

								}
								tran1.commit();

								if (missed) {

									MissedEntryOrb1 obj2 = new MissedEntryOrb1();

									String[] misseddate = jObj.getString("missed_date").split("T");

									obj2.setMissed_entry_type("de_bunkering_of_fuel_oil");
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
									mstrObj.lastEntryUpdation("de_bunkering_of_fuel_oil", jObj.getString("stop_date"),
											"", 0, timezone, stopdate[0], 0, 0);
								}

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "de_bunkering_of_fuel_oil", "de_bunkering_of_fuel_oil",
										" ", " ", mac, "ORB1");

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
