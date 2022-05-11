package com.dunkware.trade.tick.service.protocol.feed;

import com.dunkware.trade.tick.model.consumer.TickConsumerSession;

public class TickFeedStartResp {

	private String code; 
	private String error; 
	
	private TickConsumerSession session;
	
	public TickFeedStartResp() { 
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public TickConsumerSession getSession() {
		return session;
	}

	public void setSession(TickConsumerSession session) {
		this.session = session;
	}
	
	
	
	
	
}
