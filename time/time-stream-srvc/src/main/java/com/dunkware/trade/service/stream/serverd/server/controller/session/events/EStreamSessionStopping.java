package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;

public class EStreamSessionStopping extends EStreamSessionEvent  {

	public EStreamSessionStopping(StreamSession session) {
		super(session);
	}

	
}
