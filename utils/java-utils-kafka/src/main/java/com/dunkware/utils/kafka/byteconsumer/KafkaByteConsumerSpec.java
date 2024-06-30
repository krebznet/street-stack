package com.dunkware.utils.kafka.byteconsumer;



import java.util.ArrayList;
import java.util.List;



public class KafkaByteConsumerSpec {

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
	
	public KafkaByteConsumerSpec() {
		
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






	public static Builder newBuilder(KafkaByteConsumerSpec.ConsumerType type, KafkaByteConsumerSpec.OffsetType offsetType) throws Exception {
		return new Builder(type, offsetType);
	}
	

	public static class Builder {
		private KafkaByteConsumerSpec.ConsumerType type;
		private List<String> brokers = new ArrayList<String>();
		private List<String> topics = new ArrayList<String>();
		private List<Integer> partitions = new ArrayList<Integer>();
		private List<Long> partitionOffsets = new ArrayList<Long>();

		private String clientId;
		private String groupId;

		private boolean throttle = false;
		private int throttleLimit = 0;

		private KafkaByteConsumerSpec.OffsetType offsetType = OffsetType.Latest;
		private long offset = 0;

		private Builder(KafkaByteConsumerSpec.ConsumerType type, KafkaByteConsumerSpec.OffsetType offsetType) throws Exception{
			this.type = type;
			this.offsetType = offsetType;
			if(type != ConsumerType.Manual && offsetType == OffsetType.Manual) {
				throw new Exception("Invalid Configuration with non manual consumer type but offset manual, consumer type needs to be topics");
			}
		}

		public Builder setBrokerString(String brokers) {
			this.brokers.clear();
			String[] values = brokers.split(",");
			for (String string : values) {
				this.brokers.add(string);
			}
			return this;
		}

		public Builder addBroker(String broker) {
			this.brokers.add(broker);
			return this;
		}

		public Builder addTopic(String topic) {
			this.topics.add(topic);
			return this;
		}

		public Builder setTopicString(String input) {
			this.topics.clear();
			String[] values = input.split(",");
			for (String string : values) {
				topics.add(string);
			}
			return this;
		}

		public Builder setClientAndGroup(String clientId, String groupId) {
			this.clientId = clientId;
			this.groupId = groupId;
			return this;
		}

		public Builder addPartition(int partition) throws Exception {
			if(this.offsetType == offsetType.Manual) {
				throw new Exception("Must specify offset when adding partition with offset in manual mode");
			}
			this.partitions.add(partition);
			return this;
		}


		public Builder addPartition(int partition, long offset) {
			this.partitions.add(partition);
			this.partitionOffsets.add(offset);
			return this;
		}

		public Builder setThrottle(int limit) {
			throttle = true;
			throttleLimit = limit;
			return this;
		}



		public KafkaByteConsumerSpec build() throws Exception {
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
			if (type == KafkaByteConsumerSpec.ConsumerType.Manual) {
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

			KafkaByteConsumerSpec spec = new KafkaByteConsumerSpec();
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
			if (this.offsetType == KafkaByteConsumerSpec.OffsetType.Manual) {
				// then we need to add the parittion offsets
				spec.setPartitionOffsets(partitionOffsets.toArray(new Long[partitionOffsets.size()]));
			}


			return spec;
		}

	}
	
	
	
	
	
	
}


