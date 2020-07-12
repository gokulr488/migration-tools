package migrationtool.datacollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.TabExpander;

import com.mysql.cj.protocol.Resultset;

import migrationtool.utils.Configs;

public class H2Manager {
	Connection srcConn;
	Connection h2Conn;

	public H2Manager(Connection srcConn) {
		this.srcConn = srcConn;

	}

	public Connection getH2Connection() {

		try {
			h2Conn = DriverManager.getConnection("jdbc:h2:mem:lookup", "lookup", "lookup");
			h2Conn.setAutoCommit(false);
			return h2Conn;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	public void createTable(String tableName) throws SQLException {
		Statement statement = h2Conn.createStatement();
		statement.execute("CREATE MEMORY TABLE " + tableName + " (box_id INT,box_no VARCHAR(50));");
		h2Conn.commit();
		loadDataToTable(tableName);

	}

	public void loadDataToTable(String tableName) {
		try {
			PreparedStatement sql = srcConn.prepareStatement("SELECT * FROM " + tableName + " ;");
			System.out.println("Obtaining data for lookup from " + tableName);
			ResultSet res = sql.executeQuery();
			System.out.println("Adding to Memory...");

			PreparedStatement insertSql = h2Conn.prepareStatement("INSERT INTO " + tableName + " VALUES(?,?)");
			int insertedCount = 0;
			while (res.next()) {
				insertSql.setInt(1, res.getInt(1));
				insertSql.setString(2, res.getString(2));
				insertSql.addBatch();
				/*
				 * System.out.println(res.getInt(1)); System.out.println(res.getString(2));
				 */

				if (insertedCount > 100000) {
					insertSql.executeBatch();
					insertedCount = 0;
				}
				insertedCount++;
			}

			insertSql.executeBatch();
			h2Conn.commit();
			System.out.println("Added to Memory");

			/*
			 * insertSql = h2Conn.
			 * prepareStatement("SELECT * FROM view_item_box_no_mtr WHERE box_no = '1200071409'"
			 * ); res = insertSql.executeQuery(); while (res.next()) {
			 * System.out.println(res.getString(1) + " , " + res.getString(2)); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
