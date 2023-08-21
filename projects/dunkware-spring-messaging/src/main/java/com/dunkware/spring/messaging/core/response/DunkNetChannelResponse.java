package com.dunkware.spring.messaging.core.response;

public interface DunkNetChannelResponse {

	public void exception(String exception);
	
	public void start(Object response);
	
	
}
