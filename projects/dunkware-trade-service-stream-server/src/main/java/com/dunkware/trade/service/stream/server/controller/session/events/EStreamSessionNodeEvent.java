package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;

public class EStreamSessionNodeEvent extends DEvent {

	private StreamSessionNode node;
	
	public EStreamSessionNodeEvent(StreamSessionNode node) { 
		this.node = node;
	}
	
	public StreamSessionNode getNode() { 
		return node;
	}
}
