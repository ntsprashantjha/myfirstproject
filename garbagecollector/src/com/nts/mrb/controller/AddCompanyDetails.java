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
import com.nts.grb.query.GetCompanyDetails;
import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.AccessRight;
import com.nts.mrb.model.CompanyDetails;


@WebServlet("/AddCompanyDetails")
public class AddCompanyDetails extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	JSONObject jObj;
	
	String ture_json = "\"status\":\"succese\"";
	String false_json = "\"status\"";

	
	public AddCompanyDetails() {
		
		super();
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		PrintWriter out = response.getWriter();
		JSONObject njObj = new JSONObject();
		
		System.out.println("Obj:\n" + njObj);
		//String js = njObj.toString();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		
		//Getting Company Details From AccessRight Class In Sting Format
		try {
			out.print(new AccessRight().company_details());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		try {
			
			//***************For Audit****************
			//Getting User Ip Address
			String mac = GetUserMacId.getClientMACAddress(request);
			System.out.println("User MAc ID : - "+mac);
			
			String oldcompdet = GetCompanyDetails.get();
			
			//Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");
			
			if(userid==null)
				throw new Exception("Emp ID Not Available");
			
			System.out.println("User Is : - "+userid);
			
		
		CompanyDetails company_dtls = new CompanyDetails();
		
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj);
		
		Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
			
			company_dtls.setOperator_company_address(jObj.getString("operator_company_address").toUpperCase());
			company_dtls.setOperator_company_name(jObj.getString("operator_company_name").toUpperCase());
			
			company_dtls.setOwner_company_address(jObj.getString("owner_company_address").toUpperCase());
			company_dtls.setOwner_company_name(jObj.getString("owner_company_name").toUpperCase());
			
			company_dtls.setid(1);
			company_dtls.setComp_status(1);
			
			session.saveOrUpdate(company_dtls);
			tran.commit();
			
			session.close();
			String newcompdet = GetCompanyDetails.get();
			
			//Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid,"EDIT", "Company Details",oldcompdet,newcompdet, mac, "MRB");
			
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