package com.dunkware.xstream.model.stats;

import java.util.Map;

public class EntityStatBulkResp {

	private Map<String,Exception> exception;
	private Map<String,Integer> unresolved;
	private Map<String,Number> resolved;
	
	public Map<String, Exception> getException() {
		return exception;
	}
	public void setException(Map<String, Exception> exception) {
		this.exception = exception;
	}
	public Map<String, Integer> getUnresolved() {
		return unresolved;
	}
	public void setUnresolved(Map<String, Integer> unresolved) {
		this.unresolved = unresolved;
	}
	public Map<String, Number> getResolved() {
		return resolved;
	}
	public void setResolved(Map<String, Number> resolved) {
		this.resolved = resolved;
	}
	
	
}
