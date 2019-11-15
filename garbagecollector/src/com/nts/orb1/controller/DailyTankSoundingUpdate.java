package com.nts.orb1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.nts.orb1.dao.JsonObj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.GetUserMacId;
import com.nts.orb1.service.DailyTankQuantityUpdation;

/**
 * Servlet implementation class DailyTankSoundingUpdate
 */
@WebServlet("/DailyTankSoundingUpdate")
public class DailyTankSoundingUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DailyTankSoundingUpdate() {
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

		Connection con = dbConnection.getConnection();
		
		// Getting User Ip Address
		String mac = GetUserMacId.getClientMACAddress(request);
		System.out.println("User MAc ID : - " + mac);
		
		PrintWriter out = response.getWriter();
		JSONArray jObj = new JsonObj().get_btn_num(request, response);
		out.print(new DailyTankQuantityUpdation().quantityUpdation(jObj,mac,con));

		// out.print(ReturnResponse.success_condition());

	}

}