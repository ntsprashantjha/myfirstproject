package com.nts.mrb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import com.nts.grb.query.ActiveUserInfo;

import com.nts.grb.service.GetUserMacId;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.InsertNewRank;
import com.nts.mrb.dao.NewUsrModuleAccess;
import com.nts.mrb.model.AddNewRankndAccess;

@WebServlet("/AccessRightDtlsUpdate")
public class AccessRightDtlsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JSONObject jObj;

	public AccessRightDtlsUpdate() {

		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("***access details servlet is called***");
		int newly_gnrted_id = 0;

		AddNewRankndAccess addnewrankDtls = new AddNewRankndAccess();

		PrintWriter out = response.getWriter();

		// Getting data from webpage in json format
		jObj = new JsonObj().get_btn_num(request, response);

		System.out.println("******access details servlet*******" + jObj.length() + jObj);

		try {

			Session session = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			// if new rank is 0 then it is updating a rank access_right
			System.out.println(jObj.getString("rank_name"));

			if (jObj.length() > 1) {

				// ***************For Audit****************
				// Getting User Ip Address
				String mac = GetUserMacId.getClientMACAddress(request);
				System.out.println("User MAc ID : - " + mac);

				String oldaccdet = com.nts.mrb.dao.GetDetails
						.getAccessRightsByUser(Integer.toString(jObj.getInt("rank_id")));

				// Getting User Employee Id
				String userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac)))
						.get(0)).getString("emp_id");

				if (userid == null)
					throw new Exception("Emp ID Not Available");

				System.out.println("if ");

				addnewrankDtls = (AddNewRankndAccess) session.get(AddNewRankndAccess.class, jObj.getInt("rank_id"));
				addnewrankDtls.setAdd_edit_user(jObj.getString("add_edit_user").toUpperCase());

				addnewrankDtls.setBack_up_rights(jObj.getString("back_up_rights").toUpperCase());
				addnewrankDtls.setCreate_rank(jObj.getString("create_rank").toUpperCase());

				addnewrankDtls.setEdit_general_setting(jObj.getString("edit_general_setting").toUpperCase());
				addnewrankDtls.setMrb_control(jObj.getString("mrb_control").toUpperCase());

				addnewrankDtls.setPassword_reset_access_control(
						jObj.getString("password_reset_access_control").toUpperCase());
				addnewrankDtls.setRank_id(jObj.getInt("rank_id"));

				addnewrankDtls.setSign_of_user(jObj.getString("sign_of_user").toUpperCase());
				addnewrankDtls.setSoftware_access_control(jObj.getString("software_access_control").toUpperCase());

				session.saveOrUpdate(addnewrankDtls);

				tran.commit();
				session.close();

				String newaccdet = com.nts.mrb.dao.GetDetails
						.getAccessRightsByUser(Integer.toString(jObj.getInt("rank_id")));

				// Create Audit Of Following Entry In Audit Table
				CreateAudit.auditUpdate(userid, "EDIT",
						"Access Right Changed For Rank Id :- " + Integer.toString(jObj.getInt("rank_id")), oldaccdet,
						newaccdet, mac, "MRB");
				out.print(ReturnResponse.success_condition());
			}

			// in case of adding new rank
			else {

				System.out.println("ifeles********************** ");
				// updating new rank into rank_details table and getting newly gnrted rank id

				newly_gnrted_id = InsertNewRank.addRankndAccess(jObj.getString("rank_name").toUpperCase());
				// If user already exist newly_gnrted_id will be 0 else it is new generated id

				// We Can Also Solve This Problem By Making That Column UniqueKey In Database
				if (newly_gnrted_id != 0) {

					// ***************For Audit****************
					// Getting User Ip Address
					String mac = GetUserMacId.getClientMACAddress(request);
					System.out.println("User MAc ID : - " + mac);

					// Getting User Employee Id
					String userid = ((JSONObject) (StringToJsonConverter
							.convrt(new ActiveUserInfo().activateUser1(mac))).get(0)).getString("emp_id");

					if (userid == null)
						throw new Exception("Emp ID Not Available");

					System.out.println("User Is : - " + userid);

					addnewrankDtls.setAdd_edit_user("NO");
					addnewrankDtls.setBack_up_rights("NO");

					addnewrankDtls.setCreate_rank("NO");
					addnewrankDtls.setEdit_general_setting("NO");

					addnewrankDtls.setMrb_control("NO");
					addnewrankDtls.setPassword_reset_access_control("NO");

					addnewrankDtls.setRank_id(newly_gnrted_id);

					addnewrankDtls.setSign_of_user("NO");
					addnewrankDtls.setSoftware_access_control("NO");
					addnewrankDtls.setisDeletable(1);

					session.save(addnewrankDtls);
					tran.commit();
					session.close();

					com.nts.grb.model.UserManagement userManagment = new com.nts.grb.model.UserManagement();
					Session session_usrmngmnt = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
					Transaction tran_usrmngmnt = session_usrmngmnt.beginTransaction();

					userManagment.setadd_remove_circular("NO");
					userManagment.setData_entry_setting("NO");

					userManagment.setDigi_bio_sign("NO");
					userManagment.setEdit_rbac("NO");

					userManagment.setFinal_approval("NO");

					userManagment.setGrb_log_access("NO");
					userManagment.setId(newly_gnrted_id);

					userManagment.setmake_strike_entry("NO");
					userManagment.setRank(jObj.getString("rank_name").toUpperCase());

					session_usrmngmnt.save(userManagment);
					tran_usrmngmnt.commit();
					session_usrmngmnt.close();

					com.nts.grb2.model.UserManagement_Grb2 userManagment_grb2 = new com.nts.grb2.model.UserManagement_Grb2();
					Session session_usrmngmnt_grb2 = com.nts.mrb.util.MrbUtil.buildSessionFactory().openSession();
					Transaction tran_usrmngmnt_grb2 = session_usrmngmnt_grb2.beginTransaction();

					userManagment_grb2.setCircular_access("NO");
					userManagment_grb2.setDigi_bio_sign("NO");

					userManagment_grb2.setEdit_grb_sit("NO");

					userManagment_grb2.setFinal_approval("NO");
					userManagment_grb2.setGrb_log_access("NO");

					userManagment_grb2.setMake_entry("NO");
					userManagment_grb2.setRank_id(newly_gnrted_id);

					userManagment_grb2.setReport_view_access("NO");
					userManagment_grb2.setRank(jObj.getString("rank_name").toUpperCase());

					session_usrmngmnt_grb2.save(userManagment_grb2);
					tran_usrmngmnt_grb2.commit();
					session_usrmngmnt_grb2.close();

					NewUsrModuleAccess.addRankndAccess(1, newly_gnrted_id);

					out.print(ReturnResponse.success_condition());

					// Create Audit Of Following Entry In Audit Table
					CreateAudit.auditUpdate(userid, "ADD", "Added New Rank ID :- " + Integer.toString(newly_gnrted_id),
							" ", " ", mac, "MRB");

					out.print(ReturnResponse.success_condition());
				} else {
					out.print(ReturnResponse.rank_exist());

				}
			}

		} catch (JSONException e) {

			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();

		} catch (SQLException e) {

			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();

		} catch (Exception e) {

			out.print(ReturnResponse.somethingWentWrong());
			e.printStackTrace();

		}
	}

}