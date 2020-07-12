package migrationtool.dbWrite;

import java.util.ArrayList;
import java.util.List;

public class TargetMetaData {

	int noOfColumns;
	List<Column> colmns = new ArrayList<Column>();

	public int getNoOfColumns() {
		return noOfColumns;
	}

	public void setNoOfColumns(int noOfColumns) {
		this.noOfColumns = noOfColumns;
	}

	public List<Column> getColmns() {
		return colmns;
	}

	public void setColmns(List<Column> colmns) {
		this.colmns = colmns;
	}
}
