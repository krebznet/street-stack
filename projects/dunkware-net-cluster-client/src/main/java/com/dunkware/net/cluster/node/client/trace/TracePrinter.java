package com.dunkware.net.cluster.node.client.trace;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.net.cluster.GTrace;
import com.dunkware.net.cluster.util.trace.TraceFormatter;

public class TracePrinter implements DKafkaByteHandler2 {

	public static void main(String[] args) {
		try {
			new TracePrinter("localhost:9091", "cluster-trace");	
			Thread stay = new Thread() { 
				public void run() { 
					while(true) {
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			};
			stay.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);

		}
		
	}
	
	
	private String brokers; 
	private String topic; 
	
	private DKafkaByteConsumer2 kafkaConsumer; 
	
	public TracePrinter(String brokers, String topic)  throws Exception { 
		this.brokers = brokers;
		this.topic = topic;
		try {
			kafkaConsumer = DKafkaByteConsumer2.newInstance(DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Earliest).setBrokerString(brokers).setClientAndGroup("Prinjjktejr", "Pjkkrinter").setTopicString(topic).build());
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			System.err.println(e.toString());
			System.exit(-1);
		}
	
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		byte[] value = record.value();
		try {
			GTrace trace = GTrace.parseFrom(value);
			System.out.println(TraceFormatter.format1(trace));
			
		} catch (Exception e) {
			System.err.println("GTrace parse " + e.toString());
			return;
		}
		
		
		
	}
	
	
		
	
}
