package com.dunkware.trade.service.stream.server.session.core;

import com.dunkware.trade.service.stream.server.session.StreamSessionNode;

public interface StreamSessionNodeCallback {
	
	public void nodeStarted(StreamSessionNode node);
	
	public void nodeStartException(StreamSessionNode node);
	
	public void nodeStopped(StreamSessionNode node);

}
