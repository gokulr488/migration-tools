package migrationtool.customstubs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import migrationtool.dbconnection.ConnectionManager;
import migrationtool.utils.Configs;
import migrationtool.utils.FileWriteUtils;

public class Custom {

	Connection srcConn;
	Map<Integer, Map<String, Integer>> distributorIds;
	Map<String, Integer> itemMappingIds;

	public void start() {
		ConnectionManager conManager = new ConnectionManager();
		try {
			this.srcConn = conManager.getSourceConnection();
			distributorIds = new HashMap<Integer, Map<String, Integer>>();
			itemMappingIds = new HashMap<String, Integer>();
			getItemMappingIds();
			getDistributorId();

			calculateCount();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void calculateCount() throws SQLException {

		for (int i = 1; i < 13; i++) {
			String sql = "select distributor_id,item_code from view_item_combined_mgr_" + i + " limit 50;";
			Statement st = srcConn.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				int count=distributorIds.get(res.getInt("distributor_id")).get(res.getString("item_code"));
				
			}
		}

	}

	private void getItemMappingIds() throws SQLException {
		Statement st = srcConn.createStatement();
		ResultSet res = st.executeQuery("SELECT item_code from t_mtr_item_mapping limit 50;");
		while (res.next()) {
			itemMappingIds.put(res.getString("item_code"), 0);
		}
		System.out.println("Obtained item item_code");
		res.close();
		st.close();
	}

	private void getDistributorId() throws SQLException {
		Statement st = srcConn.createStatement();
		ResultSet res = st.executeQuery("SELECT distributor_id from t_mtr_distributor limit 50;");
		while (res.next()) {
			Map<String, Integer> itemCountMap = new HashMap<String, Integer>();
			itemCountMap = itemMappingIds;
			distributorIds.put(res.getInt("distributor_id"), itemCountMap);
		}
		System.out.println("Obtained distributor IDs");
		res.close();
		st.close();
	}

}
