package com.dunkware.common.spec.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class DKafkaByteConsumer2Spec {

	public static enum ConsumerType { 
		Auto,Manual,AllPartitions
	}
	
	public static enum LogLevel { 
		Debug,Info,Error
	}
	
	public static enum OffsetType { 
		Latest,Earliest,Manual
	}
	
	public static enum ThrottleType { 
		None,Manual;
	}
	

	private String[] brokers; 
	private String consumerId = null;
	private String consumerGroup = null;
	private String[] topics;


	
	private boolean enableQueueLimit = false; 
	private int throttleLimit = 10000;
	
	private Integer[] partitions = null;
	private Long[] partitionOffsets = null;
	
	private ConsumerType consumerType = ConsumerType.Auto;
	private ThrottleType throttleType = ThrottleType.None;
	private OffsetType offsetType = OffsetType.Latest;
	
	private LogLevel logLevel = LogLevel.Info;
	private String logIdentifier = null; 
	
	public DKafkaByteConsumer2Spec() { 
		
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

	public boolean isEnableQueueLimit() {
		return enableQueueLimit;
	}

	public void setEnableQueueLimit(boolean enableQueueLimit) {
		this.enableQueueLimit = enableQueueLimit;
	}

	
	public int getThrottleLimit() {
		return throttleLimit;
	}


	public void setThrottleLimit(int throttleLimit) {
		this.throttleLimit = throttleLimit;
	}

	public ConsumerType getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(ConsumerType consumerType) {
		this.consumerType = consumerType;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogIdentifier() {
		return logIdentifier;
	}

	public void setLogIdentifier(String logIdentifier) {
		this.logIdentifier = logIdentifier;
	}


	public String[] getBrokers() {
		return brokers;
	}

	public void setBrokers(String[] brokers) {
		this.brokers = brokers;
	}


	public String[] getTopics() {
		return topics;
	}


	public void setTopics(String[] topics) {
		this.topics = topics;
	}


	public ThrottleType getThrottleType() {
		return throttleType;
	}


	public void setThrottleType(ThrottleType throttleType) {
		this.throttleType = throttleType;
	}


	public OffsetType getOffsetType() {
		return offsetType;
	}


	public void setOffsetType(OffsetType offsetType) {
		this.offsetType = offsetType;
	}


	public Integer[] getPartitions() {
		return partitions;
	}


	public void setPartitions(Integer[] partitions) {
		this.partitions = partitions;
	}


	public Long[] getPartitionOffsets() {
		return partitionOffsets;
	}


	public void setPartitionOffsets(Long[] partitionOffsets) {
		this.partitionOffsets = partitionOffsets;
	}


	
	

	
	
	
	
	
	
	
	
	
	
}
