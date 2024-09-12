package com.dunkware.trade.service.stream.json.controller.session;

public class StreamSessionResp {

	private String summary; 
	private StreamSessionStats session;
	
	public StreamSessionResp() { 
		
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public StreamSessionStats getSession() {
		return session;
	}

	public void setSession(StreamSessionStats session) {
		this.session = session;
	}
	
	
	
	
	
	
}
