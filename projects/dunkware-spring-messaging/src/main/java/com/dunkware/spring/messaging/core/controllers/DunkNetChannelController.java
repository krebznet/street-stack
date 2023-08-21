package com.dunkware.spring.messaging.core.controllers;

import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.core.DunkMessageHandler;
import com.dunkware.spring.messaging.message.DunkNetMessage;


public class DunkNetChannelController implements DunkMessageHandler {

	private DunkNet net;

	@Override
	public void start(DunkNet dunkNet) {
		this.net = dunkNet;
	}

	@Override
	public boolean handle(DunkNetMessage message) {
		switch(message.getType()) { 
		case DunkNetMessage.TYPE_CHANNEL_REQUEST:
			break;
		case DunkNetMessage.TYPE_CHANNEL_RESPONSE:
			break;
		case DunkNetMessage.TYPE_CHANNEL_DISPOSE:
		}
		
		return false;
	}

}
