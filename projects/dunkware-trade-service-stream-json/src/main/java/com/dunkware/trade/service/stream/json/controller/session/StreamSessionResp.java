package com.dunkware.trade.service.stream.json.controller.session;

public class StreamSessionResp {

	private String summary; 
	private StreamSessionStatus session;
	
	public StreamSessionResp() { 
		
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public StreamSessionStatus getSession() {
		return session;
	}

	public void setSession(StreamSessionStatus session) {
		this.session = session;
	}
	
	
	
	
	
	
}
