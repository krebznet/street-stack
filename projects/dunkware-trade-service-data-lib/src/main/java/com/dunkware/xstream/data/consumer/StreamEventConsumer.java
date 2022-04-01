package com.dunkware.xstream.data.consumer;

public interface StreamEventConsumer  {
	
	public void addEventHandler(StreamEventHandler handler);
	
	public void removeEventHandler(StreamEventHandler handler);
	
	public void start(StreamEventConsumerConfig config) throws StreamEventConsumerException;
	
	public void dispose();
		

	

}
