package com.dunkware.spring.cluster.core.controllers;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.message.DunkNetMessage;


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
		
		}
		// TODO Auto-generated method stub
		return false;
	}

}
