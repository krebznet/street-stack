package com.dunkware.trade.service.stream.server.streaming;

public interface StreamingListener {

	void clientDisconnect(StreamingAdapter adapter);
	
	void serverDisconnect(StreamingAdapter adapter);
}
