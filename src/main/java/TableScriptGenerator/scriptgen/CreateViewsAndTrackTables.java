package TableScriptGenerator.scriptgen;

import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;

import TableScriptGenerator.ScriptGenConfig;
import TableScriptGenerator.dboperations.mappers.DataTypeMapper;
import TableScriptGenerator.model.Column;
import TableScriptGenerator.model.Metadata;
import TableScriptGenerator.model.PrimaryKey;
import TableScriptGenerator.model.Table;

public class CreateViewsAndTrackTables {
	DataTypeMapper mapper;

	public CreateViewsAndTrackTables(DataTypeMapper mapper) {
		this.mapper = mapper;
	}

	public List<String> createTableScripts(Metadata metadata) {

		List<String> viewsAndTableScripts = new ArrayList<String>();

		for (Table table : metadata.getTables()) {
			Column col = new Column();
			if (table.getPrimaryKeys() != null) {
				PrimaryKey pKey = table.getPrimaryKeys().get(0);
				for (Column cols : table.getColumns()) {
					if (pKey.getPrimaryKey().equals(cols.getColumnName())) {
						col = cols;
					}
				}
			} else {
				System.out.println("no primaryKey found for table : " + table.getTabelName());
			}
			String key = "viewsAndTablesTrg";
			if (ScriptGenConfig.createFor.equals("source")) {
				key = "viewsAndTablesSrc";
			}
			ST template = GenUtils.getTemplate("resource\\template.stg", key);
			template.add("tableName", "track_" + table.getTabelName());
			template.add("viewName", "view_" + table.getTabelName());
			template.add("columnName", "track_" + col.getColumnName());
			if (ScriptGenConfig.createFor.equals("source")) {
				template.add("dataType", col.getDataType());
			} else {
				template.add("dataType", mapper.getMySqlTypeOf(col.getDataType()));
			}
			template.add("linkingTableName", table.getTabelName());
			template.add("linkingTableColName", col.getColumnName());
			viewsAndTableScripts.add(template.render());
		}
		return viewsAndTableScripts;

	}
}
