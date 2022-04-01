package com.dunkware.trade.service.stream.server.session.events;

import com.dunkware.trade.service.stream.server.session.StreamSession;

public class EStreamSessionStopping extends EStreamSessionEvent  {

	public EStreamSessionStopping(StreamSession session) {
		super(session);
	}

	
}
