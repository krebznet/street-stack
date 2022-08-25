package com.dunkware.common.kafka.tests;

import java.util.UUID;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.tick.TickBuilder;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesBuilder;

public class DKafkaByteProducerTest {

	public static void main(String[] args) {
		DProperties props = DPropertiesBuilder.newBuilder()
				.addProperty("topics", "fuckpoop")
		.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, " 172.16.16.55:31090").build();
		//.addProperty(DKafkaProperties.BOOTSTRAP_SERVERS, "192.168.23.22:31090").build();
		try {
			
			//172.16.16.55:31090
		
			DKafkaByteProducer producer = DKafkaByteProducer.newInstance(props);
			
			while(true) {
				Tick tick = TickBuilder.newBuilder(1).doubleField(1, 32.23).doubleField(2, 33.0).stringField(4, UUID.randomUUID().toString()).build();
			//	System.out.println(TickHelper.printTick(tick));
				byte[] array = tick.toByteArray();
				producer.sendBytes(array);
					System.out.println("sent");
					Thread.sleep(1000);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			//System.err.println(e.toString());
			//e.printStackTrace();
		}
	}
}
