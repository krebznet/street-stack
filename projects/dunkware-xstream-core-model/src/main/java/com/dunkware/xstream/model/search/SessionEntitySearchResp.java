package com.dunkware.xstream.model.search;

public class SessionEntitySearchResp {
	
	public static final int SEARCH_EXECUTED = 1;
	public static final int SEARCH_UNRESOLVABLE = 2; 
	public static final int SEARCH_NO_SESSION = 3; 
	public static final int SEARCH_EXCEPTION = 4; 
	
	private String exception = null; 
	
	private SessionEntityList results = new SessionEntityList();
	
	private int response;

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

	public SessionEntityList getResults() {
		return results;
	}

	public void setResults(SessionEntityList results) {
		this.results = results;
	} 
	
	
	
	

}
