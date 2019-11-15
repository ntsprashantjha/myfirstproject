package testing;

import java.sql.ResultSet;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.model.AllFilters;
import com.nts.grb.query.HomeDataFullReportById;

public class FilterDtls {

	String entryName[] = { " ", "ALL", "Discharge to sea" };
	String entryCate[] = { " ", "ALL", "A-plastic" };
	String entryStatus[] = { " ", "ALL", "Approved" };
	String en = entryName[1];
	String ec = entryCate[2];
	String es = entryStatus[2];

	public JSONArray getDtls(AllFilters alf) throws Exception {

		FilterDao fld = new FilterDao();

		String grbentrytype = "Sea/Ashore";
		String GarbageCat = "G", todate = "", fordate = "", status = "", tablename2 = "Discharge_To_Sea",
				tablename1 = "Discharge_To_Ashore";

		ResultSet resultSet;
		JSONArray obj = new JSONArray();
		JSONArray obj1 = new JSONArray();

		com.nts.grb.query.HomeDataFullReport1 homeDataFullReport = new com.nts.grb.query.HomeDataFullReport1();

		en = alf.getEntrytype();
		ec = alf.getCategory();
		es = alf.getStatus();

		System.out.println(en + "\n" + ec + "\n" + es + alf.getTodate() + "\n" + alf.getFordate());
		obj = HomeDataFullReportById.entry_id(alf.getTodate(), alf.getFordate(), dbConnection.getConnection());

		return obj;
	}

}
