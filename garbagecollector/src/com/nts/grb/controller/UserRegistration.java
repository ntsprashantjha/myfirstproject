package com.nts.grb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
import com.nts.grb.util.Dateutil;
import com.nts.grb.util.UsrRegistration;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.GetRankId;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistration() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int user_rank_id = 0;
		Date user_dob;

		PrintWriter out = response.getWriter();

		System.out.println("from post method of dischargetosea servlet");

		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");

		try {
			//***************For Audit****************			
			//Getting User Employee Id
			String mac = GetUserMacId.getClientMACAddress(request);
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");
			
			if(userid==null)
				throw new Exception("Emp ID Not Available");
			
			System.out.println("User Is : - "+userid);

			com.nts.grb.model.UserRegistration usrRgstration = new com.nts.grb.model.UserRegistration();
			usrRgstration.setCnfrmPsw(jObj.getString("confirmPasswordForm"));

			usrRgstration.setCountry(jObj.getString("countryForm").toUpperCase());
			usrRgstration.setSeamanBook(jObj.getString("seaMenBookForm"));

			user_dob = Dateutil.parseDateTime(jObj.getString("empDOBForm"));
			usrRgstration.setUsrDOB(user_dob);

			usrRgstration.setUsrJoinDt(Dateutil.parseDateTime(jObj.getString("empDOJForm")));
			// usrRgstration.setUsrLeaveDt(Dateutil.parseDateTime(jObj.optString("date")));

			usrRgstration.setusrID(jObj.getString("empIdForm"));
			usrRgstration.setUsrfrstName(jObj.getString("firstNameForm").toUpperCase());

			usrRgstration.setUsrmdlName(jObj.optString("middleNameForm").toUpperCase());
			usrRgstration.setUsrRnk(jObj.getString("empRankForm"));

			usrRgstration.setUsrlstName(jObj.getString("lastNameForm").toUpperCase());
			user_rank_id = GetRankId.rankId(jObj.getString("empRankForm"));

			usrRgstration.setRank_id(user_rank_id);

			Session session = UsrRegistration.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			session.save(usrRgstration);
			tran.commit();
			

			//Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid,"ADD", "Added New User "+jObj.getString("empIdForm"), " ", " ", mac, "MRB");
			

			/*
			 * NewUsrModuleAccess.addRankndAccess(
			 * GetRankId.userId(jObj.getString("firstNameForm"),
			 * jObj.getString("confirmPasswordForm")), user_rank_id);
			 */

		} catch (JSONException e) {
			// TODO Auto-generated catch block

			out.print(ReturnResponse.retrnresponse_wrong_json());
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();
		}
		out.print(ReturnResponse.success_condition());
		// doGet(request, response);
	}

}