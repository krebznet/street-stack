package com.dunkware.trade.service.stream.server.controller.session;

import com.dunkware.xstream.model.signal.XStreamSignal;

public class StreamSessionSignal {
	
	private XStreamSignal signal; 
	private StreamSession session; 
	
	public StreamSessionSignal(StreamSession session, XStreamSignal signal) { 
		this.signal = signal;
		this.session = session;
	}

	public XStreamSignal getSignal() {
		return signal;
	}

	public void setSignal(XStreamSignal signal) {
		this.signal = signal;
	}

	public StreamSession getSession() {
		return session;
	}

	public void setSession(StreamSession session) {
		this.session = session;
	}
	
	

}
