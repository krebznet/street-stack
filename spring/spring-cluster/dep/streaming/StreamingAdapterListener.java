package com.dunkware.spring.messaging.streaming;

public interface StreamingAdapterListener {

	void clientDisconnect(StreamingAdapter adapter);
	
	void serverDisconnect(StreamingAdapter adapter);
}
