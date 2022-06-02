package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStarting extends EStreamSessionEvent {

	public EStreamSessionStarting(StreamSession session) {
		super(session);
	}

}
