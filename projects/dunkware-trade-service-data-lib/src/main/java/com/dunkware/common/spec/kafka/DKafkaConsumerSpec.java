package com.dunkware.common.spec.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class DKafkaConsumerSpec {

	private String brokers; 
	private String consumerId = null;
	private String consumerGroup = null;
	private String topics;
	
	private boolean latestOffset = true; 
	private boolean specificOffset = false; 
	private long offset; 
	
	public DKafkaConsumerSpec() { 
		
	}

	public String getBrokers() {
		return brokers;
	}

	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public boolean isLatestOffset() {
		return latestOffset;
	}

	public void setLatestOffset(boolean latestOffset) {
		this.latestOffset = latestOffset;
	}

	public boolean isSpecificOffset() {
		return specificOffset;
	}

	public void setSpecificOffset(boolean specificOffset) {
		this.specificOffset = specificOffset;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	
}
