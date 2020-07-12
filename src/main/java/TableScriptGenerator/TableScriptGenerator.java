package TableScriptGenerator;

import java.io.IOException;
import java.sql.SQLException;

import TableScriptGenerator.dboperations.ConnectionManager;
import TableScriptGenerator.model.Metadata;
import TableScriptGenerator.scriptgen.MySqlScriptGen;
import TableScriptGenerator.scriptgen.ScriptGen;
import TableScriptGenerator.scripttofile.FileWriteAdapter;

public class TableScriptGenerator {

	public static void main(String[] args) throws IOException, SQLException {
		ScriptGenConfig.readValues();

		ConnectionManager connectionManager = new ConnectionManager();

		connectionManager.getSourceConnection();
		Metadata mySqlMD = connectionManager.getMySqlMetaData();

		ScriptGen scriptGen = new MySqlScriptGen(mySqlMD);

		if (ScriptGenConfig.writeToFile) {
			FileWriteAdapter writer = new FileWriteAdapter();

			writer.writeTo(ScriptGenConfig.filepath + "_CreateDataTables.sql");
			writer.dropTables(mySqlMD.getTables());
			writer.createTables(scriptGen.createTableScripts());
			writer.close();

			writer.writeTo(ScriptGenConfig.filepath + "_ViewsAndTables.sql");
			writer.write(scriptGen.getViewsAndTables());
			writer.close();

		}

	}

}
