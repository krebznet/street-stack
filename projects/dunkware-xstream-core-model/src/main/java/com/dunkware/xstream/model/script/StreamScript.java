package com.dunkware.xstream.model.script;

import java.util.ArrayList;
import java.util.List;

/**
 * This defines the stream script elements that are running in a session it includes
 * a script version list of variables and list of signal types. 
 * @author duncankrebs
 *
 */
public class StreamScript {
	
	private double version; 
	private List<StreamScriptVar> variables = new ArrayList<StreamScriptVar>();
	private List<StreamSignalType> signalTypes = new ArrayList<StreamSignalType>();
	
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public List<StreamScriptVar> getVariables() {
		return variables;
	}
	public void setVariables(List<StreamScriptVar> variables) {
		this.variables = variables;
	}
	public List<StreamSignalType> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<StreamSignalType> signalTypes) {
		this.signalTypes = signalTypes;
	}
	
	
	

}
