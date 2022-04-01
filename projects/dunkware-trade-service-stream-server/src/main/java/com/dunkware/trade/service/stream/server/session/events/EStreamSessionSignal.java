package com.dunkware.trade.service.stream.server.session.events;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionSignal;

public class EStreamSessionSignal extends EStreamSessionEvent {

	private StreamSessionSignal signal;
	
	public EStreamSessionSignal(StreamSession session, StreamSessionSignal  signal) {
		super(session);
		this.signal = signal;
	}
	
	public StreamSessionSignal getSignal() { 
		return signal;
	}

	
	
	
}
