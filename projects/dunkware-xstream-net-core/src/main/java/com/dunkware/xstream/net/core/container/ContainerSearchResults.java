package com.dunkware.xstream.net.core.container;

import java.util.ArrayList;
import java.util.List;

public class ContainerSearchResults<T> {
	
	private List<T> results = new ArrayList<T>();
	private double searchTime; 
	private String errorMessage; 
	private boolean error = false; 
	
	public void setError(String error) { 
		this.error = true;
		this.errorMessage = error;
	}
	
	public boolean hasError() { 
		return error;
	}
	
	public String getErrorMessage() { 
		return errorMessage;
	}
	
	public void setData(List<T> results, double searchTime) {
		this.results = results; 
		this.searchTime = searchTime;
		ContainerSearchResults<ContainerEntitySignal> j = new ContainerSearchResults<>();
	}
	
	public List<T> getResults() { 
		return results;
	}
	
	public double getSearchTime() { 
		return searchTime;
	}
}
