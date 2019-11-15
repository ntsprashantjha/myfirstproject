package com.nts.grb.validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReturnResponse {

	JSONObject jObj;

	public static JSONArray retrnresponse_url() {

		JSONObject jObj;
		JSONObject responsejson = new JSONObject();

		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", "user is not logged in");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}
	
	public static JSONArray ownMessage(String msg) {

		JSONObject jObj;
		JSONObject responsejson = new JSONObject();

		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", msg);
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}

	public static JSONArray capacityExceed() {
		JSONObject responsejson = new JSONObject();

		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", "Tank Capacity Exceeded");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}
	

	
	public static JSONArray previous_data_notavailable(String date) {
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", "Please Fill Daily Sounding Sheet Of " + date + " First.");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}
	
	public static JSONArray entry_date_less() {
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", "Entry Date Should Not less Last Entry Date");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}
	public static JSONArray entry_date_less_then_DTS_lst_dt() {
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message", "Cannot Make Entry Before Last Updated Daily Tank Sounding Sheet");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}

	public static JSONArray extracaptain_not_available() {
		JSONObject responsejson = new JSONObject();

		JSONArray resArr = new JSONArray();

		try {

			responsejson.put("status", false);
			responsejson.put("message",
					"Only Single Captain Is Available On The Ship Please Add Another Captain Before Deleting This Captain.");
			resArr.put(responsejson);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return resArr;
	}

	public static JSONArray retrnresponse_wrong_json() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Data is not valid");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray rank_exist() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "rank name already exist with different rank id");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray retrnresponse_exception_occured() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Exception Occurred");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray newpassAndconfrmpassNotMatched() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Confirmed Password is Not Matched");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray attachedCondNotMatched() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Data Not Found");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray retrnresponse_db_error() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "internal server error");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray licenceExpire() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "your software licence is expired");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray success_condition() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", true);
			responsejson.put("message", " Successfully Done");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray ganrate_key_success_condition(String file) {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", true);
			responsejson.put("message", " Successfully registered");
			responsejson.put("key_loc", file);
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray wrong_username_pass() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "wrong user name password ");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray somethingWentWrong() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "something went wrong ");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray keyfilenotfound() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "File Not Found ");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray keyOrMachineNotMatch() {
		JSONObject jObj;
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Your software key is not matched to your machine");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray StartDeckWashing() {
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Please First Start To Sea Deck Washing");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}

	public static JSONArray StopDeckWashing() {
		JSONObject responsejson = new JSONObject();
		JSONArray resArr = new JSONArray();
		try {
			responsejson.put("status", false);
			responsejson.put("message", "Please First Stop To Sea Deck Washing");
			resArr.put(responsejson);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resArr;
	}
}
