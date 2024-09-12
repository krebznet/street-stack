package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionCancelled extends EStreamSessionEvent {

	public EStreamSessionCancelled(StreamSession session) {
		super(session);

	}

}
