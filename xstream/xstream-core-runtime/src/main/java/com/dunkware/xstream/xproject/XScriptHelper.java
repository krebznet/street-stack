package com.dunkware.xstream.xproject;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.xScript.ScriptElement;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xScript.VarType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XScript;
import com.dunkware.xstream.xScript.XTradeBotType;

public class XScriptHelper {
	
	
	public static List<VarType> getVarTypes(XScript script){
		List<VarType> types = new ArrayList<VarType>();
		for (ScriptElement element : script.getElements()) {
			if (element instanceof VarType) {
				VarType varType = (VarType) element;
				types.add(varType);
			}
		}
		return types;
	}

	public static List<SignalType> getSignals(XScript script) { 
		List<SignalType> types = new ArrayList<SignalType>();
		for (ScriptElement element : script.getElements()) {
			if (element instanceof SignalType) {
				SignalType clazz = (SignalType) element;
				types.add(clazz);
			}
		}
		return types;
	}
	
	public static List<XClassType> getClasses(XScript script) { 
		List<XClassType> types = new ArrayList<XClassType>();
		for (ScriptElement element : script.getElements()) {
			if (element instanceof XClassType) {
				XClassType clazz = (XClassType) element;
				types.add(clazz);
			}
		}
		
		return types;
	}
	
	public static List<XTradeBotType> getStreamBots(XScript script) { 
		List<XTradeBotType> types = new ArrayList<XTradeBotType>();
		for (ScriptElement element : script.getElements()) {
			if (element instanceof XTradeBotType) {
				XTradeBotType clazz = (XTradeBotType) element;
				types.add(clazz);
			}
		}
		
		return types;
	}
}
