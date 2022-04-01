package com.dunkware.trade.service.stream.server.session.core;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionSignal;
import com.dunkware.xstream.core.model.signal.XStreamSignal;

public class StreamSessionSignalImpl implements StreamSessionSignal  {

	private XStreamSignal source;
	private StreamSession session; 
	
	public static StreamSessionSignalImpl newSignal(XStreamSignal signal, StreamSession session) {
		return new StreamSessionSignalImpl(signal, session);
	}
	
	public  StreamSessionSignalImpl(XStreamSignal source, StreamSession session) {
		this.source = source; 
		this.session = session;
	}
	
	@Override
	public XStreamSignal getSource() {
		return source; 
	}

	@Override
	public StreamSession getSession() {
		return session;
	}
	
	

	
	
	

	
}
