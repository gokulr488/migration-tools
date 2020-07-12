package TableScriptGenerator.dboperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import TableScriptGenerator.ScriptGenConfig;
import TableScriptGenerator.model.Metadata;

public class ConnectionManager {

	private Connection connection;

	public Connection getSourceConnection() throws SQLException {

		connection = DriverManager.getConnection(ScriptGenConfig.url, ScriptGenConfig.username,
				ScriptGenConfig.password);
		System.out.println("Connected to DB");
		return connection;

	}

	public Metadata getMySqlMetaData() throws SQLException {

		MetaDataCollection dataCollector = new MetaDataCollection();
		return dataCollector.getMetaData(connection);
	}

}
