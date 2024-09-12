package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionStopException extends EStreamSessionEvent {

	private String error;
	public EStreamSessionStopException(StreamSession session, String error) {
		super(session);
		this.error = error;
	}
	
	
	public String getError() {
		return error;
	}

	
}
