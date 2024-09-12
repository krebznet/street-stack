package com.dunkware.trade.service.stream.serverd.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionStarted extends EStreamSessionEvent {

	public EStreamSessionStarted(StreamSession session) {
		super(session);
	}

	
}
