package com.dunkware.trade.service.data.service.message;

import com.dunkware.trade.service.stream.json.message.StreamSessionPing;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;

public interface StreamMessageHandler {
	
	public default void sessionPing(StreamSessionPing ping) { 
		
	}
	
	public default void sessionStart(StreamSessionStart start) {
		
	}
	
	public default void sessionStop(StreamSessionStop stop) {
		
	}

}
