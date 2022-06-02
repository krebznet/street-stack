package com.dunkware.trade.service.stream.server.controller.session.container;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;

public interface SessionContainerHandler {
	
	public void onControllerMessage(GContainerServerMessage message);
	
}
