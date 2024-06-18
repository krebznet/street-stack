package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStopping extends EStreamSessionEvent  {

	public EStreamSessionStopping(StreamSession session) {
		super(session);
	}

	
}
