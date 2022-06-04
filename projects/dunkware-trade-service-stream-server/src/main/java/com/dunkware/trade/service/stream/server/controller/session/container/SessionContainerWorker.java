package com.dunkware.trade.service.stream.server.controller.session.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.proto.cluster.GContainerWorkerMessage;

public class SessionContainerWorker {

	private ClusterNode node; 
	private String workerId; 
	
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<GContainerWorkerMessage> messageQueue = new LinkedBlockingQueue<GContainerWorkerMessage>();
	
	private DKafkaByteProducer messageProducer; 
	
	private String kafkaBrokers;
	
	private SessionContainer controller;
	private DataStreamWorkerContainerStartReq req;
	

	public void start(ClusterNode node, DataStreamWorkerContainerStartReq req, SessionContainer controller) throws Exception { 
		this.req = req;
		this.node = node;
		this.controller = controller;
		
		DataStreamWorkerContainerStartResp resp = null;
		try {
			resp = (DataStreamWorkerContainerStartResp)node.jsonPost("/worker/stream/container/start", req, DataStreamWorkerContainerStartResp.class);
			if(resp.isSuccessfull() == false) { 
				throw new Exception("Worker sevice returned exception " + resp.getException());			}
		} catch (Exception e) {
			logger.error("Exception starting node " + e.toString());
			throw e;
		} 
		
		try {
			messageProducer = DKafkaByteProducer.newInstance(req.getKafkaBroker(),req.getWorkerTopic(),"ContainerWorker_" + req.getWorkerId());
		} catch (Exception e) {
			logger.error("exception creating kafka producer " + e.toString());
		}
	}
	
	public void sendMessage(GContainerWorkerMessage message) {
		try {
			messageProducer.sendBytes(message.toByteArray());	
		} catch (Exception e) {
			logger.error("Exception sending worker " + req.getWorkerId() + " message " + e.toString(),e);
		}
	}
	
	public String getWorkerId() { 
		return workerId; 
	}
	
	public ClusterNode getNode() { 
		return node; 
	}
	
	
}