package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.grb.dao.LoginValidate;
import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;

/**
 * Servlet implementation class UserLogout
 */
@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	JSONObject njObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogout() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
		
			//***************For Audit****************
			String mac = GetUserMacId.getClientMACAddress(request);
			//Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");
			
			if(userid==null)
				throw new Exception("Emp ID Not Available");
			
			System.out.println("User Is : - "+userid);
		

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		out.print(LoginValidate.validate1(GetUserMacId.getClientMACAddress(request)));

		//Create Audit Of Following Entry In Audit Table
		CreateAudit.auditUpdate(userid,"LOGOUT", "Logged Out "+userid, " ", " ", mac, "MRB");
		
	}catch (Exception e) {

		out.print(ReturnResponse.retrnresponse_wrong_json());
		e.printStackTrace();
	}

}
}