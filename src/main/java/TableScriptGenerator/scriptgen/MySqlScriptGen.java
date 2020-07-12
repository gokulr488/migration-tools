package TableScriptGenerator.scriptgen;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import TableScriptGenerator.ScriptGenConfig;
import TableScriptGenerator.dboperations.mappers.DataTypeMapper;
import TableScriptGenerator.dboperations.mappers.PostgreTypeMapper;
import TableScriptGenerator.model.Column;
import TableScriptGenerator.model.ForeignKey;
import TableScriptGenerator.model.Metadata;
import TableScriptGenerator.model.PrimaryKey;
import TableScriptGenerator.model.Table;
import TableScriptGenerator.model.TableScript;

public class MySqlScriptGen implements ScriptGen {

	Metadata mySqlMD;
	DataTypeMapper mapper;

	public MySqlScriptGen(Metadata mySqlMD) {
		this.mySqlMD = mySqlMD;

	}

	@Override
	public List<TableScript> createTableScripts() {
		try {
			mapper = new PostgreTypeMapper();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TableScript> createScripts = new ArrayList<TableScript>();
		for (Table table : mySqlMD.getTables()) {
			TableScript script = new TableScript();
			script.setTableName(table.getTabelName());
			script.setScript(getCreateTableScript(table));
			createScripts.add(script);
		}
		return createScripts;

	}

	@Override
	public List<String> getViewsAndTables() {
		CreateViewsAndTrackTables trackTables = new CreateViewsAndTrackTables(mapper);
		return trackTables.createTableScripts(mySqlMD);
	}

	private String getCreateTableScript(Table table) {

		String script = "";

		ST template = new ST("CREATE TABLE <tableName> (\r\n");
		template.add("tableName", table.getTabelName());
		script = script + template.render();

		script = genColumns(table, script);
		if (table.getPrimaryKeys() != null) {
			script = genPrimaryKey(table, script);
		}
		if (ScriptGenConfig.includeForeignKeys) {
			script = genForeignKeys(table, script);
		}

		script = script.substring(0, script.length() - 3);
		script = script + ");";
		System.out.println("Script created for " + table.getTabelName() + " table");
		return script;
	}

	private String genForeignKeys(Table table, String script) {

		for (ForeignKey fKey : table.getForeignKeys()) {
			ST template = new ST("FOREIGN KEY (<thisCol>) REFERENCES <fTable>(<fCol>)");
			template.add("thisCol", fKey.getRefferingColumnName());
			template.add("fTable", fKey.getForeignTableName());
			template.add("fCol", fKey.getForeignColumnName());
			script = script + template.render();
			script = script + ",\r\n";
		}
		return script;
	}

	private String genPrimaryKey(Table table, String script) {
		ST template = new ST("PRIMARY KEY (<pKey>),\r\n");
		int x = 0;
		String comma = ",";
		for (PrimaryKey pKey : table.getPrimaryKeys()) {
			if (x++ == table.getPrimaryKeys().size() - 1) {
				comma = "";
			}

			template.add("pKey", pKey.getPrimaryKey() + comma);

		}
		script = script + template.render();
		return script;
	}

	private String genColumns(Table table, String script) {

		for (Column col : table.getColumns()) {
			boolean required = true;
			for (String avoidedColumn : ScriptGenConfig.avoidColumns) {
				if (avoidedColumn.equals(col.getColumnName())) {
					required = false;
				}
			}

			if (required) {
				ST template = new ST("	<colName> <colType><colSize> <defaultValue>");
				template.add("colName", col.getColumnName());
				/*
				 * if(mapper.getMySqlTypeOf(col.getDataType())==null) {
				 * System.out.println(col.getDataType()+"   "+col.getColumnSize()); }
				 */
				if (mapper.getMySqlTypeOf(col.getDataType()).equals("VARCHAR") && col.getColumnSize() >= 65535) {

					template.add("colType", "LONGTEXT");

				} else {
					template.add("colType", mapper.getMySqlTypeOf(col.getDataType()));
				}

				if (mapper.getMySqlTypeOf(col.getDataType()).equals("VARCHAR") && col.getColumnSize() < 65535) {
					String colSize = "(" + col.getColumnSize() + ")";
					template.add("colSize", colSize);
				} else {
					template.add("colSize", "");
				}

				template.add("defaultValue", filterDefaultValue(col));
				script = script + template.render();
				if (!col.isNullable()) {
					script = script + " NOT NULL";
					// script = script + " DEFAULT NULL";
				}
				if (col.isAutoIncrement() && ScriptGenConfig.autoIncrement) {
					script = script + " AUTO_INCREMENT";
				}
				script = script + ",\r\n";
			}

		}
		return script;

	}

	private String filterDefaultValue(Column col) {
		String defaultValue = col.getDefaultValue();
		if (defaultValue == null) {
			return "";
		} else if (defaultValue.contains("nextval")) {
			return "";
		} else if (mapper.getMySqlTypeOf(col.getDataType()).equals("DATE") && col.getDefaultValue() != null) {
			return "DEFAULT (CURRENT_DATE)";
		} else if (mapper.getMySqlTypeOf(col.getDataType()).equals("DATETIME") && col.getDefaultValue() != null) {
			return "DEFAULT CURRENT_TIMESTAMP";
		} else if (defaultValue.contains("::")) {
			String[] val = defaultValue.split("::");
			return "DEFAULT " + val[0];
		}

		return "DEFAULT " + defaultValue;
	}

}
