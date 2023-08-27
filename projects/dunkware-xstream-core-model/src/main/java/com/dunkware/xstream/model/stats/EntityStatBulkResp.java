package com.dunkware.xstream.model.stats;

import java.util.Map;

public class EntityStatBulkResp {

	private Map<String,String> exceptionStats;
	private Map<String,Integer> unresolvedStats;
	private Map<String,Number> resolvedStats;
	
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
	
	
	
	
}
