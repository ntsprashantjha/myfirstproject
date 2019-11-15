package com.nts.annexvi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.annexvi.model.BunkerLogAnxTnkDet;
import com.nts.annexvi.util.HibernateUtil;
import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.orb1.dao.SoftwareLicenceTime;
import com.nts.annexvi.service.MasterTable;

/**
 * Servlet implementation class SludgeManoptC11_4
 */
@WebServlet("/BunkerLog")
public class BunkerLog extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final Logger logger = Logger.getLogger(BunkerLog.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BunkerLog() {
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
		System.out.println("**json data from Bunker Log ANNEX VI controller**" + jObj);

		PrintWriter out = response.getWriter();

		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		}

		else {

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

				//System.out.println("****when user is not looged in***");
				out.print(ReturnResponse.retrnresponse_url());
			}

			else {

				try {

					// ***************For Audit****************
					// Getting User Ip Address
					String mac = GetUserMacId.getClientMACAddress(request);
					// System.out.println("User MAc ID : - " + mac);

					// Getting User Employee Id
					String userid = ((JSONObject) (StringToJsonConverter
							.convrt(new ActiveUserInfo().activateUser1(mac))).get(0)).getString("emp_id");

					if (userid == null)
						throw new Exception("Emp ID Not Available");

					// System.out.println("User Is : - " + userid);

					String timezone = GetShipTimeZone.gettimezone();

					com.nts.annexvi.model.BunkerLogAnx obj = new com.nts.annexvi.model.BunkerLogAnx();
					com.nts.annexvi.model.BunkerLogAnxTnkDet tnkdet = null;

					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					
					JSONArray jsa = jObj.getJSONArray("tank_details");
					double totalQuantity = 0;
					
					for (int i = 0; i < jsa.length(); i++) {
						totalQuantity += jsa.getJSONObject(i).getDouble("qty_added");
					}
					
					String[] date = jObj.getString("date").split("T");

					obj.setDate(date[0]);
					obj.setDbn_ref_no(jObj.getString("dbn_ref_no"));

					obj.setPort(jObj.getString("port"));
					obj.setBunker_supplier(jObj.getString("bunker_supplier"));

					obj.setDeliver_by(jObj.getString("deliver_by"));
					obj.setFuel_oil_grade(jObj.getString("fuel_oil_grade"));

					obj.setBdn_sulphur(jObj.getDouble("bdn_sulphur"));
					obj.setMarpol_sample_seal_number(jObj.getString("marpol_sample_seal_number"));

					obj.setBdn_compliant(jObj.getString("bdn_compliant"));
					obj.setMarpol_sample_complaint(jObj.getString("marpol_sample_complaint"));

					obj.setLop_issued(jObj.getString("lop_issued"));
					
					if(jObj.getString("lop_issued").equalsIgnoreCase("YES")) {
						
						obj.setLop_issue_remark(jObj.getString("lop_issue_remark"));
						
					}
					
					obj.setTotal_quantity(totalQuantity);

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.saveOrUpdate(obj);
					tran.commit();

					Session session1 = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran1 = session1.beginTransaction();

					
					for (int i = 0; i < jsa.length(); i++) {

						tnkdet = new BunkerLogAnxTnkDet();

						tnkdet.setTank_name(jsa.getJSONObject(i).getString("tank_name"));
						tnkdet.setQty_added(jsa.getJSONObject(i).getDouble("qty_added"));
						tnkdet.setSource_entry_id(obj.getEntry_id());

						session1.saveOrUpdate(tnkdet);

					}

					tran1.commit();

					mstrObj.lastEntryUpdation("BUNKER LOG",jObj.getString("date"),timezone,date[0]);

					// Create Audit Of Following Entry In Audit Table
					CreateAudit.auditUpdate(userid, "BUNKER LOG", "BUNKER LOG", " ", " ", mac, "ANNEX VI");

					out.print(ReturnResponse.success_condition());

				}

				catch (Exception e) {
					
					e.printStackTrace();
					out.print(ReturnResponse.somethingWentWrong());

					
				}

			}

		}
	}
}
