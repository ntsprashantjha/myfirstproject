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
import org.json.JSONObject;

import com.nts.annexvi.service.MasterTable;
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

/**
 * Servlet implementation class SludgeManoptC11_4
 */
@WebServlet("/ODSLog")
public class ODSLog extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final Logger logger = Logger.getLogger(BunkerLog.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ODSLog() {
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
		System.out.println("**json data from ODS Log ANNEX VI controller**" + jObj);

		PrintWriter out = response.getWriter();

		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		}

		else {

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {

				// System.out.println("****when user is not looged in***");
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

					com.nts.annexvi.model.ODS_LogAnx obj = new com.nts.annexvi.model.ODS_LogAnx();

					obj.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					String[] date = jObj.getString("date").split("T");

					obj.setDate(date[0]);
					obj.setEquipment(jObj.getString("equipment"));

					obj.setService_done(jObj.getString("service_done"));
					obj.setAmount_of_ods(jObj.getDouble("amount_of_ods"));

					obj.setReason(jObj.getString("reason"));

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.saveOrUpdate(obj);
					tran.commit();

					mstrObj.lastEntryUpdation("ODS LOG", jObj.getString("date"), timezone, date[0]);

					// Create Audit Of Following Entry In Audit Table
					CreateAudit.auditUpdate(userid, "ODS LOG", "ODS LOG", " ", " ", mac, "ANNEX VI");

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
