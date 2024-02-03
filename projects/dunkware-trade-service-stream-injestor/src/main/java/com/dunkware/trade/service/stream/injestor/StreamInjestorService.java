package com.dunkware.trade.service.stream.injestor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.snapshot.SnapshotValue;

@Service
@Profile("StreamInjestor")
public class StreamInjestorService implements DKafkaByteHandler2 {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamInjestorService");
	
	@Value("${stream.injestor.broker}")
	private String broker; 
	@Value("${stream.injestor.consumer}")
	private String consumer; 
	@Value("${stream.injestor.group")
	private String group;
	@Value("${stream.injestor.topic}")
	private String topic; 
	
	
	private DKafkaByteConsumer2 kafkaConsumer; 
	
	private BlockingQueue<SnapshotValue> queue = new LinkedBlockingQueue<SnapshotValue>();
	
	private Injestor injestor; 
	
	private boolean dispose = false; 
	
	@PostConstruct
	public void postConstruct() { 
		try {
			DKafkaByteConsumer2Spec spec = 
			DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(broker).
			addTopic(topic).setClientAndGroup(consumer, group).build();
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.addStreamHandler(this);
			kafkaConsumer.start();
			injestor = new Injestor();
			injestor.start();
		} catch (Exception e) {
			logger.error(marker, "Exception building kafka injestor service {}",e.toString());
		}
	}
	
	
	
	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			SnapshotValue value = DJson.getObjectMapper().readValue(record.value(), SnapshotValue.class);
			this.queue.add(value); 
		} catch (Exception e) {
			logger.error(marker, "Exception consuming SnapshotValue Record " + e.toString());
		}
	}





	/**
	 * This will injest 
	 * @author duncankrebs
	 *
	 */
	private class Injestor extends Thread { 
		
		public void run() { 
			
			
			while(!interrupted()) { 
				SnapshotValue value = null;
				try {
					value = queue.poll(5, TimeUnit.SECONDS); 
					if(value == null) { 
						if(dispose) { 
							return;
						}
						continue;
					}
				} catch (Exception e) {
					logger.error(marker, "Exception pulling snapshto from injextor" );
				}
			}
			
		}
	}

}
