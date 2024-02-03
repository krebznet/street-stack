package com.dunkware.trade.service.stream.worker.session.container.core;

import com.dunkware.net.proto.cluster.GContainerWorkerMessage;

public interface WorkerContainerHandler {

	public void consumeMessage(GContainerWorkerMessage message);
	
}
