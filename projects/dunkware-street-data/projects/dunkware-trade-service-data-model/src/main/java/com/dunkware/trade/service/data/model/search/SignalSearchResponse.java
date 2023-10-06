package com.dunkware.trade.service.data.model.search;

import java.util.List;

import com.dunkware.xstream.model.signal.StreamEntitySignal;

//SD21-GIFT-7 - model for sending a search response back, we return a list of results the time it took to query and exception if exists
public class SignalSearchResponse {

	private double queryTime; 
	private String exception; 
	private List<StreamEntitySignal> results;
	
	public double getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(double queryTime) {
		this.queryTime = queryTime;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public List<StreamEntitySignal> getResults() {
		return results;
	}
	public void setResults(List<StreamEntitySignal> results) {
		this.results = results;
	}
	
	
}
