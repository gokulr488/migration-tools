package migrationtool.datacollector;

import java.util.ArrayList;
import java.util.List;

public class MetaData {

	public static long rowCount;
	public static int columnCount;
	public static List<String> columns = new ArrayList<String>();
	public static boolean finishedPull = false;
	public static String primaryKey;

}
