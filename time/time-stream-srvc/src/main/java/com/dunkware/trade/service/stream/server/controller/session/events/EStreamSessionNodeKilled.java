package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;

public class EStreamSessionNodeKilled extends EStreamSessionNodeEvent {

	public EStreamSessionNodeKilled(StreamSessionNode node) {
		super(node);
	
	}

}
