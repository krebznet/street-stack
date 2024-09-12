package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionStopped extends EStreamSessionEvent {

	public EStreamSessionStopped(StreamSession session) {
		super(session);
	}

	
}
