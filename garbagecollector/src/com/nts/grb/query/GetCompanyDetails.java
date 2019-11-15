package com.nts.grb.query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.mrb.dao.AccessRight;
public class GetCompanyDetails {
	
	public static String get() {
		
		try {
			
		String s =new  AccessRight().company_details();
		JSONArray jObj = new JSONArray(s);
		System.out.println(jObj);
		JSONObject j = (JSONObject) jObj.get(0);
		s = "owner_company_name : " +
				j.getString("owner_company_name") 
			+ "\n" +	"owner_company_address : " + j.getString("owner_company_address") 
			+ "\n" + 	"operator_company_name : " + j.getString("operator_company_name") 
			+ "\n" + 	"operator_company_address : " + j.getString("operator_company_address");
		
		System.out.println(s);
		
		return s ;
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
			return " ";
			}
		
		
	}
public static String getVesselPreDataInformate() {
		
		try {
			
			String s = new AccessRight().vessel_details();
			JSONArray jObj = new JSONArray(s);
			JSONObject j = (JSONObject) jObj.get(0);
			s = "vessel_name : " +	j.getString("vessel_name") 
				+ "\n" +	"vessel_type : " + j.getString("vessel_type") 
				+ "\n" + 	"date_delivered : " + j.getString("date_delivered") 
				+ "\n" + 	"length_bw_perpandicular : " + Integer.toString(j.getInt("length_bw_perpandicular"))
				+ "\n" + 	"moduled_depth : " + Integer.toString(j.getInt("moduled_depth")) 
				+ "\n" + 	"call_sign : " + j.getString("call_sign") 
				+ "\n" + 	"flag : " + j.getString("flag") 
				+ "\n" + 	"gross_tonnage : " + Integer.toString(j.getInt("gross_tonnage")) 
				+ "\n" + 	"hull_no : " + Integer.toString(j.getInt("hull_no")) 
				+ "\n" + 	"ship_yard : " + Integer.toString(j.getInt("ship_yard")) 
				+ "\n" + 	"vessel_imo : " + j.getString("vessel_imo") 
				+ "\n" + 	"date_keel_laid : " + j.getString("date_keel_laid") 
				+ "\n" + 	"length_over_all : " + Integer.toString(j.getInt("length_over_all")) 
				+ "\n" + 	"moduled_breath : " + Integer.toString(j.getInt("moduled_breath")) 
				+ "\n" + 	"moduled_draft : " + Integer.toString(j.getInt("moduled_draft")) 
				+ "\n" + 	"class : " + Integer.toString(j.getInt("class"))  
				+ "\n" + 	"port_of_registry : " + j.getString("port_of_registry") 
				+ "\n" + 	"dwt : " + Integer.toString(j.getInt("dwt"))  
				+ "\n" + 	"loa : " + Integer.toString(j.getInt("loa"));
		
		return s ;
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
			return " ";
			}
}
	
	public static void main(String[] args) throws JSONException {
		

		
	}
	

}