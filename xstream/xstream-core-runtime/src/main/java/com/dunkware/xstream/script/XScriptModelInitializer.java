package com.dunkware.xstream.script;

import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.utils.XScriptModelBuilder;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xScript.XScriptBot;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

public class XScriptModelInitializer {
	
	
	public static XScriptDescriptor generate(String script, String name, String type, String version) throws Exception { 
		XScriptBundle bundle = XscriptBundleHelper.createBundleFromFileContents(script);
		XScriptDescriptor model = new XScriptDescriptor();
		model.setName(name);
		model.setType(type);
		model.setVersion(version);
		
		XScriptProject project = null;
		try {
			project = new XScriptProject(bundle);
		} catch (Exception e) {
			throw e;
		}
		XScriptModelBuilder b = XScriptModelBuilder.instance();
		b.setName(name);
		b.setType(type);
		b.setVersion(version);
		for (VarType var : project.getStreamVars()) {
			b.insertVariable(version, var.getCode(), var.getName(), var.getLabel(), var.getGroup().getLabel(), var.getFormat().name());
		}
		for (SignalType sig : project.getStreamSignals()) {
			b.insertSignal(sig.getId(), sig.getName(), sig.getLabel(), sig.getGroup().getLabel(), version);
		}
		for (XScriptBot bot : project.getStreamBots()) {
			b.insertBot(bot.getId(),version,bot.getName(),bot.getLabel(),bot.getGroup().getLabel(),bot.getDescription());
		}
		return b.build();
	}

}
