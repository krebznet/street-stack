package com.dunkware.trade.service.stream.server.session.events;

import com.dunkware.trade.service.stream.server.session.StreamSession;

public class EStreamSessionStarted extends EStreamSessionEvent {

	public EStreamSessionStarted(StreamSession session) {
		super(session);
	}

	
}
