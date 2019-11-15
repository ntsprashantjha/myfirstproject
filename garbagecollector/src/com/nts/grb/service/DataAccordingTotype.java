package com.nts.grb.service;
import java.sql.ResultSet;

import org.json.JSONArray;

import com.nts.grb.model.AllFilters;

public class DataAccordingTotype {
	static String grbentrytype = "Sea/Ashore";
	static String GarbageCat = "G", todate = "", fordate = "", status = "",
			tablename2 = "Discharge_To_Sea", tablename1 = "Discharge_To_Ashore";

	static ResultSet resultSet;
	static JSONArray obj = new JSONArray();
	static JSONArray obj1 = new JSONArray();
	static	AllFilters allFilters=new AllFilters();
	public static JSONArray getDischargeToSeaReport(AllFilters allFilters ) throws Exception {
		/*allFilters.setEntrytype(tablename1);
		allFilters.setCategory(GarbageCat);
		allFilters.setStatus(status);
		allFilters.setFordate(fordate);
		allFilters.setTodate(todate);
		allFilters.setTablename1(tablename1);*/
		grbentrytype=allFilters.getEntrytype();
		status=allFilters.getStatus();
		tablename1=allFilters.getTablename1();
		tablename2=allFilters.getTablename2();
		com.nts.grb.query.HomeDataFullReport1 homeDataFullReport = new com.nts.grb.query.HomeDataFullReport1();
		if (grbentrytype.equals("") && GarbageCat.equals("") && status.equals("")) {
			obj = homeDataFullReport.toType(allFilters, fordate);

		} else {
			if (!grbentrytype.equals("") && GarbageCat.equals("") || GarbageCat.equals("All") && status.equals("") || status.equals("All") && fordate.equals("")
					&& todate.equals("")) {
				obj = homeDataFullReport.toType(allFilters, fordate);

			}

			else if (!grbentrytype.equals("") && GarbageCat.equals("") && status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {

				obj = homeDataFullReport.toTypeDate(allFilters, fordate);
			}

			else if (!grbentrytype.equals("") && !GarbageCat.equals("") && status.equals("") && fordate.equals("")
					&& todate.equals("")) {
				System.out.println("on write condition");

				obj = homeDataFullReport.toTypeCat(allFilters, fordate);
			}

			else if (!grbentrytype.equals("") && !GarbageCat.equals("") && status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {

				obj = homeDataFullReport.toTypeCatDate(allFilters, fordate);
			}
			else if (!grbentrytype.equals("") && GarbageCat.equals("") && !status.equals("") && fordate.equals("")
					&& todate.equals("")) {

				// homeDataFullReport.toTypeStatus(allFilters, fordate);
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusA(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusA(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusReApp(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusReApp(allFilters, fordate);
				}

			} else if (!grbentrytype.equals("") && GarbageCat.equals("") && !status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {
				// homeDataFullReport.toTypeStatus(allFilters, fordate);
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				}

			} else if (!grbentrytype.equals("") && !GarbageCat.equals("") && !status.equals("") && fordate.equals("")
					&& todate.equals("")) {

				// homeDataFullReport.toTypeStatusCat(allFilters, fordate);
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusCatA(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusCatA(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusCatReApp(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusCatReApp(allFilters, fordate);
				}
			} else if (!grbentrytype.equals("") && !GarbageCat.equals("") && !status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {
				// homeDataFullReport.toTypeStatusCat(allFilters, fordate);
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusCatADate(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusCatADate(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusCatReAppDate(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusCatReAppDate(allFilters, fordate);
				}
			} else if (grbentrytype.equals("") && !GarbageCat.equals("") && status.equals("") && fordate.equals("")
					&& todate.equals("")) {
				obj = homeDataFullReport.toCat(allFilters, fordate);
			} else if (grbentrytype.equals("") && !GarbageCat.equals("") && status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {
				obj = homeDataFullReport.toCatDate(allFilters, fordate);
			}

			else if (grbentrytype.equals("") && !GarbageCat.equals("") && !status.equals("") && fordate.equals("")
					&& todate.equals("")) {
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toCatStatusA(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toCatStatusA(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toCatStatusReApp(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusReApp(allFilters, fordate);
				}
				// homeDataFullReport.toCatStatus(allFilters, fordate);
			}

			else if (grbentrytype.equals("") && !GarbageCat.equals("") && !status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				}
				// homeDataFullReport.toCatStatus(allFilters, fordate);
			}

			else if (grbentrytype.equals("") && GarbageCat.equals("") && !status.equals("") && fordate.equals("")
					&& todate.equals("")) {
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toStatusA(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toStatusA(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toStatusReApp(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toStatusReApp(allFilters, fordate);
				}
				// homeDataFullReport.toStatus(allFilters, fordate);
			}

			else if (grbentrytype.equals("") && GarbageCat.equals("") && !status.equals("") && !fordate.equals("")
					&& !todate.equals("")) {
				if (status.equals("Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Pending for Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusADate(allFilters, fordate);
				} else if (status.equals("Re-Approved")) {
					allFilters.setStatusvalue(1);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				} else if (status.equals("Pending for Re-Approval")) {
					allFilters.setStatusvalue(0);
					obj = homeDataFullReport.toTypeStatusReAppDate(allFilters, fordate);
				}
				// homeDataFullReport.toStatus(allFilters, fordate);
			}
		}
		return obj;
	}

	public static void main(String args[]) {
		try {
			getDischargeToSeaReport(allFilters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}