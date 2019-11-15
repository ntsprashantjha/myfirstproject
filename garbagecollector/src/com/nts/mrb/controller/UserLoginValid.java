package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.mrb.dao.LoginValidate;

/**
 * Servlet implementation class UserLoginValid
 */
@WebServlet("/UserLoginValid")
public class UserLoginValid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONObject jObj, njObj;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginValid() {
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
		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");
		try {
			njObj = new JSONObject();
			System.out.println("Obj:\n" + njObj);
			/*
			 * response.setContentType("application/json");
			 * response.setCharacterEncoding("utf-8");
			 */
			System.out.println(new LoginValidate().user_access_right("ajay", "1234", "abc"));
			out.print(new LoginValidate().user_access_right("ajay", "1234", "abc"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		jObj = new JsonObj().get_btn_num(request, response);
		System.out.println(jObj + "-:value of  json");
		try {
			njObj = new JSONObject();
			System.out.println("Obj:\n" + njObj);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			out.print(new LoginValidate().user_access_right("ajay", "1234", "acb"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
