package com.dunkware.spring.cluster;

public interface DunkNetChannel {
	
	void addComponent(DunkNetComponent component) throws DunkNetException;
	
	void closeChannel();
	
	void notifyInit();
	
	Object getRequestResponse();
	
	void event(Object payload) throws DunkNetException; 
	
	public Object serviceBlocking(Object payload) throws DunkNetException;
	
	public void addListener(DunkNetChannelListener listener);
	
	public void removeListener(DunkNetChannelListener listener);
	
	
	
	
	

}
