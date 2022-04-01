package com.dunkware.xstream.data.capture.signal;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.dtime.DTimeZone;

public class MongoSignalCaptureTest {
	
public static void main(String[] args) {
		
		try {
			MongoSignalCaptureInput input = new MongoSignalCaptureInput();
			DKafkaConsumerSpec2 spec  = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Earliest).
					setTopicString("stream_us_equity_220209_signals").setClientAndGroup("jjkj4kkjkj", "jj4jkjkkk").
					setBrokerString("172.16.16.55:31090").build();
			
			input.setKafkaSpec(spec);
			input.setMongoCollection("stream_signals_us_equity_220209");
			input.setMongoDatabase("staging");
			input.setCaptureId("test");
			input.setDebugLogging(true);
			input.setMongoURL("mongodb://root:password@172.16.16.55:32700");
			//input.setMongoURL("mongodb://root:rootpassword@localhost:27017");
			input.setSignalQueueSizeLimit(2000);
			input.setTimeZone(DTimeZone.NewYork);
			input.setStream("us_equity");
			
			MongoSignalCapture capture = new MongoSignalCapture();
			capture.start(input);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	
		
		

		
		
	}

}
