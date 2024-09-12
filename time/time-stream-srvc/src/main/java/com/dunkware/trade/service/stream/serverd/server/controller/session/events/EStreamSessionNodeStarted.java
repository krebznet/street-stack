package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;

public class EStreamSessionNodeStarted extends EStreamSessionNodeEvent {

	public EStreamSessionNodeStarted(StreamSessionNode node) {
		super(node);
	}

	
}
