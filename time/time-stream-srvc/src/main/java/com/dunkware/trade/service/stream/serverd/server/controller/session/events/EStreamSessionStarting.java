package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionStarting extends EStreamSessionEvent {

	public EStreamSessionStarting(StreamSession session) {
		super(session);
	}

}
