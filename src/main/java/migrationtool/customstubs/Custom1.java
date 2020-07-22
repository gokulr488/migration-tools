package migrationtool.customstubs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import migrationtool.dbconnection.ConnectionManager;
import migrationtool.utils.Configs;

public class Custom1 {
	Connection srcConn;
	Map<String, Integer> map;

	public void start() {
		ConnectionManager conManager = new ConnectionManager();
		try {
			this.srcConn = conManager.getSourceConnection();
			if (Configs.outputColumns.equals("stsSource")) {

				calulateStsSource();
			} else if (Configs.outputColumns.equals("stsTarget")) {
				calculateStsTarget();

			} else if (Configs.outputColumns.equals("srisSource")) {
				calculateSrisSource();
			} else if (Configs.outputColumns.equals("srisTarget")) {
				calculateSrisTarget();
			}

			writeToFile(convertToSTSPojo(), convertToSRISPojo());
			System.out.println("ERROR :  Process Finished ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private List<SRISPojo> convertToSRISPojo() {
		List<SRISPojo> output = new ArrayList<SRISPojo>();
		for (String key : map.keySet()) {
			SRISPojo pojo = new SRISPojo();
			String[] data = key.split(",");
			pojo.setRegion_id(data[0]);
			pojo.setCluster_id(data[1]);
			pojo.setCount(map.get(key));
			output.add(pojo);
		}

		return output;
	}

	private List<STSPojo> convertToSTSPojo() {
		List<STSPojo> output = new ArrayList<STSPojo>();
		for (String key : map.keySet()) {
			STSPojo pojo = new STSPojo();
			String[] data = key.split(",");
			pojo.setIstatus(data[1]);
			pojo.setOriginal_distributor_code(data[0]);
			pojo.setItem_code(data[2]);
			pojo.setCount(map.get(key));
			output.add(pojo);
		}

		return output;
	}

	private void calculateSrisTarget() throws SQLException {
		map = new HashMap<String, Integer>();

		for (int i = 0; i < Configs.endingRecord; i += Configs.batchSize) {
			String sql = "select region_id,cluster_id from asset_details limit " + Configs.batchSize + " offset " + i
					+ ";";
			System.out.println(sql);
			Statement st = srcConn.createStatement();
			System.out.println("Getting data with offset= " + i + " for SRIS at  : " + System.currentTimeMillis());
			ResultSet res = st.executeQuery(sql);
			System.out.println("Data set Obtained. Calculating count at : " + System.currentTimeMillis());
			while (res.next()) {
				String data = res.getString("region_id") + "," + res.getString("cluster_id");
				addOrIncrementMap(data);
			}
			System.out.println("Size of map = " + map.size());
			st.close();
			res.close();
		}
		System.out.println("Count Calculated.  Writing to File...");

	}

	private void calculateSrisSource() throws SQLException {
		map = new HashMap<String, Integer>();

		for (int i = 1; i < 11; i++) {
			String sql = "select region_id_item,cluster_id_item from view_sris_t_mtr_item_mgr_" + i + " limit 50;";
			Statement st = srcConn.createStatement();
			System.out.println("Getting data from SRIS view " + i + " at  : " + System.currentTimeMillis());
			ResultSet res = st.executeQuery(sql);
			System.out.println("Data set Obtained. Calculating count at : " + System.currentTimeMillis());
			while (res.next()) {
				String data = res.getString("region_id_item") + "," + res.getString("cluster_id_item");
				addOrIncrementMap(data);
			}
			System.out.println("Size of map = " + map.size());
			st.close();
			res.close();
		}
		System.out.println("Count Calculated.  Writing to File...");

	}

	private void calculateStsTarget() throws SQLException {
		map = new HashMap<String, Integer>();

		for (int i = 0; i < Configs.endingRecord; i += Configs.batchSize) {
			String sql = "select owner_id,asset_states_id,product_id from asset_details limit " + Configs.batchSize
					+ " offset " + i + ";";
			System.out.println(sql);
			Statement st = srcConn.createStatement();
			System.out.println("Getting data with offset= " + i + " for STS at  : " + System.currentTimeMillis());
			ResultSet res = st.executeQuery(sql);
			System.out.println("Data set Obtained. Calculating count at : " + System.currentTimeMillis());
			while (res.next()) {
				String data = res.getString("owner_id") + "," + res.getString("asset_states_id") + ","
						+ res.getString("product_id");
				addOrIncrementMap(data);
			}
			System.out.println("Size of map = " + map.size());
			st.close();
			res.close();
		}
		System.out.println("Count Calculated.  Writing to File...");

	}

	private void writeToFile(List<STSPojo> STSMap, List<SRISPojo> SRISMap) {
		SaveReconcilation save = new SaveToFile();
		save.persistSTS(STSMap);
		save.persistSRIS(SRISMap);
//		
//		FileWriteUtils writer = new FileWriteUtils();
//		writer.openFile(Configs.outputPath);
//		writer.write("original_distributor_code,istatus,item_code,count");
//		System.out.println("Writing to " + Configs.outputPath);
//		List<String> putToFile = new ArrayList<String>();
//		for (String data : map.keySet()) {
//			data = data + "," + map.get(data);
//			putToFile.add(data);
//			if (putToFile.size() > Configs.outputBatchSize) {
//				writer.write(putToFile);
//				putToFile.clear();
//				System.out.println(Configs.outputBatchSize + " Records written to File ");
//			}
//		}
//		writer.write(putToFile);
//		writer.close();
	}

	private void calulateStsSource() throws SQLException {
		map = new HashMap<String, Integer>();

		for (int i = 1; i < 13; i++) {
			String sql = "select original_distributor_code,istatus,item_code from view_item_combined_mgr_" + i + " ;";
			Statement st = srcConn.createStatement();
			System.out.println("Getting data from view " + i + " at  : " + System.currentTimeMillis());
			ResultSet res = st.executeQuery(sql);
			System.out.println("Data set Obtained. Calculating count at : " + System.currentTimeMillis());
			while (res.next()) {
				String data = res.getString("original_distributor_code") + "," + res.getString("istatus") + ","
						+ res.getString("item_code");
				addOrIncrementMap(data);
			}
			System.out.println("Size of map = " + map.size());
			st.close();
			res.close();
		}
		System.out.println("Count Calculated.  Writing to File...");
	}

	private void addOrIncrementMap(String data) {
		map.putIfAbsent(data, 0);
		map.put(data, map.get(data) + 1);

	}
}
