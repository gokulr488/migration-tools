package migrationtool.lookup;

public class LookUp {
	String TableName;
	String columnName;
	int lookUpIndex;
	String DataType;

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return DataType;
	}

	public void setDataType(String dataType) {
		DataType = dataType;
	}

	public int getLookUpIndex() {
		return lookUpIndex;
	}

	public void setLookUpIndex(int lookUpIndex) {
		this.lookUpIndex = lookUpIndex;
	}
}
