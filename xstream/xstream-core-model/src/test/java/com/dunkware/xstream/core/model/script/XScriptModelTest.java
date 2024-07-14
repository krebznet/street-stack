package com.dunkware.xstream.core.model.script;

import org.junit.jupiter.api.Test;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptUpdate;
import com.dunkware.xstream.model.script.utils.ScriptComparator;
import com.dunkware.xstream.model.script.utils.XScriptModelCopier;
import com.dunkware.xstream.model.script.utils.XScriptModelFormatter;
import com.dunkware.xstream.model.script.utils.XScriptModelGenerator;
import com.dunkware.xstream.model.script.utils.XScriptUpdateBuilder;
import com.dunkware.xstream.model.script.utils.XScriptUpdateFormatter;

import junit.framework.TestCase;

public class XScriptModelTest  {

	
	public void updaterTest() {
		XScriptModel model = new XScriptModel();
		XScriptUpdateBuilder updater = new XScriptUpdateBuilder(model);
		updater.insertVariable(3, "test", "poop", "see", "Currency");
		updater.insertSignal(323, "sdfd", "dd", "df");
		XScriptModel updated = updater.getUpdatedScriptModel();
	//	assertEquals(updated.getVariables().size(), 1);
	///	assertEquals(updated.getVariables().get(0).getName(), "test");

		model = XScriptModelGenerator.generateModel("Test", "1.3.3", 19, 4, 3, 1, 1, 1);
		
		
		XScriptModel clonedModel = XScriptModelCopier.copy(model);
		updater = new XScriptUpdateBuilder(clonedModel);
		updater.insertVariable(49, "Insert1", "Inser1", "Inserts", "CURRENCY");
		updater.deleteVariable(1);
		
		
		XScriptModel updatedModel = updater.getUpdatedScriptModel();
		System.out.println(XScriptModelFormatter.format(updatedModel));
		System.out.println(updatedModel.getVariables().size());
		System.out.println("---");
		System.out.println(model.getVariables().size());
		System.out.println(XScriptModelFormatter.format(model));
		//assertEquals(updatedModel.getVariables().size(), model.getVariables().size());
		ScriptComparator comp = new ScriptComparator();
		XScriptUpdate update = comp.compareScripts(model, updatedModel);
		XScriptUpdateFormatter f = new XScriptUpdateFormatter();
		
		System.out.println(XScriptUpdateFormatter.format(update));
		
	}
	
	
	

}