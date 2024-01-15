package com.dunkware.trade.service.stream.ingest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.trade.service.stream.ingest.anot.AStreamIngestor;
import com.dunkware.xstream.model.snapshot.SnapshotValue;

@Service()
@Profile("StreamIngest")
public class StreamIngestService implements DKafkaByteHandler2   {
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private DunkNet dunkNet;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamInjestService");
	
	private List<StreamIngestor> ingestors = new ArrayList<StreamIngestor>();
	private BlockingQueue<byte[]> decodeQueue = new LinkedBlockingQueue<byte[]>();
	private BlockingQueue<SnapshotValue> snapshotQueue = new LinkedBlockingQueue<SnapshotValue>();
	
	private AtomicLong snapshotsTodayCount = new AtomicLong(0);
	private DKafkaByteConsumer2 kafkaConsumer; 
	
	private List<ByteDecoder> byteDecoders = new ArrayList<ByteDecoder>();
	private List<SnapshotRouter> snapshotRouters = new ArrayList<StreamIngestService.SnapshotRouter>();
	
	@PostConstruct
	private void init() { 
		try {
			dunkNet.extensions().addExtension(this);	
		} catch (Exception e) {
			logger.error(marker, "Exception adding DunkNet Extensions " + e.toString());
		}
		Set<Class<?>> classes = DAnotHelper.getClassesAnnotedWith(AStreamIngestor.class);
		for (Class<?> class1 : classes) {
			try {
				StreamIngestor ingestor = (StreamIngestor)class1.newInstance();
				ac.getAutowireCapableBeanFactory().autowireBean(ingestor);
				ingestor.start(this);
				ingestors.add(ingestor);
			} catch (Exception e) {
				logger.error(marker, "Exception starting StreamInjestor type {} error {}",class1.getName(),e.toString(),e);
			}
		}
		
		String brokers = dunkNet.getConfig().getServerBrokers();
		String consumerId = "injest_" + dunkNet.getId().toLowerCase();
		String groupId = "StreamInjestors";
		DKafkaByteConsumer2Spec consumerSpec = null;
		try {
			consumerSpec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(brokers).addTopic("stream_queue_snapshot").setClientAndGroup(consumerId, groupId).build();	
		} catch (Exception e) {
			logger.error(marker, "StreamInjest consumerSpec exception service not online " + e.toString());
			return;
		}
		try {
			kafkaConsumer = DKafkaByteConsumer2.newInstance(consumerSpec);
		} catch (Exception e) {
			logger.error("Exception starting kafka consumer " + e.toString());
			return;
		}
		
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		decodeQueue.add(record.value());
	}
	
	
	private class ByteDecoder extends Thread { 
		
		public void run() { 
			
			while(!interrupted()) { 
				try {
					byte[] bytes = decodeQueue.take();
					SnapshotValue value = DJson.getObjectMapper().readValue(bytes, SnapshotValue.class);
					snapshotQueue.add(value);
				} catch (Exception e) {
					logger.error("Byte Decoder Exception " + e.toString());
				}
				
			}
		}
	}
	
	
	private class SnapshotRouter extends Thread { 
		
		public void run() { 
			while(!interrupted()) {
				try {
					SnapshotValue value = snapshotQueue.take();
					for (StreamIngestor injestor : ingestors) {
						injestor.consume(value);
					}
					snapshotsTodayCount.incrementAndGet();
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Routing Snapsshot " + e.toString());
				}
				
			}
		}
	}
	
	
	
	
	
	
	// consumer / consumer group
	
	

	
	

}
