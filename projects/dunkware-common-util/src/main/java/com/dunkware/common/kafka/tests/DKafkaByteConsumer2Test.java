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
	//	DProperties props = DPropertiesBuilder.newBuilder()
		//		.addProperty("topics", "fuckpoop")
		//.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, " 172.16.16.55:31090").build();
		DKafkaByteConsumer2 consumer = null;
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Earliest).addBroker("172.16.16.55:31090").setClientAndGroup("dd", "ff")
					.addTopic("fuckpoop").build();
				consumer = DKafkaByteConsumer2.newInstance(spec);
				consumer.addStreamHandler(new DKafkaByteHandler2() {
					
					@Override
					public void record(ConsumerRecord<String, byte[]> record) {
						System.out.println("recieved byte[] messsage");
						System.out.println("received record " + String.valueOf(record));
					}
				});
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
							counter.incrementAndGet();	
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
