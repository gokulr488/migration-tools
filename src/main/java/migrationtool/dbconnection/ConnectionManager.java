package migrationtool.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import TableReferenceFinder.AppConfigs;
import migrationtool.utils.Configs;

public class ConnectionManager {
	private BasicDataSource sourceBDS = new BasicDataSource();

	public Connection getSourceConnection() throws SQLException {

		sourceBDS.setUrl(Configs.url);
		sourceBDS.setUsername(Configs.username);
		sourceBDS.setPassword(Configs.password);
		sourceBDS.setMinIdle(5);
		sourceBDS.setMaxIdle(8);
		sourceBDS.setMaxOpenPreparedStatements(25);

		/*
		 * Connection conn = DriverManager.getConnection(Configs.url, Configs.username,
		 * Configs.password);
		 */

		Connection conn = sourceBDS.getConnection();

		System.out.println("Connected to source DB");
		return conn;

	}

	public void closeSourceConnection() {
		try {
			sourceBDS.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getTargetConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(Configs.targetUrl, Configs.targetUserName,
				AppConfigs.targetPassword);
		System.out.println("Connected to target DB");
		return conn;

	}

}
