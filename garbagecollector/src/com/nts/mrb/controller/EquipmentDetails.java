package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.AllUsersInfo;
import com.nts.mrb.model.EquipmentDtls;

@WebServlet("/EquipmentDetails")
public class EquipmentDetails extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public EquipmentDetails() {
		
		super();
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//Getting Eqipment Details From AllUserInfo Class available in com.nts.mrb.dao package
		out.print(new AllUsersInfo().return_equipmnt_dtls());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		//Getting Webpage Data In Json Format
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		
		System.out.println("skjvn" + jObj);
		EquipmentDtls equip = new EquipmentDtls();
				
		try {
			
			//***************For Audit****************
			//Getting User Ip Address
			String mac = GetUserMacId.getClientMACAddress(request);
			System.out.println("User MAc ID : - "+mac);
			
			//Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");
			
			if(userid==null)
				throw new Exception("Emp ID Not Available");
			
			System.out.println("User Is : - "+userid);
			
			equip.setId(1);
			equip.setBilge_capacity(jObj.getDouble("bilge_capacity"));
			
			equip.setOliywater_capacity(jObj.getDouble("separator_capacity"));
			equip.setIncinerator_capacity(jObj.getDouble("incinerator_capacity"));
			
			equip.setBilge_maker(jObj.optString("bilge_maker").toUpperCase());
			equip.setOliywater_maker(jObj.optString("separator_maker").toUpperCase());
			
			equip.setIncinerator_maker(jObj.optString("incinerator_maker").toUpperCase());
			equip.setBilge_type(jObj.optString("bilge_type").toUpperCase());
			
			equip.setOliywater_type(jObj.optString("separator_type").toUpperCase());
			equip.setIncinerator_type(jObj.optString("incinerator_type").toUpperCase());
			
			equip.setPlastic_burn_approval(jObj.optInt("plastic_burning"));
			Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
			
			Transaction tran = session.beginTransaction();
			session.saveOrUpdate(equip);
			
			tran.commit();
			
			
			//Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid,"Equipment Details", "Equipment Details Added", " ", " ", mac, "MRB");
			
			
		} catch (JSONException e) {
			
			out.print(ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
			
		}
		catch (Exception e) {
			
			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();
		}
		out.print(ReturnResponse.success_condition());
	}

}