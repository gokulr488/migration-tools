package migrationtool.dbWrite;

import java.sql.ResultSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import migrationtool.datacollector.DbDataCollector;

public class DbWriteManager {

	BlockingQueue<ResultSet> dataQueue;

	public void start() {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		dataQueue = new ArrayBlockingQueue<>(2);

		DbDataCollector dataCollector = new DbDataCollector(dataQueue, null);
		DbWriter writer = new DbWriter(dataQueue);
		executorService.execute(dataCollector);
		executorService.execute(writer);
	}

}
