package com.dunkware.common.kafka.tests;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;

public class DKafkaByteConsumer2Test {

	public static void main(String[] args) {
		DProperties props = DPropertiesBuilder.newBuilder()
		.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, "localhost:9091")
		.addProperty("topics", "stream_us_equity_snapshots").build();
		DKafkaByteConsumer2 consumer = null;
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Earliest).addBroker("localhost:9091").setClientAndGroup("dd", "ff")
					.addTopic("stream_us_equity_snapshots").build();
				consumer = DKafkaByteConsumer2.newInstance(spec);
				consumer.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			// TODO: handle exception
		}
		
		try {
			AtomicInteger counter = new AtomicInteger(0);
			
			consumer.addStreamHandler(new DKafkaByteHandler2() {
				
				
				@Override
				public void record(ConsumerRecord<String, byte[]> record) {
					counter.incrementAndGet();	
				}

				
			});
			
			class RecordsPerSecond extends Thread { 
				
				public void run() { 
					try {
						while(true) { 
							Thread.sleep(1000);
							System.out.println("Consumer2" + counter.get());
							counter.set(0);	
						}
						
					} catch (Exception e) {
						
					}
				}
			}
			
			RecordsPerSecond per = new RecordsPerSecond();
			per.start();
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			//System.err.println(e.toString());
			//e.printStackTrace();
		}
	}
}
