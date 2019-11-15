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
import com.nts.orb1.model.BilgeDisposalBwBTankToStankD13Orb1;
import com.nts.orb1.service.AutomaticCheck;
import com.nts.orb1.service.AutomaticEntryGeneration;
import com.nts.orb1.service.CurrentQuanityUpdation;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.UpdatePreviousDateQuantity;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

@WebServlet("/B_DisposalBtBtankToStankD13")
public class B_DisposalBtBtankToStankD13 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(B_DisposalBtBtankToStankD13.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public B_DisposalBtBtankToStankD13() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside Servlet");
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from Transfer Between Sludge Tanks controller**" + jObj);

		PrintWriter out = response.getWriter();

		if (new SoftwareLicenceTime().remainTime() < 0) {
			out.print(ReturnResponse.licenceExpire());

		}

		else {
			System.out.println("Licence Verified");

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {
				System.out.println("****when user is not looged in***");
				out.print(ReturnResponse.retrnresponse_url());

			}

			else {
				System.out.println("User Verified");

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
					// ******************************************

					String timezone = GetShipTimeZone.gettimezone();
					BilgeDisposalBwBTankToStankD13Orb1 obj = new BilgeDisposalBwBTankToStankD13Orb1();
					int ofcr_info = com.nts.grb.query.ActiveUserInfo
							.activeUserId(GetUserMacId.getClientMACAddress(request));
					obj.setOfficer_id(ofcr_info);

					System.out.println("Officer Id Verified");
					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(stopdate[0], " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								obj.setStop_date(stopdate[0]);

								obj.setStop_time(jObj.getString("stop_time"));
								obj.setStart_time(jObj.getString("start_time"));

								obj.setDestination_tank(jObj.getString("destination_tank"));
								obj.setSource_tank(jObj.getString("source_tank"));

								obj.setQty_transfered(jObj.getDouble("qty_transfered"));

								obj.setQuan_ret_des_tank(jObj.getDouble("qty_retained_dest"));
								obj.setQuan_ret_sou_tank(jObj.getDouble("qty_retained_src"));

								MasterTable mstrObj = new MasterTable();

								int entry_id = mstrObj.lastEntry();
								double currentqtysou = AutomaticEntryGeneration.getTankCurrentQnty("qty_retained_src");
								double currentqtydes = AutomaticEntryGeneration.getTankCurrentQnty("qty_retained_dest");

								if (AutomaticCheck.isAutomatic(jObj.getString("source_tank"))) {

									double temp = currentqtysou - jObj.getDouble("qty_transfered");
									if (jObj.getDouble("qty_retained_src") > temp) {
										entry_id += 1;
									}
								}

								if (AutomaticCheck.isAutomatic(jObj.getString("destination_tank"))) {

									if (jObj.getDouble("qty_retained_dest") > currentqtydes) {
										entry_id += 1;
									}

								}

								// create automatic
								if (AutomaticCheck.isAutomatic(jObj.getString("source_tank"))) {

									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankSou(mac,
											jObj.getString("stop_date"), jObj.getString("source_tank"),
											jObj.getDouble("qty_retained_src"), jObj.getDouble("qty_transfered"),
											userid, entry_id);
								}

								if (AutomaticCheck.isAutomatic(jObj.getString("destination_tank"))) {

									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankDes(mac,
											jObj.getString("stop_date"), jObj.getString("destination_tank"),
											jObj.getDouble("qty_retained_dest"), jObj.getDouble("qty_transfered"),
											userid, entry_id);
								}

								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								mstrObj.lastEntryUpdation("Disposal of Bilge From Bilge Tank to Sludge Tank",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0], 0, 0);

								com.nts.orb1.model.SludgeManualC11_4Orb1 sludge_manaul_obj = new com.nts.orb1.model.SludgeManualC11_4Orb1();

								sludge_manaul_obj.setEntry_id(mstrObj.lastEntry());
								sludge_manaul_obj.setDestination_tank(jObj.getString("destination_tank"));

								sludge_manaul_obj.setQuantity_transferred(jObj.getDouble("qty_transfered"));
								sludge_manaul_obj.setSource(jObj.getString("source_tank"));

								sludge_manaul_obj.setQuan_ret_des_tank(jObj.getDouble("qty_retained_dest"));
								sludge_manaul_obj.setOfficer_id(ofcr_info);

								sludge_manaul_obj.setStart_date(startdate[0]);
								sludge_manaul_obj.setStop_date(stopdate[0]);

								Session session_bToS = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran_bToS = session_bToS.beginTransaction();

								session_bToS.save(sludge_manaul_obj);
								tran_bToS.commit();

								mstrObj.lastEntryUpdation("Sludge Collected by manual operation C11.4",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0], 0,
										obj.getEntry_id());

								new CurrentQuanityUpdation().currentQuantity(
										jObj.getString("source_tank").toUpperCase(),
										jObj.getDouble("qty_retained_src"));

								new CurrentQuanityUpdation().currentQuantity(
										jObj.getString("destination_tank").toUpperCase(),
										jObj.getDouble("qty_retained_dest"));

								// Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry() - 1,
										jObj.getString("source_tank"), stopdate[0], jObj.getDouble("qty_retained_src"));

								// Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry() - 1,
										jObj.getString("destination_tank"), stopdate[0],
										jObj.getDouble("qty_retained_dest"));

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Bilge Transfer",
										"Disposal of Bilge From Bilge Tank to Sludge Tank", " ", " ", mac, "ORB1");

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