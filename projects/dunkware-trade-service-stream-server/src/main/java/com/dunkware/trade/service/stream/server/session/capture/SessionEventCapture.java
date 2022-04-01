package com.dunkware.trade.service.stream.server.session.capture;

import org.springframework.integration.support.MessageBuilder;

import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartReq;
import com.dunkware.trade.service.stream.server.session.capture.protocol.SessionEventCaptureWorkerStartResp;

public class SessionEventCapture {
	
	private ClusterNode clusterNode; 
	private SessionEventCaptureConfig config; 
	
	private SessionEventCaptureWorkerStartResp startResponse;
	
	public void start(ClusterNode node, SessionEventCaptureConfig config) throws Exception { 
		this.config = config;
		this.clusterNode = node;
		
		
		
		SessionEventCaptureWorkerStartReq req = new SessionEventCaptureWorkerStartReq();
		req.setEventBrokers(config.getEventBrokers());
		req.setEventTopics(config.getEventBrokers());
		req.setMongoCollection(config.getMongoCollection());
		req.setMongoURL(config.getMongoURL());
		
		String path = clusterNode.getEndpoint("/stream/event/capture/start");
		SessionEventCaptureWorkerStartResp response = null;
		try {
			response = (SessionEventCaptureWorkerStartResp)DHttpHelper.postJsonWithResponse(path, req, SessionEventCaptureWorkerStartResp.class);
		} catch (Exception e) {
			throw new Exception("Exception starting mongo event node capture on node " + e.toString());
		}
		if(response.isSuccess() == false) { 
			throw new Exception("event capture worker error returned  " + response.getError());
		}
		
	}
	
	public void dispose() {
		
	}
	
	
	
	

}
