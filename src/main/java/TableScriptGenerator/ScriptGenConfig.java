package TableScriptGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ScriptGenConfig {

	public static String username;
	public static String password;
	public static String url;
	public static String[] tables;
	public static boolean writeToFile = true;
	public static String filepath;
	public static boolean applyToDb;
	public static String schema;
	public static boolean autoIncrement;
	public static String[] avoidColumns;
	public static String appenddbName;
	public static boolean includeForeignKeys;
	public static String createFor;

	static FileInputStream fileInputStream;

	static void readValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "scriptGen.config";

			fileInputStream = new FileInputStream(new File(propFileName));

			if (fileInputStream != null) {
				prop.load(fileInputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			username = prop.getProperty("username").trim();
			password = prop.getProperty("password").trim();
			url = prop.getProperty("url").trim();
			filepath = prop.getProperty("filepath").trim();
			schema = prop.getProperty("schemas").trim();
			createFor = prop.getProperty("createFor").trim();

			appenddbName = prop.getProperty("appenddbName").trim();
			String tblStr = prop.getProperty("tables").trim();
			if (tblStr != null) {
				tables = tblStr.split(",");
			}
			String avoidCols = prop.getProperty("avoidColumns");
			if (avoidCols != null) {
				avoidColumns = avoidCols.split(",");
			}

			if (prop.getProperty("writeToFile").equals("false")) {
				writeToFile = false;
			} else {
				writeToFile = true;
			}

			if (prop.getProperty("applyToDb").equals("false")) {
				applyToDb = false;
			} else {
				applyToDb = true;
			}
			if (prop.getProperty("autoIncrement").equals("false")) {
				autoIncrement = false;
			} else {
				autoIncrement = true;
			}

			if (prop.getProperty("includeForeignKeys").equals("false")) {
				includeForeignKeys = false;
			} else {
				includeForeignKeys = true;
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			fileInputStream.close();
		}

	}

}
