package com.nts.grb2.controller;

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
import com.nts.grb2.model.Access_Right_GRB2;
import com.nts.grb2.util.HibernateUtilGRB2;

/**
 * Servlet implementation class Update_Access_Rigth_GRB2
 */
@WebServlet("/Update_Access_Rigth_GRB2")
public class Update_Access_Rigth_GRB2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update_Access_Rigth_GRB2() {
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
		com.nts.grb2.model.Access_Right_GRB2 UserManagement = new com.nts.grb2.model.Access_Right_GRB2();
		PrintWriter out = response.getWriter();

		UserManagement.setRank("4TH ENGINEER");
		UserManagement.setmake_strike_entry("NO");

		UserManagement.setadd_remove_circular("YES");
		UserManagement.setData_entry_setting("YES");

		UserManagement.setDigi_bio_sign("YES");
		UserManagement.setFinal_approval("YES");

		UserManagement.setGrb_log_access("YES");
		UserManagement.setEdit_rbac("YES");

		Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();

		session.save(UserManagement);
		tran.commit();
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
		JSONObject jObj = new JsonObj().get_btn_num(request, response);

		System.out.println(jObj + "-:value of  json");
		com.nts.grb2.model.Access_Right_GRB2 userManagment = new com.nts.grb2.model.Access_Right_GRB2();
		try {

			Session session = HibernateUtilGRB2.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();
			userManagment = (Access_Right_GRB2) session.get(com.nts.grb2.model.Access_Right_GRB2.class,
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
		}

		catch (JSONException e) {
			ReturnResponse.retrnresponse_wrong_json();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(ReturnResponse.success_condition());
	}

}
