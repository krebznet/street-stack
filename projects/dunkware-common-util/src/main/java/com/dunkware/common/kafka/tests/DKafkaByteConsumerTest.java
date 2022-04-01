package com.dunkware.common.kafka.tests;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;

public class DKafkaByteConsumerTest {

	public static void main(String[] args) {
		DProperties props = DPropertiesBuilder.newBuilder()
		.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, "172.16.16.55:31090")
		.addProperty("topics", "stream_us_equity_events").build();
		try {
			AtomicInteger counter = new AtomicInteger(0);
			DKafkaByteConsumer consumer = DKafkaByteConsumer.newInstance(props);
			consumer.addStreamHandler(new DKafkaByteHandler() {
				
				@Override
				public void streamBytes(byte[] bytes) {
					try {
						
						counter.incrementAndGet();
						//Tick tick = Tick.parseFrom(bytes);
					//	if(counter.incrementAndGet() == 25000) { 
							//System.out.println(TickHelper.printTick(tick));
						//	counter.set(0);
					//	}
						
					} catch (Exception e) {
						System.err.println("exception parsing tick " + e.toString());
					}
				}
			});
			
			class RecordsPerSecond extends Thread { 
				
				public void run() { 
					try {
						while(true) { 
							Thread.sleep(1000);
							System.out.println(counter.get());
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
