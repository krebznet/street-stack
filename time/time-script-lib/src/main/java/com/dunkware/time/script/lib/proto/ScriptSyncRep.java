package com.dunkware.time.script.lib.proto;

import java.util.List;


public class ScriptSyncRep {

	private boolean error = false; 
	private String errorMessage; 
	private double nextVersion; 
	private List<ScriptDelta> deltas;
	
	
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
	public double getNextVersion() {
		return nextVersion;
	}
	public void setNextVersion(double nextVersion) {
		this.nextVersion = nextVersion;
	}
	public List<ScriptDelta> getDeltas() {
		return deltas;
	}
	public void setDeltas(List<ScriptDelta> deltas) {
		this.deltas = deltas;
	}	
	
	
	
	
}
