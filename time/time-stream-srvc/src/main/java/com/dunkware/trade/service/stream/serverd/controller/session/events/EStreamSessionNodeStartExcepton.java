package com.dunkware.trade.service.stream.serverd.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;

public class EStreamSessionNodeStartExcepton extends EStreamSessionNodeEvent {

	private String exception;
	
	public EStreamSessionNodeStartExcepton(StreamSessionNode node, String exception) {
		super(node);
		this.exception = exception;
	}
	
	public String getException() { 
		return exception;
	}
	


	
}