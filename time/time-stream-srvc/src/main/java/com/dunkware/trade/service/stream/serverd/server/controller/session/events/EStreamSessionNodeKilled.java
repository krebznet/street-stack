package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;

public class EStreamSessionNodeKilled extends EStreamSessionNodeEvent {

	public EStreamSessionNodeKilled(StreamSessionNode node) {
		super(node);
	
	}

}
