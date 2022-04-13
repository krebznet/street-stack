package com.dunkware.trade.service.data.service.stream.writers;

import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.dtime.DTimeZone;

public class DataStreamSnapshotWriterTest {
	
	public static void main(String[] args) {
		
		try {
			DataStreamSnapshotWriterInput input = new DataStreamSnapshotWriterInput();
			DKafkaConsumerSpec2 spec  = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Latest).
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
			
			DataStreamSnapshotWriter capture = new DataStreamSnapshotWriter();
			capture.start(input);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	
		
		

		
		
	}

}
