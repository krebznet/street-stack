package com.dunkware.trade.service.stream.server.session.events;

import com.dunkware.trade.service.stream.server.controller.event.ESteamEvent;
import com.dunkware.trade.service.stream.server.session.StreamSession;

public class EStreamSessionEvent extends ESteamEvent {

	private StreamSession session;
	
	public EStreamSessionEvent(StreamSession session) { 
		super(session.getStream());
	}

	public StreamSession getSession() {
		return session;
	}

	public void setSession(StreamSession session) {
		this.session = session;
	}
	
	
}
