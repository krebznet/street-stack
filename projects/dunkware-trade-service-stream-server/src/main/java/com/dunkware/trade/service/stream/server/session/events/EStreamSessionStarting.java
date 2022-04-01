package com.dunkware.trade.service.stream.server.session.events;

import com.dunkware.trade.service.stream.server.session.StreamSession;

public class EStreamSessionStarting extends EStreamSessionEvent {

	public EStreamSessionStarting(StreamSession session) {
		super(session);
	}

}
