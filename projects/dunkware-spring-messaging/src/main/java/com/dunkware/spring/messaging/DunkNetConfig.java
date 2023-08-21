package com.dunkware.spring.messaging;

import com.dunkware.common.util.executor.DExecutor;

public class DunkNetConfig {
	
	private String brokers; 
	private String consumer; 
	private String consumerGroup; 
	private String nodeId;
	private DExecutor executor; 
	
	public String getBrokers() {
		return brokers;
	}
	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getConsumerGroup() {
		return consumerGroup;
	}
	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public DExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DExecutor executor) {
		this.executor = executor;
	} 
	
	
	
	

}
