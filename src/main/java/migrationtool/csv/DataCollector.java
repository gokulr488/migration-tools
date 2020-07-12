package migrationtool.csv;

import java.sql.ResultSet;
import java.util.concurrent.BlockingQueue;

public class DataCollector implements Runnable {

	private BlockingQueue<ResultSet> dataQueue;

	public DataCollector(BlockingQueue<ResultSet> dataQueue) {
		this.dataQueue = dataQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				
				dataQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
