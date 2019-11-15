package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.UpdateNewPassword;

@WebServlet("/FirstTimePasswordChange")
public class FirstTimePasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FirstTimePasswordChange() {
		
		super();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//Getting Webpage Data In Json Format
		JSONObject jObj = new JsonObj().get_btn_num(request, response);
		
		System.out.println(jObj + "-:value of  json");
		
		try {
			
			if (!jObj.getString("new_password").equals(jObj.getString("confirm_new_password"))) {
				
				out.print(com.nts.grb.validation.ReturnResponse.newpassAndconfrmpassNotMatched());
			
			} else {
				
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
				
				out.print(new UpdateNewPassword().updatePass(
						
				//Find User Is Active Or Not
				ActiveUserInfo.activeUserId(GetUserMacId.getClientMACAddress(request)),
				jObj.getString("old_password"), jObj.getString("new_password")));
				
				//Create Audit Of Following Entry In Audit Table
				CreateAudit.auditUpdate(userid,"Password Change", "First Time Password Change", " ", " ", mac, "MRB");
								
		
			}
		}
		catch (JSONException e) {

			out.print(com.nts.grb.validation.ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
		
		} catch (SQLException e) {
		
			out.print(com.nts.grb.validation.ReturnResponse.retrnresponse_db_error());
			e.printStackTrace();
			
		} catch (Exception e) {
			
			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();
		}
	}

}