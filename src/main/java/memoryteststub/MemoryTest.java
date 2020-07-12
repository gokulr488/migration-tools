package memoryteststub;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import migrationtool.utils.Configs;

public class MemoryTest {
	static Connection conn;
	static Map<String, Long> map = new HashMap<String, Long>();

	public static void main(String[] args) throws SQLException, IOException {
		Configs.getAllValues();
		conn = DriverManager.getConnection(Configs.url, Configs.username, Configs.password);
		System.out.println("Connected to DB");
		Statement stmt = conn.createStatement();
		System.out.println("Executing :  " + Configs.customQuery + " at  :  " + System.currentTimeMillis());
		ResultSet res = stmt.executeQuery(Configs.customQuery);
		System.out.println("ResultSet obtained and inserting into Map at  :  " + System.currentTimeMillis());
		while (res.next()) {
			map.put(res.getString("iccid"), res.getLong("item_id"));
		}
		conn.close();
		System.gc();
		System.out.println("Map insertion completed " + "Getting value for iccid = " + Configs.lookUpColumn2
				+ "  at  : " + System.currentTimeMillis());
		System.out.println(map.get(Configs.lookUpColumn2));
		System.out.println("Value obtained at :  " + System.currentTimeMillis());
		System.out.println(
				(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000 + " MB used");

	}

}
