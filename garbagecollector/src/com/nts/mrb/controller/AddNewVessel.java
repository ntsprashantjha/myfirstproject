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
import com.nts.mrb.model.VesselDetails;


@WebServlet("/AddNewVessel")
public class AddNewVessel extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	public AddNewVessel() {
		
		super();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		JSONObject njObj = new JSONObject();
		
		System.out.println("Obj:\n" + njObj);
	
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			
			//getting vessel details from database in json format
			out.print(new AccessRight().vessel_details());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		System.out.println("******add new vessel******");
		
		//Getting Webpage DATA In Json Format
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**json data from add new vessel details**" + jObj);
		
		VesselDetails vsl_dtls = new VesselDetails();
		
		try {
			
			//***************For Audit****************
			//Getting User Ip Address
			String mac = GetUserMacId.getClientMACAddress(request);
			System.out.println("User MAc ID : - "+mac);
			String oldvesdetails =GetCompanyDetails.getVesselPreDataInformate();
			//Getting User Employee Id
			String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
					.get(0)).getString("emp_id");
			
			if(userid==null)
				throw new Exception("Emp ID Not Available");
			
			System.out.println("User Is : - "+userid);
			
			Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
		
			vsl_dtls.setCall_sign(jObj.getString("callSignForm").toUpperCase());
			vsl_dtls.setClasses(jObj.optString("shipclassForm").toUpperCase());
			
			vsl_dtls.setComp_status(1);
			//vsl_dtls.setDate_delivered(Dateutil.parseDateTime(jObj.getString("dateDeliveredForm")));
			
			vsl_dtls.setDate_delivered(jObj.getString("dateDeliveredForm"));
			vsl_dtls.setDwt(jObj.optString("dwtForm").toUpperCase());
			
			vsl_dtls.setFlag(jObj.optString("flagForm").toUpperCase());
			vsl_dtls.setGross_tonnage(jObj.optInt("grossTonnageForm"));
			
			vsl_dtls.setHull_no(jObj.optInt("hullNoForm"));
			vsl_dtls.setLength_bw_perpandicular(jObj.optInt("lengthPerpendicularForm"));
			
			vsl_dtls.setLength_overall(jObj.optInt("lengthOverlallForm"));
			vsl_dtls.setLoa(jObj.optString("loaForm"));
			
			//vsl_dtls.setDate_keel_laid(Dateutil.parseDateTime(jObj.getString("dateKeelLaidForm")));
			vsl_dtls.setDate_keel_laid(jObj.getString("dateKeelLaidForm"));
			
			vsl_dtls.setModuled_breath(jObj.optInt("mouldedBreadthForm"));
			vsl_dtls.setModuled_depth(jObj.optInt("mouldedDepthForm"));
			
			vsl_dtls.setModuled_draft(jObj.optInt("mouldedDraftForm"));
			vsl_dtls.setPort_of_registry(jObj.optString("portRegistryForm").toUpperCase());
			
			vsl_dtls.setShip_yard(jObj.optInt("shipYardForm"));
			vsl_dtls.setid(1);
			
			vsl_dtls.setVessel_imo(jObj.getString("vesselIMOForm").toUpperCase());
			vsl_dtls.setVessel_name(jObj.getString("vesselNameForm").toUpperCase());
			
			vsl_dtls.setVessel_status(1);
			vsl_dtls.setVessel_type(jObj.optString("vesselTypeForm").toUpperCase());
			
			vsl_dtls.setIncinerator_capacity(jObj.optInt("incinerator_capacity"));
			
			session.saveOrUpdate(vsl_dtls);
			tran.commit();
			session.close();
			String newvesdetails = GetCompanyDetails.getVesselPreDataInformate();
			//Create Audit Of Following Entry In Audit Table
			CreateAudit.auditUpdate(userid,"EDIT", "Ship Details", oldvesdetails, newvesdetails, mac, "MRB");
			
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