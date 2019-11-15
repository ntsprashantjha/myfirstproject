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
 * Servlet implementation class SludgeTnsfrBw2TanksC12_2
 */
@WebServlet("/SludgeFuelRegenerationC12_4")
public class SludgeFuelRegenerationC12_4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SludgeFuelRegenerationC12_4.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SludgeFuelRegenerationC12_4() {
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
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from Regeneration For Fuel 12.4 controller**" + jObj);

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
					com.nts.orb1.model.SludgeFuelRegenerationC12_4Orb1 obj = new com.nts.orb1.model.SludgeFuelRegenerationC12_4Orb1();
					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(jObj.getString("stop_date"), " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								obj.setStop_date(stopdate[0]);

								obj.setSource_tank(jObj.getString("source_sludge_tank"));
								obj.setDestination_tank(jObj.getString("bilge_destination_tank"));

								obj.setQuan_of_sludge_disposed(jObj.getDouble("qty_sludge_disposed"));
								obj.setQuan_of_blidgewater_generated(jObj.getDouble("qty_bilge_water_generated"));

								obj.setQuan_ret_sou_tank(jObj.getDouble("qty_retained_source"));
								obj.setQuan_ret_des_tank(jObj.getDouble("qty_retained_destination"));

								MasterTable mstrObj = new MasterTable();

								int entry_id = mstrObj.lastEntry();
								double currentqtysou = AutomaticEntryGeneration
										.getTankCurrentQnty("qty_retained_source");
								double currentqtydes = AutomaticEntryGeneration
										.getTankCurrentQnty("qty_retained_destination");

								if (AutomaticCheck.isAutomatic(jObj.getString("source_sludge_tank"))) {

									double temp = currentqtysou - jObj.getDouble("qty_sludge_disposed");
									if (jObj.getDouble("qty_retained_source") > temp) {
										entry_id += 1;
									}
								}
								
								if (AutomaticCheck.isAutomatic(jObj.getString("bilge_destination_tank"))) {

									if (jObj.getDouble("qty_retained_destination") > currentqtydes) {
										entry_id += 1;
									}

								}

								
								if (AutomaticCheck.isAutomatic(jObj.getString("source_sludge_tank"))) {
									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankSou(mac, jObj.getString("stop_date"),
											jObj.getString("source_sludge_tank"),
											jObj.getDouble("qty_retained_source"),
											jObj.getDouble("qty_sludge_disposed"), userid,entry_id);
								}
								
								if (AutomaticCheck.isAutomatic(jObj.getString("bilge_destination_tank"))) {
									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankDes(mac, jObj.getString("stop_date"),
											jObj.getString("bilge_destination_tank"),
											jObj.getDouble("qty_retained_destination"),
											jObj.getDouble("qty_bilge_water_generated"), userid,entry_id);
								}
								
								obj.setDestination_fuel_tank(jObj.getString("fuel_oil_tank"));
								obj.setQuantity_fuel_gnerated(jObj.getDouble("qty_retained_in_fuel_tank"));
								
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								mstrObj.lastEntryUpdation("Regeneration Of Fuel From Sludge C_12.4",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0],0,0);

								new CurrentQuanityUpdation().currentQuantity(jObj.getString("bilge_destination_tank"),
										jObj.getDouble("qty_retained_destination"));
								
								new CurrentQuanityUpdation().addQuantity(jObj.getString("fuel_oil_tank"),
										jObj.getDouble("qty_retained_in_fuel_tank"));
								
								new CurrentQuanityUpdation().currentQuantity(jObj.getString("source_sludge_tank"),
										jObj.getDouble("qty_retained_source"));
								
								//Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry()-1, jObj.getString("source_sludge_tank"),
										stopdate[0], jObj.getDouble("qty_retained_source"));
								
								//Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry()-1, jObj.getString("bilge_destination_tank"),
										stopdate[0], jObj.getDouble("qty_retained_destination"));

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Sludge Transfer",
										"Sludge Transfer Between Sludge Tanks", " ", " ", mac, "ORB1");

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
