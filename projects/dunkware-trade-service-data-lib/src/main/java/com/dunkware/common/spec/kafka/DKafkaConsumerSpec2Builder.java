package com.dunkware.common.spec.kafka;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ThrottleType;

public class DKafkaConsumerSpec2Builder {

	public static DKafkaConsumerSpec2Builder newBuilder(DKafkaConsumerSpec2.ConsumerType consumerType, DKafkaConsumerSpec2.OffsetType offsetType) throws Exception {
		return new DKafkaConsumerSpec2Builder(consumerType,offsetType);
	}

	private DKafkaConsumerSpec2.ConsumerType type;
	private List<String> brokers = new ArrayList<String>();
	private List<String> topics = new ArrayList<String>();
	private List<Integer> partitions = new ArrayList<Integer>();
	private List<Long> partitionOffsets = new ArrayList<Long>();
	
	private String clientId;
	private String groupId;

	private boolean throttle = false;
	private int throttleLimit = 0;

	private DKafkaConsumerSpec2.OffsetType offsetType = OffsetType.Latest;
	private long offset = 0;

	private DKafkaConsumerSpec2Builder(DKafkaConsumerSpec2.ConsumerType type, DKafkaConsumerSpec2.OffsetType offsetType) throws Exception{
		this.type = type;
		this.offsetType = offsetType; 
		if(type != ConsumerType.Manual && offsetType == OffsetType.Manual) {
			throw new Exception("Invalid Configuration with non manual consumer type but offset manual, consumer type needs to be topics");
		}
	}

	public DKafkaConsumerSpec2Builder setBrokerString(String brokers) {
		this.brokers.clear();
		String[] values = brokers.split(",");
		for (String string : values) {
			this.brokers.add(string);
		}
		return this;
	}

	public DKafkaConsumerSpec2Builder addBroker(String broker) {
		this.brokers.add(broker);
		return this;
	}

	public DKafkaConsumerSpec2Builder addTopic(String topic) {
		this.topics.add(topic);
		return this;
	}

	public DKafkaConsumerSpec2Builder setTopicString(String input) {
		this.topics.clear();
		String[] values = input.split(",");
		for (String string : values) {
			topics.add(string);
		}
		return this;
	}

	public DKafkaConsumerSpec2Builder setClientAndGroup(String clientId, String groupId) {
		this.clientId = clientId;
		this.groupId = groupId;
		return this;
	}

	public DKafkaConsumerSpec2Builder addPartition(int partition) throws Exception {
		if(this.offsetType == offsetType.Manual) { 
			throw new Exception("Must specify offset when adding partition with offset in manual mode");
		}
		this.partitions.add(partition);
		return this;
	}
	
	
	public DKafkaConsumerSpec2Builder addPartition(int partition, long offset) {
		this.partitions.add(partition);
		this.partitionOffsets.add(offset);
		return this;
	}

	public DKafkaConsumerSpec2Builder setThrottle(int limit) {
		throttle = true;
		throttleLimit = limit;
		return this;
	}

	public DKafkaConsumerSpec2 build() throws Exception {
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
		if (type == DKafkaConsumerSpec2.ConsumerType.Manual) {
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

		DKafkaConsumerSpec2 spec = new DKafkaConsumerSpec2();
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
		if (this.offsetType == DKafkaConsumerSpec2.OffsetType.Manual) {
			// then we need to add the parittion offsets 
			spec.setPartitionOffsets(partitionOffsets.toArray(new Long[partitionOffsets.size()]));
		}
		

		return spec;
	}

}