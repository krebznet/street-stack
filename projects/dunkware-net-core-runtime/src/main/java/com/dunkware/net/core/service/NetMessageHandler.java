package com.dunkware.net.core.service;

import com.dunkware.net.proto.net.GNetMessage;

public interface NetMessageHandler {
	
	public void handle(GNetMessage message);

}
