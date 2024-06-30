package com.dunkware.spring.cluster;

public interface DunkNetChannelHandler {
	
	public void channelInit(DunkNetChannel channel) throws DunkNetException;
	
	public void channelStart() throws DunkNetException;
	
	public void channelClose();
	
	public void channelStartError(String exception);
	
	

}
