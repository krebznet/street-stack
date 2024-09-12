package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;

public class EStreamSessionNodeStopException extends EStreamSessionNodeEvent {

	private String exception;
	
	public EStreamSessionNodeStopException(StreamSessionNode node, String exception) {
		super(node);
		this.exception = exception;
	}
	
	public String getException() { 
		return exception;
	}

	
}