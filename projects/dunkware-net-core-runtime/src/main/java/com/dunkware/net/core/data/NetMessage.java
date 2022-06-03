package com.dunkware.net.core.data;

public interface NetMessage {

	int getMessageId();
	
	void setCallbackid(int id);
	
	NetBean getData();
	
	String getEndPoint();
	
	boolean isCallback();

	
	
	
}
