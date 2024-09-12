package com.dunkware.trade.service.stream.serverd.server.web.bean;

import java.util.ArrayList;
import java.util.List;

public class SignalSearchResponse {
	
	private double searchTime; 
	private ArrayList<SignalSearchRow> results = new ArrayList<SignalSearchRow>();
	
	public double getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(double searchTime) {
		this.searchTime = searchTime;
	}
	public ArrayList<SignalSearchRow> getResults() {
		return results;
	}
	public void setResults(ArrayList<SignalSearchRow> results) {
		this.results = results;
	}
	
	
	
	
	

}
