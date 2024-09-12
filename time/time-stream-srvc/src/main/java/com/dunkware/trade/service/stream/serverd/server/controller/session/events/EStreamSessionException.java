package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionException extends EStreamSessionEvent {

	private String exception; 
	
	public EStreamSessionException(StreamSession session, String exception) {
		super(session);
		this.exception = exception;
		
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	

	
}
