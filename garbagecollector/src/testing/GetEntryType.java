package testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.nts.grb.connection.dbConnection;
import com.nts.orb1.service.GetTableName;

public class GetEntryType {

	public LinkedList<String> getEntryNames(int entry_id) {
		Connection con = null;

		LinkedList<String> lst = new LinkedList<>();
		try {
			con = dbConnection.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT entry_type from master_table_orb1 where entry_id >=?");
			ps.setInt(1, entry_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString(1).equalsIgnoreCase("MISSED ENTRY")) {

					ResultSet rs1 = ps.executeQuery(
							"select missed_entry_type from missed_entry_orb1 where source_entryid = " + entry_id);
					if (rs1.next()) {

						lst.add(GetTableName.getDbTableName(rs1.getString(1)));

					}

				} else {

					lst.add(GetTableName.getDbTableName(rs.getString(1)));

				}
			}
			System.out.println(lst);
			return lst;

		} catch (Exception e) {

		} finally {

			try {
				con.close();
			} catch (SQLException e) {

			}
		}
		return lst;
	}

	public static void main(String[] args) throws SQLException {

		System.out.println(new GetEntryType().getEntryNames(261));

	}

}
