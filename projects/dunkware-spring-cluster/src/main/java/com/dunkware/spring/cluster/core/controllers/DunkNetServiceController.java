package com.dunkware.spring.cluster.core.controllers;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetComponent.ComponentMethod;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;

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
		node.message(message);
		return req;
	}
	
	
	private class ServiceRunnable implements Runnable { 
		
		private Object input; 
		private ComponentMethod method; 
		private String requestId; 
		private DunkNetNode sourceNode;
		public ServiceRunnable(Object input, ComponentMethod serviceMethod, String requestId, DunkNetNode sourceNode) { 
			this.requestId = requestId; 
			this.sourceNode = sourceNode;
			this.input = input;
			this.method = serviceMethod;
		}
		
		public void run() {
			try {
				Object output = method.getMethod().invoke(method.getObject(),input);
				// make a message and bam; 
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
	}
	
	

}
