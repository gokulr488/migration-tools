package TableScriptGenerator.model;

public class ForeignKey {

	String foreignTableName;
	String foreignColumnName;
	String refferingColumnName;

	public String getForeignTableName() {
		return foreignTableName;
	}

	public void setForeignTableName(String foreignTableName) {
		this.foreignTableName = foreignTableName;
	}

	public String getForeignColumnName() {
		return foreignColumnName;
	}

	public void setForeignColumnName(String foreignColumnName) {
		this.foreignColumnName = foreignColumnName;
	}

	public String getRefferingColumnName() {
		return refferingColumnName;
	}

	public void setRefferingColumnName(String refferingColumnName) {
		this.refferingColumnName = refferingColumnName;
	}

}
