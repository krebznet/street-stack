package com.dunkware.trade.service.data.service.message;

import com.dunkware.trade.service.data.json.controller.message.StreamSessionPing;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStart;
import com.dunkware.trade.service.data.json.controller.message.StreamSessionStop;

public interface MessageHandler {
	
	public default void sessionPing(StreamSessionPing ping) { 
		
	}
	
	public default void sessionStart(StreamSessionStart start) {
		
	}
	
	public default void sessionStop(StreamSessionStop stop) {
		
	}

}
