package com.dunkware.trade.service.stream.server.controller.session.events;

import com.dunkware.trade.service.stream.server.controller.event.EStreamEvent;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;

public class EStreamSessionEvent extends EStreamEvent {

	private StreamSession session;
	
	public EStreamSessionEvent(StreamSession session) { 
		super(session.getStream());
		this.session = session;
	}

	public StreamSession getSession() {
		return session;
	}

	public void setSession(StreamSession session) {
		this.session = session;
	}
	
	
}
