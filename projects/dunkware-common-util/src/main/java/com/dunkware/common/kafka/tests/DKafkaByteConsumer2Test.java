package com.dunkware.common.kafka.tests;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;

public class DKafkaByteConsumer2Test {
	
	public static int myCounter = 0;
	public static int countBatch = 0; 
	public static DStopWatch stopWatch = DStopWatch.create();
	

	public static void main(String[] args) {
		//DProperties props = DPropertiesBuilder.newBuilder()
		//	.addProperty("topics", "fuckpoop")
		//.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, " 172.16.16.55:31090").build();
		
		
		DKafkaByteConsumer2 consumer = null;
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker("172.16.16.55:31090").setClientAndGroup("dd" + DUUID.randomUUID(5), "ff" + DUUID.randomUUID(3))
					.addTopic("dunknet.testrock.node.ping").build();
				consumer = DKafkaByteConsumer2.newInstance(spec);
				stopWatch.start();
				consumer.addStreamHandler(new DKafkaByteHandler2() {
					
					
					@Override
					public void record(ConsumerRecord<String, byte[]> record) {
						try {
							myCounter++;
							countBatch++;
							  String s = new String(record.value(), StandardCharsets.UTF_8);
							  System.out.println(s);
							if(countBatch == 1000) { 
								stopWatch.stop();
								try {
									//System.out.println(DJson.serializePretty(snapshot));
								} catch (Exception e) {
									e.printStackTrace();
									// TODO: handle exception
								}
								System.out.println("Recieved 1K Snapshots in " + stopWatch.getCompletedSeconds() + " seconds " + " Total " + myCounter);
								stopWatch.start();
								countBatch = 0; 
								
							}
							//
						
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}

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
