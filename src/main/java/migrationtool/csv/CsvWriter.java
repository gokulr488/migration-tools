package migrationtool.csv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import migrationtool.datacollector.MetaData;
import migrationtool.lookup.LookUp;
import migrationtool.lookup.LookUpList;
import migrationtool.utils.Configs;
import migrationtool.utils.FileWriteUtils;

public class CsvWriter implements Runnable {
	private Connection lookupConn;
	private BlockingQueue<ResultSet> dataQueue;
	private int noOfRecordsWritten = 0;

	public CsvWriter(BlockingQueue<ResultSet> dataQueue, Connection lookupConn) {
		this.dataQueue = dataQueue;
		this.lookupConn = lookupConn;
	}

	@Override
	public void run() {
		try {

			writeToFile();
		} catch (InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void writeToFile() throws InterruptedException, SQLException {
		long startTime = 0L;
		FileWriteUtils writer = new FileWriteUtils();
		int fileNumber = 1;
		int outputBatchCounter = (int) Configs.outputBatchSize + 1;

		while (!MetaData.finishedPull || !dataQueue.isEmpty()) {

			if (outputBatchCounter >= Configs.outputBatchSize) {
				if (fileNumber != 1) {
					writer.close();
					System.out.println("ERROR: " + Configs.outputBatchSize + " No of records written to "
							+ Configs.table + "_" + (fileNumber - 1) + ".csv");
					System.out.println(Configs.outputBatchSize + " records written in "
							+ (System.currentTimeMillis() - startTime) + " ms");

				}

				writer.openFile(Configs.table + "_" + fileNumber + ".csv");
				System.out.println("opening " + Configs.table + "_" + fileNumber + ".csv" + " file");
				startTime = System.currentTimeMillis();
				writer.write(getHeader());

				fileNumber++;
				outputBatchCounter = 0;
			}
			if (Configs.lookUp.equals("INMEMORY")) {
				writer.write(getData(dataQueue.take(), getLookUpIndex(Configs.lookUpColumn)));
			} else if (Configs.lookUp.equals("H2")) {

				writer.write(getDataWithLookup(dataQueue.take(), getLookUpList()));
			} else {
				writer.write(getData(dataQueue.take()));
			}

			outputBatchCounter += Configs.batchSize;

		}
		System.out.println(
				"ERROR : File Write Process Finished for: " + Configs.table + " at " + System.currentTimeMillis());
		writer.close();
	}

	private LookUpList getLookUpList() {

		// TODO change hardcoding
		LookUpList lookUpList = new LookUpList();
		List<LookUp> list = new ArrayList<LookUp>();
		LookUp lookUp = new LookUp();
		lookUp.setTableName("view_item_box_no_mtr");
		lookUp.setColumnName("box_no");
		LookUp lookUp2 = new LookUp();
		lookUp2.setTableName("view_item_do_no_mtr");
		lookUp2.setColumnName("do_number");
		list.add(lookUp);
		// list.add(lookUp2);
		lookUpList.setLookUpList(list);
		return lookUpList;
	}

	private List<String> getDataWithLookup(ResultSet res, LookUpList lookUpList) throws SQLException {
		List<String> writeToFile = new ArrayList<String>();
		while (res.next()) {
			String data = "";
			for (int i = 1; i <= MetaData.columnCount; i++) {

				if (res.getString(i) == null) {
					data = data + Configs.csvDelimiter;
				} else {
					data = data + res.getString(i) + Configs.csvDelimiter;
				}
			}
			data = addLookupData(data, lookUpList, res);

			data = data.replaceAll("null", "");

			writeToFile.add(data);
		}
		noOfRecordsWritten = noOfRecordsWritten + writeToFile.size();
		res.close();
		// System.gc();
		return writeToFile;

	}

	private String addLookupData(String data, LookUpList lookUpList, ResultSet res) throws SQLException {
		for (LookUp lookUp : lookUpList.getLookUpList()) {
			lookUp.setLookUpIndex(getLookUpIndex(lookUp.getColumnName()));
			PreparedStatement stmt = lookupConn.prepareStatement("SELECT * FROM " + lookUp.getTableName() + " WHERE "
					+ lookUp.getColumnName() + " = '" + res.getString(lookUp.getLookUpIndex()) + "' ;");
			ResultSet lookUpRes = stmt.executeQuery();
			if (lookUpRes.next()) {
				System.out.println(res.getString(lookUp.getLookUpIndex()) + " , " + lookUpRes.getString(1));
				data = data + lookUpRes.getString(1);
			}

		}
		return data;
	}

	private int getLookUpIndex(String lookUpCol) {
		int index = 1;
		for (String col : MetaData.columns) {
			if (col.equals(lookUpCol)) {
				return index;
			}
			index++;
		}
		return 0;
	}

	private List<String> getData(ResultSet res) throws SQLException {

		List<String> writeToFile = new ArrayList<String>();
		while (res.next()) {
			String data = "";
			for (int i = 1; i <= MetaData.columnCount; i++) {

				if (res.getString(i) == null) {
					data = data + Configs.csvDelimiter;
				} else {
					data = data + res.getString(i) + Configs.csvDelimiter;
				}
			}
			data = data.substring(0, data.length() - 1);
			data = data.replaceAll("\n", "");
			writeToFile.add(data);
		}
		noOfRecordsWritten = noOfRecordsWritten + writeToFile.size();
		res.close();
		// System.gc();
		return writeToFile;
	}

	private List<String> getData(ResultSet res, int lookUpIndex) throws SQLException {

		List<String> writeToFile = new ArrayList<String>();
		while (res.next()) {
			String data = "";
			for (int i = 1; i <= MetaData.columnCount; i++) {
				String col = res.getString(i);

				if (col == null) {
					data = data + Configs.csvDelimiter;
				} else {
					data = data + col.trim() + Configs.csvDelimiter;
				}
			}
			data = data + CsvWriteManager.boxNo.get(res.getString(lookUpIndex));
			data = data.replaceAll("\n", "");
			data = data.replaceAll("null", "");

			writeToFile.add(data);
		}
		noOfRecordsWritten = noOfRecordsWritten + writeToFile.size();
		res.close();
		// System.gc();
		return writeToFile;
	}

	private String getHeader() {
		String header = "";
		for (String colName : MetaData.columns) {
			header = header + colName + Configs.csvDelimiter;
		}
		return header.substring(0, header.length() - 1);
	}

}
