package migrationtool.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.stringtemplate.v4.ST;

import TableScriptGenerator.ScriptGenConfig;
import TableScriptGenerator.model.Table;
import TableScriptGenerator.model.TableScript;

public class FileWriteUtils {
	BufferedWriter buffer;

	public void createTables(List<TableScript> scripts) {

		try {

			for (TableScript script : scripts) {
				String output = "/***************************************Table : <tableName> ***********************************************/ \r\n"
						+ "\r\n" + "<script> \r\n"
						+ "/*_________________________________________________END____________________________________________________________*/ \r\n"
						+ "\r\n";
				ST template = new ST(output);
				template.add("tableName", script.getTableName());
				template.add("script", script.getScript());
				buffer.write(template.render());
			}

			System.out.println("Table Creation Scripts saved in " + ScriptGenConfig.filepath);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dropTables(List<Table> tables) {
		try {
			buffer.write(
					"/***************************************Dropping Tables***********************************************/ \r\n");

			String output = "\r\n" + "DROP TABLE IF EXISTS <tableName> ; \r\n";

			for (int i = tables.size() - 1; i >= 0; i--) {
				String table = tables.get(i).getTabelName();

				ST template = new ST(output);
				template.add("tableName", table);

				buffer.write(template.render());
			}
			buffer.write(
					"/*_________________________________________________Dropped____________________________________________________________*/ \r\n \r\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(List<String> data) {
		for (String line : data) {
			try {
				buffer.write(line);
				buffer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data.clear();
	}

	public void write(String data) {

		try {
			buffer.write(data);
			buffer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openFile(String filePath) {
		try {
			this.buffer = new BufferedWriter(new FileWriter("output//" + filePath));
		} catch (IOException e) {
			try {
				this.buffer = new BufferedWriter(new FileWriter("output\\" + filePath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void close() {
		try {
			this.buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
