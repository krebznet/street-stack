package com.dunkware.spring.streaming;

public interface StreamingListener {

	void clientDisconnect(StreamingAdapter adapter);
	
	void serverDisconnect(StreamingAdapter adapter);
}
