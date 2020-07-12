package TableScriptGenerator.dboperations.mappers;

public interface DataTypeMapper {

	String getMySqlTypeOf(String dataType);
	String getOrcaleTypeOf(String dataType);
}
