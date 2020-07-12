package TableReferenceFinder;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Start {
	public static void main(String[] args) throws Exception {
		AppConfigs.getAllValues();

		Connection conn = getMySqlConnection();
		System.out.println("Got Connection.");
		Statement st = conn.createStatement();
		//st.executeUpdate("drop table survey;");
		//st.executeUpdate("create table survey (id int,name varchar(30));");
		//st.executeUpdate("insert into survey (id,name ) values (1,'nameValue')");

		ResultSet rs = null;
		DatabaseMetaData meta = conn.getMetaData();
		// The Oracle database stores its table names as Upper-Case,
		// if you pass a table name in lowercase characters, it will not work.
		// MySQL database does not care if table name is uppercase/lowercase.
		//
		rs = meta.getExportedKeys(conn.getCatalog(), "pointofsales", "shopmaster");
		while (rs.next()) {
			String fkTableName = rs.getString("FKTABLE_NAME");
			String fkColumnName = rs.getString("FKCOLUMN_NAME");
			int fkSequence = rs.getInt("KEY_SEQ");
			System.out.println("getExportedKeys(): fkTableName=" + fkTableName);
			System.out.println("getExportedKeys(): fkColumnName=" + fkColumnName);
			System.out.println("getExportedKeys(): fkSequence=" + fkSequence);
		}

		st.close();
		conn.close();
	}

	private static Connection getHSQLConnection() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		System.out.println("Driver Loaded.");
		String url = "jdbc:hsqldb:data/tutorial";
		return DriverManager.getConnection(url, "sa", "");
	}

	public static Connection getMySqlConnection() throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/pointofsales";
		String username = "oost";
		String password = "oost";

		
		Connection conn = DriverManager.getConnection(AppConfigs.url, AppConfigs.username, AppConfigs.password);
		return conn;
	}

	public static Connection getOracleConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:caspian";
		String username = "mp";
		String password = "mp2";

		Class.forName(driver); // load Oracle driver
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
}
