package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStarted extends EStreamSessionEvent {

	public EStreamSessionStarted(StreamSession session) {
		super(session);
	}

	
}
