package com.dunkware.trade.service.data.service.stream.container;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;

public interface StreamContainerHandler {
	
	public void onServerMessage(GContainerServerMessage message);

}
