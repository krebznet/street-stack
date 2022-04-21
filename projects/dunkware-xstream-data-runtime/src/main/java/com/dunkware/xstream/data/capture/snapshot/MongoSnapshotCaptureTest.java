package com.dunkware.xstream.data.capture.snapshot;

import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.dtime.DTimeZone;

public class MongoSnapshotCaptureTest {
	
	public static void main(String[] args) {
		
		try {
			MongoSnapshotCaptureInput input = new MongoSnapshotCaptureInput();
			DKafkaByteConsumer2Spec spec  = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).
					setTopicString("stream_us_equity_220210_snapshots").setClientAndGroup("jjkjkkjkj", "jjjkjkkk").
					setBrokerString("172.16.16.55:31090").build();
			input.setBatchSize(200);
			input.setKafkaSpec(spec);
			input.setMongoCollection("snapshots");
			input.setMongoDatabase("dunkware");
			input.setCaptureId("test");
			input.setDebugLogging(true);
			//input.setMongoURL("mongodb://root:password@172.16.16.55:32700");
			input.setMongoURL("mongodb://root:rootpassword@localhost:27017");
			input.setWriteQueueSizeLimit(2000);
			input.setTimeZone(DTimeZone.NewYork);
			input.setStream("us_equity");
			
			MongoSnapshotCapture capture = new MongoSnapshotCapture();
			capture.start(input);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	
		
		

		
		
	}

}
