package TableScriptGenerator.scriptgen;

import java.util.List;

import TableScriptGenerator.model.TableScript;

public interface ScriptGen {
	List<TableScript> createTableScripts();

	List<String> getViewsAndTables();
}
