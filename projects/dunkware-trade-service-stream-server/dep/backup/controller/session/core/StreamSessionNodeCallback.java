package com.dunkware.trade.service.stream.server.controller.session.core;

import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;

public interface StreamSessionNodeCallback {
	
	public void nodeStarted(StreamSessionNode node);
	
	public void nodeStartException(StreamSessionNode node);
	
	public void nodeStopped(StreamSessionNode node);

}
