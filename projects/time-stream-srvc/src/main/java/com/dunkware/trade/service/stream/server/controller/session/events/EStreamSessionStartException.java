package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStartException extends EStreamSessionEvent {

	private String error; 
	
	public EStreamSessionStartException(StreamSession session, String error) {
		super(session);
	
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}
