package com.dunkware.time.script.lib.proto;

import java.util.ArrayList;
import java.util.List;

public class ScriptReleaseRep {
	
	private boolean error = false; 
	private String errorMessage = null; 
	private double versions; 
	private List<ScriptDelta> deltas = new ArrayList<ScriptDelta>();
	
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public double getVersions() {
		return versions;
	}
	public void setVersions(double versions) {
		this.versions = versions;
	}
	public List<ScriptDelta> getDeltas() {
		return deltas;
	}
	public void setDeltas(List<ScriptDelta> deltas) {
		this.deltas = deltas;
	}
	
	

}
