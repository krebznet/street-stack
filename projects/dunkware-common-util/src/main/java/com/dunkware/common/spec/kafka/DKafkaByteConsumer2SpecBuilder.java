package com.dunkware.common.spec.kafka;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ThrottleType;

public class DKafkaByteConsumer2SpecBuilder {

	public static DKafkaByteConsumer2SpecBuilder newBuilder(DKafkaByteConsumer2Spec.ConsumerType consumerType, DKafkaByteConsumer2Spec.OffsetType offsetType) throws Exception {
		return new DKafkaByteConsumer2SpecBuilder(consumerType,offsetType);
	}

	private DKafkaByteConsumer2Spec.ConsumerType type;
	private List<String> brokers = new ArrayList<String>();
	private List<String> topics = new ArrayList<String>();
	private List<Integer> partitions = new ArrayList<Integer>();
	private List<Long> partitionOffsets = new ArrayList<Long>();
	
	private String clientId;
	private String groupId;

	private boolean throttle = false;
	private int throttleLimit = 0;

	private DKafkaByteConsumer2Spec.OffsetType offsetType = OffsetType.Latest;
	private long offset = 0;

	private DKafkaByteConsumer2SpecBuilder(DKafkaByteConsumer2Spec.ConsumerType type, DKafkaByteConsumer2Spec.OffsetType offsetType) throws Exception{
		this.type = type;
		this.offsetType = offsetType; 
		if(type != ConsumerType.Manual && offsetType == OffsetType.Manual) {
			throw new Exception("Invalid Configuration with non manual consumer type but offset manual, consumer type needs to be topics");
		}
	}

	public DKafkaByteConsumer2SpecBuilder setBrokerString(String brokers) {
		this.brokers.clear();
		String[] values = brokers.split(",");
		for (String string : values) {
			this.brokers.add(string);
		}
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder addBroker(String broker) {
		this.brokers.add(broker);
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder addTopic(String topic) {
		this.topics.add(topic);
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder setTopicString(String input) {
		this.topics.clear();
		String[] values = input.split(",");
		for (String string : values) {
			topics.add(string);
		}
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder setClientAndGroup(String clientId, String groupId) {
		this.clientId = clientId;
		this.groupId = groupId;
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder addPartition(int partition) throws Exception {
		if(this.offsetType == offsetType.Manual) { 
			throw new Exception("Must specify offset when adding partition with offset in manual mode");
		}
		this.partitions.add(partition);
		return this;
	}
	
	
	public DKafkaByteConsumer2SpecBuilder addPartition(int partition, long offset) {
		this.partitions.add(partition);
		this.partitionOffsets.add(offset);
		return this;
	}

	public DKafkaByteConsumer2SpecBuilder setThrottle(int limit) {
		throttle = true;
		throttleLimit = limit;
		return this;
	}

	public DKafkaByteConsumer2Spec build() throws Exception {
		if(offsetType == OffsetType.Manual) { 
			if(topics.size() > 1) { 
				throw new Exception("You cannot have manual offset configured when you are consuming from more than 1 topic");
			}
		}
		if(type == ConsumerType.Manual) { 
			if(topics.size() > 0) { 
				throw new Exception("When setting manual topic offset partitions you can only subscribe to 1 topic");
			}
		}
		if (type == DKafkaByteConsumer2Spec.ConsumerType.Manual) {
			if (partitions.size() == 0) {
				throw new Exception("Atleast 1 partition should be added for Manual Consumer Type");
			}
		}
		if (clientId == null || groupId == null) {
			throw new Exception("Cleint and & Group ID Need to be set");
		}
		if (brokers.size() == 0) {
			throw new Exception("Atleast 1 broker should be set");
		}

		if (topics.size() == 0) {
			throw new Exception("at least 1 topic should be set");
		}

		DKafkaByteConsumer2Spec spec = new DKafkaByteConsumer2Spec();
		spec.setBrokers(brokers.toArray(new String[brokers.size()]));
		spec.setTopics(topics.toArray(new String[topics.size()]));
		spec.setConsumerGroup(groupId);
		spec.setConsumerId(clientId);

		// consumer type
		spec.setConsumerType(this.type);

		// throttle
		if (throttle) {
			spec.setThrottleLimit(throttleLimit);
			spec.setThrottleType(ThrottleType.Manual);
		}
		if (!throttle) {
			spec.setThrottleType(ThrottleType.None);
		}
		
		// partitions 
		if(type == ConsumerType.Manual) { 
			if(partitions.size() == 0) { 
				throw new Exception("Manual consumer must specify at least 1 partition to consume");
			}
			spec.setPartitions(partitions.toArray(new Integer[partitions.size()]));
		}
		
		

		// offset
		spec.setOffsetType(this.offsetType);
		if (this.offsetType == DKafkaByteConsumer2Spec.OffsetType.Manual) {
			// then we need to add the parittion offsets 
			spec.setPartitionOffsets(partitionOffsets.toArray(new Long[partitionOffsets.size()]));
		}
		

		return spec;
	}

}