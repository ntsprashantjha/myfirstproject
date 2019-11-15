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
import com.nts.grb.util.UsrRegistration;

/**
 * Servlet implementation class UserManagement
 */
@WebServlet("/UserManagement")
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

       
    /*
     * @see HttpServlet#HttpServlet()
     */
    public UserManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		com.nts.grb.model.UserManagement UserManagement=new com.nts.grb.model.UserManagement();
		PrintWriter out = response.getWriter();

		UserManagement.setRank("Captain");
		UserManagement.setmake_strike_entry("NO");

		UserManagement.setadd_remove_circular("YES");
		UserManagement.setData_entry_setting("YES");
		
		UserManagement.setDigi_bio_sign("YES");
		UserManagement.setFinal_approval("YES");
		
		UserManagement.setGrb_log_access("YES");
		UserManagement.setEdit_rbac("YES");
		
		Session session = UsrRegistration.buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		
		session.save(UserManagement);
		tran.commit();
}
}