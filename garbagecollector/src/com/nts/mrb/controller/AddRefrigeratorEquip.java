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
import com.nts.mrb.model.AddRefrigeratorEquipMrb;

@WebServlet("/AddRefrigeratorEquip")
public class AddRefrigeratorEquip extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public AddRefrigeratorEquip() {
		
		super();
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("Get Method In AddRefrigeratorEquip");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		//Getting Webpage Data In Json Format
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("JSON DATA FROM FRONT END - " +jObj);
		
		AddRefrigeratorEquipMrb equip = new AddRefrigeratorEquipMrb();
				
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
			
			equip.setEquipment_name(jObj.optString("equipment_name").toUpperCase());
			equip.setEquipment_location(jObj.optString("equipment_location").toUpperCase());
			
			equip.setManufacturer(jObj.optString("manufacturer").toUpperCase());
			equip.setReferigerant_type(jObj.optString("referigerant_type").toUpperCase());
			
			equip.setQuantity(jObj.getDouble("quantity"));
			equip.setYear_of_installation(jObj.optString("year_of_installation").toUpperCase());

			Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
			
			Transaction tran = session.beginTransaction();
			session.saveOrUpdate(equip);
			
			tran.commit();
			
			
			//Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid,"Refrigerator Equipment Details", "Refrigerator Equipment Details Added", " ", " ", mac, "MRB");
			
			out.print(ReturnResponse.success_condition());
			
		} catch (JSONException e) {
			
			out.print(ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
			
		}
		catch (Exception e) {
			
			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();
		}
		
	}

}