package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStopped extends EStreamSessionEvent {

	public EStreamSessionStopped(StreamSession session) {
		super(session);
	}

	
}
