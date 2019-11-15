package com.nts.mrb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.mrb.model.AddNewRankndAccess;

/**
 * Servlet implementation class AddNewRankAccess
 */
@WebServlet("/AddNewRankAccess")
public class AddNewRankAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewRankAccess() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("asfgjkl;");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		AddNewRankndAccess addnewrank_access=new AddNewRankndAccess();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println("**key value for add new rank details**"+jObj);
		/*addnewrank_access.setAdd_edit_user(add_edit_user);
		addnewrank_access.setBack_up_rights(back_up_rights);
		addnewrank_access.setCreate_rank(create_rank);
		addnewrank_access.setEdit_general_setting(edit_general_setting);
		addnewrank_access.setMrb_control(mrb_control);
		addnewrank_access.setPassword_reset_access_control(password_reset_access_control);
		addnewrank_access.setRank_id(rank_id);
		addnewrank_access.setSign_of_user(sign_of_user);
		addnewrank_access.setSoftware_access_control(addnewrank_access);*/

	}

}
