package com.nts.grb2.service;

import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.model.AllFilters;
import com.nts.grb2.query.HomeDataFullReportById;

import testing.FilterDao;

public class FilterDtls_GRB2 {

	public JSONArray getDtls(AllFilters alf) throws Exception {

		FilterDao fld = new FilterDao();
		ResultSet resultSet;

		JSONArray obj = new JSONArray();
		JSONArray rrOb = new JSONArray();

		obj = HomeDataFullReportById.entry_id(alf.getTodate(), alf.getFordate(), dbConnection.getConnection());

		System.out.println(obj);
		for (int i = 0; i < obj.length() - 1; i++) {

			rrOb.put(obj.get(i));
		}

		return rrOb;
	}

	public static void main(String arg[]) throws Exception {

		System.out.println("gyhjbhjk" + new FilterDtls_GRB2().getDtls(new AllFilters()));

	}

}
