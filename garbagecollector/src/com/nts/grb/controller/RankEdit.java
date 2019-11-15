package com.nts.grb.controller;

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
import com.nts.grb.model.UserManagement;
import com.nts.grb.util.UsrRegistration;
import com.nts.grb.validation.ReturnResponse;

/**
 * Servlet implementation class RankEdit
 */
@WebServlet("/RankEdit")
public class RankEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RankEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		System.out.println("from post method");
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");
		com.nts.grb.model.UserManagement userManagment = new com.nts.grb.model.UserManagement();
		try {

			Session session = UsrRegistration.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			userManagment = (UserManagement) session.get(com.nts.grb.model.UserManagement.class,
					jObj.getInt("rank_id"));
			userManagment.setData_entry_setting(jObj.getString("data_entry_setting"));

			userManagment.setRank(jObj.getString("rank"));
			userManagment.setFinal_approval(jObj.getString("final_approval"));

			userManagment.setDigi_bio_sign(jObj.getString("digi_bio_sign"));
			userManagment.setEdit_rbac(jObj.getString("edit_rbac"));

			userManagment.setGrb_log_access(jObj.getString("grb_log_access"));
			userManagment.setmake_strike_entry(jObj.getString("make_strike_entry"));

			userManagment.setadd_remove_circular(jObj.getString("add_remove_circular"));
			session.saveOrUpdate(userManagment);

			tran.commit();
			session.close();

		} catch (JSONException e) {
			ReturnResponse.retrnresponse_exception_occured();
			e.printStackTrace();
		}
		ReturnResponse.success_condition();

	}
}
