package migrationtool.lookup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LookUpManager {
	Connection conn;

	public LookUpManager(Connection conn) {
		this.conn = conn;
	}

	public Map<String, Integer> storeToMemory(String tableName) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		try {
			PreparedStatement sql = conn.prepareStatement("SELECT * FROM " + tableName + " ;");
			System.out.println("Obtaining data for lookup from "+tableName);
			ResultSet res = sql.executeQuery();
			System.out.println("Adding to Memory...");
			while (res.next()) {
				map.put(res.getString(2), res.getInt(1));
			}
			System.out.println("Added to Memory");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}
}
