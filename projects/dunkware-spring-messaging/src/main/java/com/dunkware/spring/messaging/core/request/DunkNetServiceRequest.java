package com.dunkware.spring.messaging.core.request;

import java.util.concurrent.CountDownLatch;

import com.dunkware.spring.messaging.DunkNetException;
import com.dunkware.spring.messaging.DunkNetNode;
import com.dunkware.spring.messaging.message.DunkNetMessage;
import com.dunkware.spring.messaging.protocol.DunkNetNodeService;

public class DunkNetServiceRequest {

	private DunkNetNode node;
	private Object payload;
	//private volatile T result = null;
	private volatile Throwable throwable = null;
	private volatile boolean completed = false; 
	private CountDownLatch completionLatch = new CountDownLatch(1);
	private String requestId; 
	private DunkNetNodeService serviceDescriptor;
	
	public DunkNetServiceRequest(DunkNetNode node, Object payload) throws DunkNetException { 
		this.node = node; 
		this.serviceDescriptor = node.getServiceDescriptor(payload);
		if(serviceDescriptor == null) { 
			throw new DunkNetException("Service Descriptor on node " + node.getId() + " not found for class " + payload.getClass().getName());
		}
		
	}

	public void response(DunkNetMessage message ) { 
		Integer respCode = (Integer)message.getHeader(DunkNetMessage.KEY_RESPONSE_CODE);
		if(respCode == DunkNetMessage.RESPONSE_ERROR) { 
			String error = message.getHeader(DunkNetMessage.KEY_RESPONSE_ERROR).toString();
			throwable = new Throwable(error);
			completed = true;
		}
	}
	

	public  <T> T get(Class<T> type) throws Exception { 
		// this can wait- 
		return type.newInstance();
		// service(Objectpayload, EntityStatResp.class); 
	}
	
	
	
	

	

}
