package migrationtool.customquery;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import migrationtool.datacollector.MetaData;
import migrationtool.dbconnection.ConnectionManager;
import migrationtool.lookup.LookUpManager;
import migrationtool.utils.Configs;
import migrationtool.utils.FileWriteUtils;

public class CustomQuery {

	private Connection srcConn;
	Map<Integer, String> map;

	public void start() {
		ConnectionManager conManager = new ConnectionManager();
		try {
			this.srcConn = conManager.getSourceConnection();
			writeToFile();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeToFile() {
		try {

			FileWriteUtils writer = new FileWriteUtils();
			writer.openFile(Configs.outputPath);

			/*
			 * map = new HashMap<Integer, String>(); Statement st =
			 * srcConn.createStatement(); ResultSet lookRes =
			 * st.executeQuery("SELECT item_mapping_id,item_code FROM t_mtr_item_mapping;");
			 * while (lookRes.next()) { map.put(lookRes.getInt(1), lookRes.getString(2)); }
			 * System.out.println("Added to Memory");
			 */

			for (String sql : querySplitter(Configs.customQuery)) {
				ResultSet res = getCustomQuery(sql);
				System.out.println("Result Obtained. Writing to File...");
				if (res != null) {
					writeHeader(res, writer);
					// writer.write(Configs.outputColumns);
					writer.write(getData(res));
				}
				writer.close();

			}
			System.out.println("ERROR :Finished Executions");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private String[] querySplitter(String customQuery) {
		String[] queries = customQuery.split("&");
		return queries;

	}

	private List<String> getData(ResultSet res) throws SQLException {
		List<String> writeToFile = new ArrayList<String>();
		while (res.next()) {
			String data = "";
			// data = data + map.get(res.getInt(1)) + Configs.csvDelimiter;
			for (int i = 1; i <= MetaData.columnCount; i++) {

				if (res.getString(i) == null) {
					data = data + Configs.csvDelimiter;
				} else {

					data = data + res.getString(i) + Configs.csvDelimiter;
				}
			}
			data = data.substring(0, data.length() - 1);
			data = "testing \n";
			data = data.replaceAll("\n", "");
			writeToFile.add(data);
		}

		res.close();

		return writeToFile;
	}

	private void writeHeader(ResultSet res, FileWriteUtils writer) throws SQLException {
		ResultSetMetaData rsmd = res.getMetaData();
		MetaData.columnCount = rsmd.getColumnCount();

		for (int i = 1; i <= MetaData.columnCount; i++) {
			MetaData.columns.add(rsmd.getColumnName(i));
			// cols = cols + rsmd.getColumnName(i) + AppConfigs.csvDelimiter;
		}
		String header = "";
		for (String colName : MetaData.columns) {
			header = header + colName + Configs.csvDelimiter;
		}
		header = header.substring(0, header.length() - 1);
		writer.write(header);
	}

	public ResultSet getCustomQuery(String sql) throws SQLException {
		Statement selectStatement = srcConn.createStatement();

		System.out.println("Executing ... " + sql);

		if (sql.contains("SELECT") || sql.contains("select")) {
			ResultSet res = selectStatement.executeQuery(sql);
			System.out.println("Executed " + sql);
			return res;
		} else if (sql.contains("INSERT") || sql.contains("insert")) {
			selectStatement.executeUpdate(sql);
			System.out.println("Executed " + sql);
			return null;
		} else if(sql.contains("LOAD") || sql.contains("load")){
			Statement loadStmt = srcConn.createStatement();
			loadStmt.setLocalInfileInputStream();
			loadStmt.execute(sql);
			System.out.println("Executed " + sql);
			return null;
		}

		/*
		 * try { ResultSet res = selectStatement.executeQuery(sql);
		 * System.out.println("Executed " + sql); return res; } catch (Exception e) {
		 * try { selectStatement.executeUpdate(sql); System.out.println("Executed " +
		 * sql); return null; } catch (Exception e1) { try {
		 * selectStatement.execute(sql); System.out.println("Executed " + sql); } catch
		 * (SQLException e2) { System.out.println("Failed to execute : " + sql);
		 * e2.printStackTrace(); }
		 * 
		 * return null; } }
		 */

	}

}
