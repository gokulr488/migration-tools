package TableScriptGenerator.dboperations;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import TableScriptGenerator.ScriptGenConfig;
import TableScriptGenerator.model.Column;
import TableScriptGenerator.model.ForeignKey;
import TableScriptGenerator.model.Metadata;
import TableScriptGenerator.model.PrimaryKey;
import TableScriptGenerator.model.Table;

public class MetaDataCollection {
	Connection conn;

	Metadata getMetaData(Connection conn) throws SQLException {
		this.conn = conn;
		DatabaseMetaData dataBaseMD = conn.getMetaData();
		Metadata metadata = new Metadata();
		metadata.setTables(getTables(dataBaseMD));

		return metadata;
	}

	private List<Table> getTables(DatabaseMetaData dataBaseMD) throws SQLException {

		List<Table> tables = new ArrayList<Table>();
		for (String tableName : ScriptGenConfig.tables) {

			ResultSet res = dataBaseMD.getTables(null, ScriptGenConfig.schema, tableName, null);
			if (res.next()) {
				res.previous();
				while (res.next()) {
					Table table = new Table();
					table.setTabelName(ScriptGenConfig.appenddbName+res.getString("Table_NAME"));
					table.setTableSchema(res.getString("TABLE_SCHEM"));
					table.setColumns(getColumns(dataBaseMD, tableName));
					table.setPrimaryKeys(getPrimaryKey(dataBaseMD, tableName));
					table.setForeignKeys(getForeignKeys(dataBaseMD, tableName));
					tables.add(table);
					System.out.println("MetaData collected for '" + tableName + "' table");
				}
			} else {
				System.out.println("table '" + tableName + "' not found");
			}

		}
		return tables;

	}

	private List<ForeignKey> getForeignKeys(DatabaseMetaData dataBaseMD, String tableName) throws SQLException {
		ResultSet res = dataBaseMD.getImportedKeys(null, null, tableName);
		List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
		List<String> fkAdded = new ArrayList<String>();
		while (res.next()) {
			ForeignKey foreignKey = new ForeignKey();
			foreignKey.setForeignTableName(ScriptGenConfig.appenddbName+res.getString("pktable_name"));
			foreignKey.setForeignColumnName(res.getString("pkcolumn_name"));
			foreignKey.setRefferingColumnName(res.getString("fkcolumn_name"));

			if (fkAdded.contains(foreignKey.getRefferingColumnName())) {
				System.out.println("Foreign Key exist already");
			} else {
				foreignKeys.add(foreignKey);
				fkAdded.add(foreignKey.getRefferingColumnName());
			}
		}

		return foreignKeys;
	}

	private List<PrimaryKey> getPrimaryKey(DatabaseMetaData dataBaseMD, String tableName) throws SQLException {
		ResultSet res = dataBaseMD.getPrimaryKeys(null, null, tableName);

		List<PrimaryKey> primaryKeys = new ArrayList<PrimaryKey>();
		List<String> pkAdded = new ArrayList<String>();

		
		/*
		 * ResultSetMetaData metadata = res.getMetaData(); int columnCount =
		 * metadata.getColumnCount(); for (int i = 1; i <= columnCount; i++) {
		 * System.out.println(metadata.getColumnName(i)); }
		 */
		 
		while (res.next()) {
			PrimaryKey primaryKey = new PrimaryKey();
			primaryKey.setPrimaryKey(res.getString("COLUMN_NAME"));
			primaryKey.setSequence(Integer.valueOf(res.getString("KEY_SEQ")));
			if (pkAdded.contains(primaryKey.getPrimaryKey())) {
				System.out.println("Primary Key exist already");
			} else {
				primaryKeys.add(primaryKey);
				pkAdded.add(primaryKey.getPrimaryKey());
			}

		}
		return primaryKeys;
	}

	private List<Column> getColumns(DatabaseMetaData dataBaseMD, String tableName) throws SQLException {
		ResultSet res = dataBaseMD.getColumns(null, null, tableName, null);

		List<Column> columns = new ArrayList<Column>();
		List<String> colsAdded = new ArrayList<String>();
		while (res.next()) {

			Column column = new Column();
			column.setColumnName(res.getString("COLUMN_NAME"));

			column.setDefaultValue(res.getString("COLUMN_DEF"));

			column.setColumnSize(res.getInt("COLUMN_SIZE"));

			if (!res.getString("IS_NULLABLE").equals("NO")) {
				column.setNullable(true);
			}
			column.setDataType(res.getString("TYPE_NAME"));

			if (!res.getString("IS_AUTOINCREMENT").equals("NO")) {

				column.setAutoIncrement(true);
			}
			if (colsAdded.contains(column.getColumnName())) {
				System.out.println("Column exist already");
			} else {
				columns.add(column);
				colsAdded.add(column.getColumnName());
			}
		}

		return columns;
	}

}
