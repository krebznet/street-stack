package com.dunkware.xstream.net.proto;

public class StreamContainerEntitySearchResp {
	
	private String errorMessage = null; 
	private boolean error = true; 
	private Object results;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	} 
	
	
	

}
