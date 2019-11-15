package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.mrb.dao.AllUsersInfo;

/**
 * Servlet implementation class AllUsers
 */
@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject njObj = new JSONObject();
		System.out.println("Obj:\n" + njObj);
		String js = njObj.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			out.print(new AllUsersInfo().allUsersDetails());
			//System.out.print(new AllUsersInfo().allUsersDetails());
		} catch (Exception e) {
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("from get method");
		PrintWriter out = response.getWriter();
		JSONObject njObj = new JSONObject();
		System.out.println("Obj:\n" + njObj);
		String js = njObj.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		try {
			out.print(new AllUsersInfo().allUsersDetails());
		} catch (Exception e) {
		}	}

}
