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
import com.nts.orb1.service.AutomaticCheck;
import com.nts.orb1.service.AutomaticEntryGeneration;
import com.nts.orb1.service.CurrentQuanityUpdation;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.UpdatePreviousDateQuantity;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

/**
 * Servlet implementation class S_DrainingOfWaterFromStoB
 */
@WebServlet("/S_DrainingOfWaterFromStoB")
public class S_DrainingOfWaterFromStoB_C12_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(S_DrainingOfWaterFromStoB_C12_2.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S_DrainingOfWaterFromStoB_C12_2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from 12.2 controller**" + jObj);
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

					com.nts.orb1.model.SludgeDrainWtrFrmSludtoBlidTankC12_2Orb1 obj = new com.nts.orb1.model.SludgeDrainWtrFrmSludtoBlidTankC12_2Orb1();
					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(jObj.getString("stop_date"), " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								obj.setStop_date(stopdate[0]);

								obj.setDestination_blidge_tank(jObj.getString("destination_tank").toUpperCase());
								obj.setSource_sludge_tank(jObj.getString("source_tank").toUpperCase());

								obj.setQuantity_transferred(jObj.getDouble("qty_transfered"));

								obj.setQuan_ret_des_tank(jObj.getDouble("qty_retained_destination"));
								obj.setQuan_ret_sou_tank(jObj.getDouble("qty_retained_source"));
								
								MasterTable mstrObj = new MasterTable();

								int entry_id = mstrObj.lastEntry();
								double currentqtysou = AutomaticEntryGeneration
										.getTankCurrentQnty("qty_retained_source");
								double currentqtydes = AutomaticEntryGeneration
										.getTankCurrentQnty("qty_retained_destination");

								if (AutomaticCheck.isAutomatic(jObj.getString("source_tank"))) {

									double temp = currentqtysou - jObj.getDouble("qty_transfered");
									if (jObj.getDouble("qty_retained_source") > temp) {
										entry_id += 1;
									}
								}
								
								if (AutomaticCheck.isAutomatic(jObj.getString("destination_tank"))) {

									if (jObj.getDouble("qty_retained_destination") > currentqtydes) {
										entry_id += 1;
									}

								}

								
								// create automatic
								if (AutomaticCheck.isAutomatic(jObj.getString("source_tank"))) {
									
									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankSou(mac,
											jObj.getString("stop_date"), jObj.getString("source_tank"),
											jObj.getDouble("qty_retained_source"), jObj.getDouble("qty_transfered"),
											userid,entry_id);
								}

								if (AutomaticCheck.isAutomatic(jObj.getString("destination_tank"))) {

									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankDes(mac,
											jObj.getString("stop_date"), jObj.getString("destination_tank"),
											jObj.getDouble("qty_retained_destination"),
											jObj.getDouble("qty_transfered"), userid,entry_id);
								}
								
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								mstrObj.lastEntryUpdation("Draining Water From Sludge To Blidge tank",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0],0,0);

								new CurrentQuanityUpdation().currentQuantity(
										jObj.getString("destination_tank").toUpperCase(),
										jObj.getDouble("qty_retained_destination"));

								new CurrentQuanityUpdation().currentQuantity(
										jObj.getString("source_tank").toUpperCase(),
										jObj.getDouble("qty_retained_source"));

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Sludge Transfer",
										"Draining Water From Sludge To Blidge tank", " ", " ", mac, "ORB1");

								//Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry()-1, jObj.getString("source_tank"),
										stopdate[0], jObj.getDouble("qty_retained_source"));
								
								//Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry()-1, jObj.getString("destination_tank"),
										stopdate[0], jObj.getDouble("qty_retained_destination"));

								
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
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print(ReturnResponse.somethingWentWrong());

				}
			}
		}
	}

}
