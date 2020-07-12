package migrationtool.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configs {

	public static String database;
	public static String username;
	public static String password;
	public static String url;
	public static String table;
	// public static String orderByColumn;
	public static long batchSize;
	public static String columns;
	public static String csvDelimiter;

	public static String outputMode;
	public static String targetUserName;
	public static String targetPassword;
	public static String targetUrl;
	public static String tragetTable;
	public static long outputBatchSize;
	public static String outputColumns;
	public static long startingRecord;
	public static long endingRecord;
	public static String customQuery;
	public static String outputPath;
	public static String lookUp;
	public static String lookUpTable;
	public static String lookUpColumn;
	public static String lookUpTable2;
	public static String lookUpColumn2;

	static FileInputStream fileInputStream;

	public static void getAllValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "app.config";

			fileInputStream = new FileInputStream(new File(propFileName));

			if (fileInputStream != null) {
				prop.load(fileInputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			database = prop.getProperty("database");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			batchSize = Long.valueOf(prop.getProperty("batchSize"));
			// orderByColumn = prop.getProperty("orderByColumn");
			url = prop.getProperty("url");
			columns = prop.getProperty("columns");
			csvDelimiter = prop.getProperty("csvDelimiter");
			startingRecord = Long.valueOf(prop.getProperty("startingRecord"));
			endingRecord = Long.valueOf(prop.getProperty("endingRecord"));

			outputMode = prop.getProperty("outputMode");
			customQuery = prop.getProperty("customQuery");
			targetUserName = prop.getProperty("targetUserName");
			targetPassword = prop.getProperty("targetPassword");
			targetUrl = prop.getProperty("targetUrl");
			tragetTable = prop.getProperty("tragetTable");
			outputBatchSize = Long.valueOf(prop.getProperty("outputBatchSize"));
			outputColumns = prop.getProperty("outputColumns");

			outputPath = prop.getProperty("outputPath");

			table = prop.getProperty("tables");
			lookUp = prop.getProperty("lookUp");
			lookUpTable = prop.getProperty("lookUpTable");
			lookUpColumn = prop.getProperty("lookUpColumn");
			lookUpTable2 = prop.getProperty("lookUpTable2");
			lookUpColumn2 = prop.getProperty("lookUpColumn2");

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			fileInputStream.close();
		}

	}

}
