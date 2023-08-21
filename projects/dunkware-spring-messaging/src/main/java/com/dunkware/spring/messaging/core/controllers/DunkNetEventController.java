package com.dunkware.spring.messaging.core.controllers;

import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.core.DunkNetController;
import com.dunkware.spring.messaging.core.component.DunkNetComponent;
import com.dunkware.spring.messaging.message.DunkNetMessage;


public class DunkNetEventController implements DunkNetController {
	
	private DunkNet net;
	

	@Override
	public void init(DunkNet net) {
		this.net = net; 
		
	}
	
	// processOutboundMessage() -- if its a service 
	// processInboundMessage(); 
	// outboundMessage() - 

	@Override
	public boolean handleMessage(DunkNetMessage message) {
		if(message.getType() == DunkNetMessage.TYPE_EVENT) { 
			for (DunkNetComponent component : net.getComponents()) {
				//if(component.)
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
