package memoryteststub;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import migrationtool.utils.FileWriteUtils;

public class TestCsvBuilder {
	static Connection conn;
	public static void main(String[] args) {
		
		try {
			Statement stmt = (Statement) conn.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FileWriteUtils writer = new FileWriteUtils();
		writer.openFile("tempTable.csv");
		String delimiter = "|";
		for (int i = 0; i < 100; i++) {
			String data = i + delimiter + i + delimiter + i + delimiter;

			data = data.replaceAll("\\r\\n", "");
			writer.write(data);
		}
		writer.close();

	}

}
