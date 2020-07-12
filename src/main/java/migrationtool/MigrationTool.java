package migrationtool;

import java.io.IOException;
import java.sql.SQLException;

import migrationtool.csv.CsvWriteManager;
import migrationtool.customquery.CustomQuery;
import migrationtool.customstubs.Custom1;
import migrationtool.dbWrite.DbWriteManager;
import migrationtool.utils.Configs;

public class MigrationTool {

	public static void main(String[] args) throws IOException, SQLException {
		Configs.getAllValues();

		switch (Configs.outputMode) {
		case "CSV":
			CsvWriteManager csvWriter = new CsvWriteManager();
			csvWriter.start();
			break;

		case "DB":
			DbWriteManager dbWriter = new DbWriteManager();
			dbWriter.start();

			break;

		case "CUSTOMQUERY":
			CustomQuery customQuery = new CustomQuery();
			customQuery.start();
			break;

		case "CUSTOM":
			Custom1 custom = new Custom1();
			custom.start();
			break;

		default:
			System.out.println("Unrecognised outputMode. Please use 'CSV' or'DB' ");
			break;
		}

	}

}
