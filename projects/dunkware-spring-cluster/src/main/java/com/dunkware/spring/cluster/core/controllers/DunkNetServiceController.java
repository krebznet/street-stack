package com.dunkware.spring.cluster.core.controllers;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.DunkNetComponent.ComponentMethod;
import com.dunkware.spring.cluster.DunkNetException;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.spring.cluster.core.DunkNetController;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;
import com.dunkware.spring.cluster.message.DunkNetMessage;

public class DunkNetServiceController implements DunkNetController {

	private DunkNet net; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Marker marker = MarkerFactory.getMarker("DunkNet");

	
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
			DunkNetNode node = net.getNode(message.getSenderId());
			if(node == null) { 
				logger.error(marker, "Invalid Service Request sourceNode not found " + message.getSenderId());
				return true;
			}
			ComponentMethod method = null;
			try {
				method = net.getSerivceMethod(payload);
			} catch (Exception e) {
				DunkNetMessage response = DunkNetMessage.builder().serviceException(requestId,e.toString()).buildMessage();
				try {
					logger.info("Send Request Message");
					node.message(response);	
				} catch (Exception e2) {
					logger.error("Exception sending service response exception " + e2.toString());
				}
				return true;
			}
			
			
			ServiceRunnable runnable = new ServiceRunnable(payload, method, requestId, node);
			net.getExecutor().execute(runnable);
			return true;
		}
		
		if(message.isServiceResponse()) { 
			String requestId = message.getRequestId();
			if(pendingServiceRequests.containsKey(requestId)) { 
				DunkNetServiceRequest request = pendingServiceRequests.remove(requestId);
				request.response(message);
				return true;
			}
		}
		return false;
		
	}

	public DunkNetServiceRequest serviceRequest(Object payload) throws DunkNetException { 
		for (DunkNetNode node : net.getNodes()) {
			if(node.isServiceProvider(payload)) { 
				return serviceRequest(node, payload);
			}
		}
		throw new DunkNetException("No nodes found with service provider input " + payload.getClass().getName());
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
				DunkNetMessage message = DunkNetMessage.builder().serviceResponse(output, requestId).buildMessage();
				try {
					sourceNode.message(message);	
				} catch (Exception e) {
					logger.error("Exception sending service response " + e.toString());
				}
			} catch (Exception e) {
				DunkNetMessage message = DunkNetMessage.builder().serviceException(requestId,e.toString()).buildMessage();
				try {
					sourceNode.message(message);	
				} catch (Exception e2) {
					logger.error("Exception sending service response exception " + e2.toString());
				}
			}
			
		}
		
		
	}
	
	

}
