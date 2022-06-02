package com.dunkware.net.core.messaging;

public interface DChannelService {
	
	public void service(DChannel channel, DMessage request) throws DException;

   
}
