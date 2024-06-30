package com.dunkware.xstream.model.delta;

import java.util.ArrayList;
import java.util.List;

public class XScriptDelta {

	private double targetVersion; 
	private double compareVersion; 
	
	private List<String> varInserts = new ArrayList<String>();
	private List<String> varDeletes = new ArrayList<String>();
	
	private List<String> signalInserts = new ArrayList<String>();
	private List<String> signalDeletes = new ArrayList<String>();
	
	public double getTargetVersion() {
		return targetVersion;
	}
	public void setTargetVersion(double targetVersion) {
		this.targetVersion = targetVersion;
	}
	public double getCompareVersion() {
		return compareVersion;
	}
	public void setCompareVersion(double compareVersion) {
		this.compareVersion = compareVersion;
	}
	public List<String> getVarInserts() {
		return varInserts;
	}
	public void setVarInserts(List<String> varInserts) {
		this.varInserts = varInserts;
	}
	public List<String> getVarDeletes() {
		return varDeletes;
	}
	public void setVarDeletes(List<String> varDeletes) {
		this.varDeletes = varDeletes;
	}
	public List<String> getSignalInserts() {
		return signalInserts;
	}
	public void setSignalInserts(List<String> signalInserts) {
		this.signalInserts = signalInserts;
	}
	public List<String> getSignalDeletes() {
		return signalDeletes;
	}
	public void setSignalDeletes(List<String> signalDeletes) {
		this.signalDeletes = signalDeletes;
	}
	
	
	// 

}


// ScriptService 
// ScriptRegistry 
// ScriptModel
// ScriptVarModel
// ScrptBotModel 