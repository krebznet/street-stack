package com.dunkware.trade.service.stream.server.controller.event;

import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.utils.core.events.DunkEvent;

public class EStreamEvent extends DunkEvent  {

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

