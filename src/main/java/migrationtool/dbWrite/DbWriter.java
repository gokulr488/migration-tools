package migrationtool.dbWrite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import migrationtool.dbconnection.ConnectionManager;

public class DbWriter implements Runnable {

	private BlockingQueue<ResultSet> dataQueue;
	private Connection targetCon;

	public DbWriter(BlockingQueue<ResultSet> dataQueue) {
		this.dataQueue = dataQueue;
	}

	@Override
	public void run() {
		getTargetConnection();
		try {
			TargetMetaData metaDate = getMetaData();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private TargetMetaData getMetaData() throws SQLException {
		MetaDataCollector collector = new MetaDataCollector(targetCon);
		return collector.getMetaData();

	}

	private void getTargetConnection() {
		ConnectionManager conManager = new ConnectionManager();
		try {
			targetCon = conManager.getTargetConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
