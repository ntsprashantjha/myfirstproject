package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.nts.grb.connection.JsonObj;
import com.nts.mrb.service.GanrateKeyFromSystmHrdwre;

@WebServlet("/GanrateSoftwareKey")
public class GanrateSoftwareKey extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       


    public GanrateSoftwareKey() {
    	
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print(GanrateKeyFromSystmHrdwre.hardwareKey("k","","k"));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//Getting Webpage Data In JSon Format
		JSONObject	jObj = new JsonObj().get_btn_num(request, response);
		
		System.out.println("********Json value from ganratesoftwarekey*******" + jObj);                                                                   		
		out.print(GanrateKeyFromSystmHrdwre.hardwareKey("k","","k"));
	}

}