package com.dunkware.spring.messaging.core.controllers;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.spring.messaging.DunkNetException;
import com.dunkware.spring.messaging.DunkNet;
import com.dunkware.spring.messaging.DunkNetNode;
import com.dunkware.spring.messaging.core.DunkNetController;
import com.dunkware.spring.messaging.core.request.DunkNetServiceRequest;
import com.dunkware.spring.messaging.message.DunkNetMessage;

public class DunkNetServiceController implements DunkNetController {

	private DunkNet net; 
	
	private ConcurrentHashMap<String,DunkNetServiceRequest> pendingServiceRequests = new ConcurrentHashMap<String,DunkNetServiceRequest>();
	
	
	@Override
	public void init(DunkNet net) {
		this.net = net;
		
	}

	@Override
	public boolean handleMessage(DunkNetMessage message) {
		
		// service request
		if(message.isServiceRequest()) { 
			String requestId = message.getMessageId();
			Object payload = message.getPayload();
			
			// make it a runnable -- call it catch an exception and 
			// then be done. 
			// we need component. 
		}
		
		if(message.isServiceResponse()) { 
			String requestId = message.getRequestId();
			DunkNetServiceRequest req = pendingServiceRequests.get(requestId);
			if(req != null) { 
				pendingServiceRequests.remove(requestId);
				
			}
			req.response(message);
			return true;
		}
		return false;
		
	}

	
	public DunkNetServiceRequest serviceRequest(DunkNetNode node, Object payload) throws DunkNetException { 
		DunkNetServiceRequest req = new DunkNetServiceRequest(node, payload);
		DunkNetMessage message = DunkNetMessage.builder().serviceRequest(payload).buildMessage();
		String requestId = message.getMessageId();
		pendingServiceRequests.put(requestId, req);
		node.send(message);
		return req;
	}
	
	

}
