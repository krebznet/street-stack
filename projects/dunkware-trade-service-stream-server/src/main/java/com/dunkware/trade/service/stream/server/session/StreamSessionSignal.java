package com.dunkware.trade.service.stream.server.session;

import com.dunkware.xstream.core.model.signal.XStreamSignal;

public interface StreamSessionSignal {

	public XStreamSignal getSource();
	
	StreamSession getSession();
	
}
