package com.dunkware.trade.service.stream.metrics;

import java.util.Map;

public class VarSessionMetrics {
	
	private int id; 
	private String ident; 
	private Map<String,Object> metrics;
	// high
	// high time 
	// low
	// low time 
	// extendable metric service duncan 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public Map<String, Object> getMetrics() {
		return metrics;
	}
	public void setMetrics(Map<String, Object> metrics) {
		this.metrics = metrics;
	}
	
	

}
