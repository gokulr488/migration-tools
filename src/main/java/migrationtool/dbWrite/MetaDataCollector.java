package migrationtool.dbWrite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import migrationtool.utils.Configs;

public class MetaDataCollector {

	private Connection conn;

	public MetaDataCollector(Connection conn) {
		this.conn = conn;
	}

	public TargetMetaData getMetaData() throws SQLException {
		TargetMetaData metadata = new TargetMetaData();
		
		DatabaseMetaData dataBaseMD = conn.getMetaData();
		ResultSet res = dataBaseMD.getColumns(null, null, Configs.tragetTable, null);
		List<String> colsAdded = new ArrayList<String>();
		List<Column> colmns = new ArrayList<Column>();
		while (res.next()) {

			Column column = new Column();
			column.setColumnName(res.getString("COLUMN_NAME"));

			column.setDataType(res.getString("TYPE_NAME"));

			if (colsAdded.contains(column.getColumnName())) {
				System.out.println("Column exist already");
			} else {
				colmns.add(column);
				colsAdded.add(column.getColumnName());
			}
		}
		metadata.setColmns(colmns);

		return metadata;
	}

}
