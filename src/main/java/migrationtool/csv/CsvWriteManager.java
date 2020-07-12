package migrationtool.csv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import migrationtool.datacollector.DbDataCollector;
import migrationtool.datacollector.H2Manager;
import migrationtool.dbconnection.ConnectionManager;
import migrationtool.lookup.LookUpManager;
import migrationtool.utils.Configs;

public class CsvWriteManager {

	Connection srcConn;
	Connection lookupConn;
	BlockingQueue<ResultSet> dataQueue;
	static Map<String, Integer> boxNo = new HashMap<String, Integer>();

	public void start() throws SQLException {

		ConnectionManager connManager = new ConnectionManager();

		try {
			srcConn = connManager.getSourceConnection();
		} catch (SQLException e) {
			System.out.println("Unable to obtain source connection");
			e.printStackTrace();
		}

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		dataQueue = new ArrayBlockingQueue<ResultSet>(2);
		if (Configs.lookUp.equals("INMEMORY")) {
			LookUpManager lookup = new LookUpManager(srcConn);
			boxNo = lookup.storeToMemory(Configs.lookUpTable);

		} else if (Configs.lookUp.equals("H2")) {
			H2Manager h2 = new H2Manager(srcConn);
			lookupConn=h2.getH2Connection();
			h2.createTable(Configs.lookUpTable);
			//h2.loadDataToTable(Configs.lookUpTable);
		}
		DbDataCollector dataCollector = new DbDataCollector(dataQueue, srcConn);
		CsvWriter writer = new CsvWriter(dataQueue,lookupConn);
		executorService.execute(dataCollector);
		executorService.execute(writer);

		executorService.shutdown();
		;

	}

}
