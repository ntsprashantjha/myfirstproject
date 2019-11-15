package com.nts.mrb.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.nts.mrb.dao.ShipCompTimeDtls;

@WebServlet("/ShipCompanyTimeCond")
public class ShipCompanyTimeCond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ShipCompanyTimeCond() {
    	
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
try {
	
	System.out.println(ShipCompTimeDtls.getDtls());
	
} catch (SQLException e) {
	
	e.printStackTrace();
	
} catch (JSONException e) {
	
	e.printStackTrace();
	
}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}