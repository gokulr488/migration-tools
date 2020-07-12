package tabletocsv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import TableReferenceFinder.AppConfigs;
import TableScriptGenerator.scripttofile.FileWriteAdapter;

public class TableToCsv {

	public static void main(String[] args) throws IOException, SQLException {
		AppConfigs.getAllValues();
		Connection conn = getConnection();

		for (String table : AppConfigs.tables) {
			startWriting(conn, table);
			System.out.println("Finished " + table);
		}

	}

	private static void startWriting(Connection conn, String table) throws SQLException, IOException {
		FileWriteAdapter writer = new FileWriteAdapter();
		writer.writeTo(table + ".csv");

		Statement selectStatement = conn.createStatement();

		long rowCount = AppConfigs.endingRecord;

		if (AppConfigs.endingRecord == 0) {
			ResultSet res = selectStatement.executeQuery("select count(*) from " + table + ";");
			res.next();
			rowCount = res.getLong(1);
		}

		System.out.println("No of rows = " + (rowCount - AppConfigs.startingRecord));

		int colCount = getMetaData(selectStatement, table, writer);

		ST template = new ST("SELECT <columns> FROM <table> LIMIT <limit> OFFSET ");

		template.add("columns", AppConfigs.columns);
		template.add("table", table);
		template.add("limit", AppConfigs.batchSize);
		String sql = template.render();
		long offset = 0L;
		while (offset <= rowCount) {
			System.out.println(sql + offset);
			ResultSet res = selectStatement.executeQuery(sql + offset);

			writeToFile(res, writer, colCount);
			offset = offset + AppConfigs.batchSize;
			System.out.println(offset + " records processed");
		}
		writer.close();

	}

	private static int getMetaData(Statement selectStatement, String table, FileWriteAdapter writer)
			throws SQLException {
		ST template = new ST("SELECT <columns> FROM <table> LIMIT 1");
		template.add("columns", AppConfigs.columns);
		template.add("table", table);

		String sql = template.render();
		ResultSet res = selectStatement.executeQuery(sql);

		ResultSetMetaData rsmd = res.getMetaData();
		int colCount = rsmd.getColumnCount();

		String cols = "";
		for (int i = 1; i <= colCount; i++) {
			cols = cols + rsmd.getColumnName(i) + AppConfigs.csvDelimiter;
		}
		writer.write(cols.substring(0, cols.length() - 1));
		return colCount;

	}

	private static void writeToFile(ResultSet res, FileWriteAdapter writer, int colCount)
			throws IOException, SQLException {

		List<String> writeToFile = new ArrayList<String>();

		while (res.next()) {
			String data = "";
			for (int i = 1; i <= colCount; i++) {

				if (res.getString(i) == null) {
					data = data + AppConfigs.csvDelimiter;
				} else {
					data = data + res.getString(i) + AppConfigs.csvDelimiter;
				}

			}
			data = data.substring(0, data.length() - 1);
			writeToFile.add(data);
		}
		writer.write(writeToFile);

	}

	private static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(AppConfigs.url, AppConfigs.username, AppConfigs.password);
		System.out.println("Connected to DB");
		return conn;

	}
}
