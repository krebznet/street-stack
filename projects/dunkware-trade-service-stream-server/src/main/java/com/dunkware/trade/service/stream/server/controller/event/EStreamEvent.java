package com.dunkware.trade.service.stream.server.controller.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.stream.server.controller.StreamController;

public class EStreamEvent extends DEvent  {

	private StreamController stream; 
	
	public EStreamEvent(StreamController stream) { 
		this.stream = stream;
	}
	
	public StreamController getStream() { 
		return stream;
	}
}
// dunkware-cluster-server
// dunkware-cluster-node
// dunkware-cluster-model

