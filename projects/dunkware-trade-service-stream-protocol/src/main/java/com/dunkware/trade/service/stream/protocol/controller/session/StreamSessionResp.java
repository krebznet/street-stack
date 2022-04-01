package com.dunkware.trade.service.stream.protocol.controller.session;

import com.dunkware.trade.service.stream.protocol.controller.session.spec.StreamSessionStatsSpec;

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
