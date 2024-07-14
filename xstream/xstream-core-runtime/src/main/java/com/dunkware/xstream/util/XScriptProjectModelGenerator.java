package com.dunkware.xstream.util;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.utils.XScriptModelBuilder;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xScript.XTradeBotType;
import com.dunkware.xstream.xproject.XScriptProject;

public class XScriptProjectModelGenerator {
	
	public static XScriptModel generateModel(String name, String vesion, String type, XScriptProject project) {
		XScriptModelBuilder b = new XScriptModelBuilder();
		for (VarType var : project.getStreamVars()) {
			b.insertVariable(var.getCode(), var.getName(), var.getLabel(), var.getGroup().getLabel(),var.getFormat().name());
		}
		for(SignalType signal : project.getStreamSignals()) { 
			b.insertSignal(signal.getId(), signal.getName(), signal.getLabel(), signal.getGroup().getLabel());
		}
		for (XTradeBotType bot : project.getStreamBots()) {
			b.insertBot(bot.getId(), bot.getName(), bot.getGroup().getLabel());
			
		}
		b.setVersion("UNDEFINED");
		b.setName(name);
		b.setVersion(vesion);
		b.set
	}

}
