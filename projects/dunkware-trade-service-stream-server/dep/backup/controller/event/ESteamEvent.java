package com.dunkware.trade.service.stream.server.controller.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.stream.server.controller.StreamController;

public class ESteamEvent extends DEvent  {

	private StreamController stream; 
	
	public ESteamEvent(StreamController stream) { 
		this.stream = stream;
	}
	
	public StreamController getStream() { 
		return stream;
	}
}
