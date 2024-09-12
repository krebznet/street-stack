package com.dunkware.trade.service.stream.serverd.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionNode;

public class EStreamSessionNodeStopped extends EStreamSessionNodeEvent {

	public EStreamSessionNodeStopped(StreamSessionNode node) {
		super(node);

	}

	
}
