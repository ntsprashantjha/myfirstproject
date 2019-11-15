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
import com.nts.orb1.model.BilgePumpingOverboardThroughOWSBWellD13Orb1;
import com.nts.orb1.service.LastEntryCondCheck;
import com.nts.orb1.service.MasterTable;
import com.nts.orb1.service.checkPreviousDateData;
import com.nts.orb1.util.HibernateUtil;

/**
 * Servlet implementation class S_DisposalOfSludgeViaShoreConnection_C12_1
 */
@WebServlet("/B_PumpingOverboardThrghOWSFrmBwell_D13")
public class B_PumpingOverboardThrghOWSFrmBwell_D13 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final Logger logger =
	// Logger.getLogger(S_DisposalOfSludgeViaShoreConnection_C12_1.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public B_PumpingOverboardThrghOWSFrmBwell_D13() {
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
		System.out.println("**json data from 12.1 controller**" + jObj);
//		logger.setLevel(Level.ERROR.FATAL.INFO.WARN);
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
					BilgePumpingOverboardThroughOWSBWellD13Orb1 obj = new BilgePumpingOverboardThroughOWSBWellD13Orb1();
					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] stopdate = jObj.getString("stop_date").split("T");
					String[] startdate = jObj.getString("start_date").split("T");

					if (checkPreviousDateData.checkDate(stopdate[0])) {

						if (new LastEntryCondCheck().lastEntryCheck(stopdate[0], " ") > 0) {

							if (new LastEntryCondCheck().lastEntryDailyTankSoundsheet(stopdate[0], " ") <= 0) {

								obj.setStart_date(startdate[0]);
								System.out.println(obj.getStart_date());
								obj.setStop_date(stopdate[0]);

								obj.setStart_time(jObj.getString("start_time"));
								obj.setStop_time(jObj.getString("stop_time"));

								obj.setStrt_po_la_dgre(jObj.getDouble("start_latdeg"));
								obj.setStrt_po_la_min(jObj.getDouble("start_latmin"));
								obj.setStrt_po_la_dir(jObj.getString("start_latside"));

								obj.setStrt_po_lon_dgre(jObj.getDouble("start_longdeg"));
								obj.setStrt_po_lon_min(jObj.getDouble("start_longmin"));
								obj.setStrt_po_lon_dir(jObj.getString("start_longside"));

								obj.setStop_po_la_dgre(jObj.getDouble("stop_latdeg"));
								obj.setStop_po_la_min(jObj.getDouble("stop_latmin"));
								obj.setStop_po_la_dir(jObj.getString("stop_latside"));

								obj.setStop_po_lon_dgre(jObj.getDouble("stop_longdeg"));
								obj.setStop_po_lon_min(jObj.getDouble("stop_longmin"));
								obj.setStop_po_lon_dir(jObj.getString("stop_longside"));

								obj.setQuantity_discharged(jObj.getDouble("qty_discharged"));

								MasterTable mstrObj = new MasterTable();
								obj.setEntry_id(mstrObj.lastEntry());

								Session session = HibernateUtil.buildSessionFactory().openSession();
								Transaction tran = session.beginTransaction();

								session.save(obj);
								tran.commit();

								mstrObj.lastEntryUpdation("Pumping Of Bilge Water Overboard Through OWS From BWell",
										jObj.getString("stop_date"), "", 0, timezone, stopdate[0], 0, 0);

								// Create Audit Of Following Entry In Audit Table
								CreateAudit.auditUpdate(userid, "Bilge Transfer",
										"Pumping Of Bilge Water Overboard Through OWS From BWell", " ", " ", mac,
										"ORB1");

								out.print(ReturnResponse.success_condition().toString());

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

					// logger.error(e.getMessage());
					out.print(ReturnResponse.retrnresponse_db_error());
					e.printStackTrace();
				}

				catch (JSONException e) {

					// logger.error(e.getMessage());
					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					out.print(ReturnResponse.somethingWentWrong());
					e.printStackTrace();
				}
			}
		}
	}

}
