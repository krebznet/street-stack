package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;

public class EStreamSessionNodeStarted extends EStreamSessionNodeEvent {

	public EStreamSessionNodeStarted(StreamSessionNode node) {
		super(node);
	}

	
}
