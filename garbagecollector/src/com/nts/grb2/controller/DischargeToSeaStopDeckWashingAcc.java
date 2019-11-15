package com.nts.grb2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.util.Dateutil;
import com.nts.grb.validation.ActiveUserStatus;
import com.nts.grb.validation.ReturnResponse;
import com.nts.grb2.query.LastEnrtyUpdation_GRB2;
import com.nts.grb2.util.HibernateUtilGRB2;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.mrb.dao.SoftwareLicenceTime;

/**
 * Servlet implementation class DischargeToSeaStopDeckWashingAcc
 */
@WebServlet("/DischargeToSeaStopDeckWashingAcc")
public class DischargeToSeaStopDeckWashingAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DischargeToSeaStopDeckWashingAcc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
	JSONObject	jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("**json data from discharge to sea stop deck washing ac controller**" + jObj);
		if (new SoftwareLicenceTime().remainTime() < 0) {

			out.print(ReturnResponse.licenceExpire());

		}

		else {

			if (ActiveUserStatus.userstatus(GetUserMacId.getClientMACAddress(request)) == 0) {
				System.out.println("***** when user is not looged in ****");

				out.print(ReturnResponse.retrnresponse_url());
			} else {

				try {

					String str = new com.nts.grb.query.SeaCheckDeckWash().check();

				String	stringDate = jObj.getString("date");
					System.out.println(stringDate);
					String entry_filled_dt[] = stringDate.split("T");

					com.nts.grb2.model.DischargeToSeaStopDeckWashing_Acc dischtoseastpdkwsh = new com.nts.grb2.model.DischargeToSeaStopDeckWashing_Acc();

					String timezone = GetShipTimeZone.gettimezone();
					dischtoseastpdkwsh.setEntry_date(Dateutil.parseDateTime(jObj.getString("date")));
					
					System.out.println(Dateutil.parseDateTime(jObj.getString("date")));
					dischtoseastpdkwsh.setOfficer_id(
							com.nts.grb.query.ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)));

					dischtoseastpdkwsh.setEnrty_timeZone(timezone);
					dischtoseastpdkwsh.setEntry_time(jObj.getString("time"));
					
					
					String category_type = new com.nts.grb2.query.LastEntryDetails_GRB2().last_Strt_inc_cate();
					dischtoseastpdkwsh.setGarbage_category(category_type);
					
					// dischtoseastpdkwsh.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));
					dischtoseastpdkwsh.setRemark(jObj.optString("remarkForm").toUpperCase());

					dischtoseastpdkwsh.setShip_pos_latitude_degree(jObj.getDouble("latDegForm"));
					dischtoseastpdkwsh.setShip_pos_latitude_loc(jObj.getString("latSideForm"));
					dischtoseastpdkwsh.setShip_pos_latitude_min(jObj.getDouble("latMinForm"));

					dischtoseastpdkwsh.setPrecaution(jObj.getString("precautionForm"));
					dischtoseastpdkwsh.setWater_depth(jObj.optLong("depthForm"));
					
					dischtoseastpdkwsh.setShip_pos_longitude_degree(jObj.getDouble("longDegForm"));
					dischtoseastpdkwsh.setShip_pos_longitude_loc(jObj.getString("longSideForm"));
					dischtoseastpdkwsh.setShip_pos_longitude_min(jObj.getDouble("longMinForm"));

					dischtoseastpdkwsh.setFile_name(jObj.optString("fileNameForm"));
					dischtoseastpdkwsh.setAttached_file_size(jObj.optInt("file_sizeForm"));
					dischtoseastpdkwsh.setAttached_file_type(jObj.optString("file_typeForm"));

					dischtoseastpdkwsh.setGarbage_quantity(jObj.getDouble("garbageQtyForm"));

					dischtoseastpdkwsh.setMasterId("0");
					dischtoseastpdkwsh.setMasterReId(0);
					dischtoseastpdkwsh.setStrikeId("0");
					
					LastEnrtyUpdation_GRB2 lsEntryUpdt = new LastEnrtyUpdation_GRB2();
					dischtoseastpdkwsh.setEntry_id(lsEntryUpdt.get_crnt_mstr_id());
				
					Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();
					session.save(dischtoseastpdkwsh);
					
					tran.commit();
					lsEntryUpdt.strt_deck_wash(jObj.getString("date"), jObj.getString("time"),
							timezone, "stop_deck_washing_acc_grb2", 0, category_type, entry_filled_dt[0]);

					out.print(ReturnResponse.success_condition());
				}

				catch (JSONException e) {

					out.print(ReturnResponse.retrnresponse_wrong_json());
					e.printStackTrace();

				} catch (SQLException e) {

					out.print(ReturnResponse.retrnresponse_db_error());

				}
			}

		}
	}

}
