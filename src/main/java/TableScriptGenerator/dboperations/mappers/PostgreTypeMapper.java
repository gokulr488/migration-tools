package TableScriptGenerator.dboperations.mappers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PostgreTypeMapper implements DataTypeMapper {

	Map<String, String> postgreToMySql = new HashMap<String, String>();
	Map<String, String> postgreToOracle = new HashMap<String, String>();

	public PostgreTypeMapper() throws FileNotFoundException {
		populateMaps();
	}

	@Override
	public String getMySqlTypeOf(String dataType) {

		return postgreToMySql.get(dataType);
	}

	@Override
	public String getOrcaleTypeOf(String dataType) {

		return postgreToOracle.get(dataType);
	}

	private void populateMaps() throws FileNotFoundException {
		populateMySql("resource\\psqlToMySql.csv");
		// populateOrcale("");

	}

	private void populateMySql(String fileName) throws FileNotFoundException {
		List<String> mapping = readFromFile(fileName);
		for (String line : mapping) {
			String[] data = line.split(",");
			postgreToMySql.put(data[0], data[1]);
		}

	}

	private void populateOrcale(String fileName) throws FileNotFoundException {
		List<String> mapping = readFromFile(fileName);
		for (String line : mapping) {
			String[] data = line.split(",");
			postgreToOracle.put(data[0], data[1]);
		}

	}

	private List<String> readFromFile(String fileName) throws FileNotFoundException {
		List<String> mapping = new ArrayList<String>();
		Scanner reader = new Scanner(new File(fileName));
		while (reader.hasNextLine()) {
			mapping.add(reader.nextLine());
		}
		reader.close();
		return mapping;
	}

}
