package migrationtool.datacollector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import org.stringtemplate.v4.ST;

import migrationtool.dbconnection.ConnectionManager;
import migrationtool.utils.Configs;
import migrationtool.utils.GenUtils;

public class DbDataCollector implements Runnable {

	private BlockingQueue<ResultSet> dataQueue;

	private Connection srcConn;
	private String selectQuery;
	// private Statement selectStatement;

	public DbDataCollector(BlockingQueue<ResultSet> dataQueue, Connection srcConn) {
		this.dataQueue = dataQueue;
		this.srcConn = srcConn;

		try {
			getMetaData();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		long offset = Configs.startingRecord;
		long dataLimit = Configs.endingRecord;

		if (dataLimit == 0) {
			dataLimit = MetaData.rowCount;
		}
		System.out.println("Fetching data from source started at :" + System.currentTimeMillis());
		while (offset <= (dataLimit)) {
			try {
				dataQueue.put(getNextBatch(offset));
				// System.gc();

			} catch (SQLException | InterruptedException e) {
				
				e.printStackTrace();
			}
			offset = offset + Configs.batchSize;
		}
		System.out.println("Data pull completed..!!");
		MetaData.finishedPull = true;

	}

	public void getMetaData() throws SQLException {

		if (!(Configs.endingRecord == 0)) {
			MetaData.rowCount = Configs.endingRecord;
		} else {
			PreparedStatement selectStatement = srcConn.prepareStatement("select count(*) from " + Configs.table + ";");
			System.out.println("select count(*) from " + Configs.table + ";");
			ResultSet res = selectStatement.executeQuery();

			res.next();
			MetaData.rowCount = res.getLong(1);
		}
		System.out.println("No of rows = " + (MetaData.rowCount - Configs.startingRecord));

		ST template = GenUtils.getTemplate("metaData");
		template.add("columns", Configs.columns);
		template.add("table", Configs.table);
		PreparedStatement selectStatement = srcConn.prepareStatement(template.render());
		ResultSet res = selectStatement.executeQuery();

		ResultSetMetaData rsmd = res.getMetaData();
		MetaData.columnCount = rsmd.getColumnCount();

		for (int i = 1; i <= MetaData.columnCount; i++) {
			MetaData.columns.add(rsmd.getColumnName(i));
			// cols = cols + rsmd.getColumnName(i) + AppConfigs.csvDelimiter;
		}
		
		//TODO get primary key for order by clause from metadata

		// Pre building select query
		template = GenUtils.getTemplate("getData");
		template.add("columns", Configs.columns);
		template.add("table", Configs.table);
		template.add("limit", Configs.batchSize);
		template.add("orderBy", Configs.orderByColumn);
		// template.add("primaryKey", Configs.orderByColumn);
		selectQuery = template.render();

	}

	public ResultSet getNextBatch(long offset) throws SQLException {
		PreparedStatement selectStatement = srcConn.prepareStatement(selectQuery + offset + ";");
		System.out.println("Fetching records from :" + offset + " till :" + (Configs.batchSize + offset));

		ResultSet res = selectStatement.executeQuery();

		return res;
	}

}
