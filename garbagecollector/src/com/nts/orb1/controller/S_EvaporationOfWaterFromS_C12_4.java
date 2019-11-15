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
 * Servlet implementation class S_EvaporationOfWaterFromS_C12_4
 */
@WebServlet("/S_EvaporationOfWaterFromS_C12_4")
public class S_EvaporationOfWaterFromS_C12_4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(S_EvaporationOfWaterFromS_C12_4.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S_EvaporationOfWaterFromS_C12_4() {
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
		System.out.println("**json data from 12.4 Evapuration controller**" + jObj);

		PrintWriter out = response.getWriter();

		if (new SoftwareLicenceTime().remainTime() < 0) {
			out.print(ReturnResponse.licenceExpire());
		}

		else {
			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {
				System.out.println("****when user is not looged in***");
				out.print(ReturnResponse.retrnresponse_url());
			} else {

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

					com.nts.orb1.model.SludgeTankEvapWater12_4Orb1 obj = new com.nts.orb1.model.SludgeTankEvapWater12_4Orb1();
					obj.setOfficer_id(

							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));
					String[] startdate = jObj.getString("stop_date").split("T");

					if (checkPreviousDateData.checkDate(startdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(jObj.getString("stop_date"), " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(startdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								obj.setStop_date(startdate[0]);
								obj.setSource_tank(jObj.getString("tank_name"));

								obj.setQuantity_evaporated(jObj.getDouble("qty_evaporated"));
								obj.setQuan_ret_sou_tank(jObj.getDouble("qty_retained"));

								MasterTable mstrObj = new MasterTable();

								int entry_id = mstrObj.lastEntry();
																							
								if (AutomaticCheck.isAutomatic(jObj.getString("tank_name"))) {
									entry_id += 1;
									new AutomaticEntryGeneration().automaticEntryByAutoTypeTankSou(mac, jObj.getString("stop_date"),
											jObj.getString("tank_name"),
											jObj.getDouble("qty_retained"),
											jObj.getDouble("qty_evaporated"), userid,entry_id);
								}
								
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Sludge Transfer", "Evaportaion of Water From Sludge",
										" ", " ", mac, "ORB1");

								mstrObj.lastEntryUpdation("Evaportaion of Water From Sludge c12_4_orb1",
										jObj.getString("stop_date"), "", 0, timezone, startdate[0],0,0);

								new CurrentQuanityUpdation().currentQuantity(jObj.getString("tank_name"),
										jObj.getDouble("qty_retained"));

								//Update In Strike Previous Date Data Table
								new UpdatePreviousDateQuantity().updateData(mstrObj.lastEntry()-1, jObj.getString("tank_name"),
										startdate[0], jObj.getDouble("qty_retained"));
								

								
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
						LocalDate start = LocalDate.parse(startdate[0]).minusDays(1);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");

						String date = formatter.format(start);

						System.out.println(ReturnResponse.previous_data_notavailable(date));
						out.print(ReturnResponse.previous_data_notavailable(date));
					}

				} catch (SQLException e) {

					logger.error(e.getMessage());
					e.printStackTrace();
					out.print(ReturnResponse.retrnresponse_db_error());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
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
