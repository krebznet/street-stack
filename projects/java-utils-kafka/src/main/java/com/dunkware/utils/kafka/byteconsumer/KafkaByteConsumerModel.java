package com.dunkware.utils.kafka.byteconsumer;

import com.dunkware.utils.kafka.byteconsumer.KafkaByteConsumerSpec.ConsumerType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder()
/**
 * Kafka Baby
 */
public class KafkaByteConsumerModel {

	public static enum OffsetType { 
		Latest,Earliest,Manaul
	}
	
	public static enum IdentType {
		Manual,Random 
	}
	
	public static enum ConsumeType { 
		Auto,Manual,AllPartitions
	}
	
	private String brokers; 
	private String topics; 
	private OffsetType offsetType; 
	private IdentType identType; 
	private ConsumerType consumerType; 
	private String partitions; 
	private String consumerId; 
	private String consumerGroup; 
	private long throttleThreshold;
	
	
	
	
	
	
	

	 
	
	

}
