package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionCancelled extends EStreamSessionEvent {

	public EStreamSessionCancelled(StreamSession session) {
		super(session);

	}

}
