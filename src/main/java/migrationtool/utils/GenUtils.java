package migrationtool.utils;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class GenUtils {

	public static ST getTemplate(String key) {

		try {
			STGroup stGroup = new STGroupFile("resource//template.stg");
			ST template = stGroup.getInstanceOf(key);
			return template;
		} catch (Exception e) {
			STGroup stGroup = new STGroupFile("resource\\template.stg");
			ST template = stGroup.getInstanceOf(key);
			return template;
		}

	}

}
