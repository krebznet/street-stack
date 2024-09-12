package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;
import com.dunkware.utils.core.events.DunkEvent;

public class EStreamSessionNodeEvent extends DunkEvent {

	private StreamSessionNode node;
	
	public EStreamSessionNodeEvent(StreamSessionNode node) { 
		this.node = node;
	}
	
	public StreamSessionNode getNode() { 
		return node;
	}
}
