package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionStartException extends EStreamSessionEvent {

	public EStreamSessionStartException(StreamSession session) {
		super(session);
	
	}

}
