package com.dunkware.spring.streaming;

public interface StreamingAdapterListener {

	void clientDisconnect(StreamingAdapter adapter);
	
	void serverDisconnect(StreamingAdapter adapter);
}
