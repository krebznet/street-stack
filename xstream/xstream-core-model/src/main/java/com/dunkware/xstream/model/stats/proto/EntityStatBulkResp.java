package com.dunkware.xstream.model.stats.proto;

import java.util.Map;

public class EntityStatBulkResp {

	private Map<String,String> exceptionStats;
	private Map<String,Integer> unresolvedStats;
	private Map<String,Number> resolvedStats;
	private boolean success = false; 
	private String exception = null;
	
	public Map<String, String> getExceptionStats() {
		return exceptionStats;
	}
	public void setExceptionStats(Map<String, String> exceptionStats) {
		this.exceptionStats = exceptionStats;
	}
	public Map<String, Integer> getUnresolvedStats() {
		return unresolvedStats;
	}
	public void setUnresolvedStats(Map<String, Integer> unresolvedStats) {
		this.unresolvedStats = unresolvedStats;
	}
	public Map<String, Number> getResolvedStats() {
		return resolvedStats;
	}
	public void setResolvedStats(Map<String, Number> resolvedStats) {
		this.resolvedStats = resolvedStats;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	
	
	
	
}
