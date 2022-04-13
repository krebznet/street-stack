package com.dunkware.trade.service.stream.json.controller.session;

public class StreamSessionResp {

	private String summary; 
	private StreamSessionStatsSpec session;
	
	public StreamSessionResp() { 
		
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public StreamSessionStatsSpec getSession() {
		return session;
	}

	public void setSession(StreamSessionStatsSpec session) {
		this.session = session;
	}
	
	
	
	
	
	
}
