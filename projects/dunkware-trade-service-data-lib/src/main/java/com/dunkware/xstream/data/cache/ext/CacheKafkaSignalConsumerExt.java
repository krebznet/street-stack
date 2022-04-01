package com.dunkware.xstream.data.cache.ext;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaConsumerSpec2Builder;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.data.cache.CacheException;
import com.dunkware.xstream.data.cache.CacheExtension;
import com.dunkware.xstream.data.cache.CacheExtensionType;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheValueSet;
import com.dunkware.xstream.data.cache.anot.ACacheExtension;
import com.dunkware.xstream.data.cache.util.CacheEventHelper;

@ACacheExtension(type = CacheKafkaSignalConsumerExtType.class)
public class CacheKafkaSignalConsumerExt implements CacheExtension, DKafkaByteHandler2 {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheStream cache; 
	private CacheKafkaSignalConsumerExtType type;
	
	private DKafkaByteConsumer2 consumer;
	
	private AtomicLong signalCount = new AtomicLong();
	
	private BlockingQueue<GEntitySignal> snapshotQueue = new LinkedBlockingQueue<GEntitySignal>();
	
	private SnapshotPublisher publisher;
	
	
	@Override
	public void init(CacheStream stash, CacheExtensionType type) throws CacheException {
		this.cache = stash; 
		this.type = (CacheKafkaSignalConsumerExtType)type;
		DKafkaConsumerSpec2 spec = null;
		try {
			spec = DKafkaConsumerSpec2Builder.newBuilder(ConsumerType.AllPartitions, OffsetType.Earliest).addBroker(this.type.getKafkaBrokers())
			.addTopic(this.type.getKafkaTopic()).setClientAndGroup("ss", "dfsd").build();
		} catch (Exception e) {
			throw new CacheException("Exception building cache kafka snapshot consumer spec " + e.toString(),e);
		}
		try {
			consumer = DKafkaByteConsumer2.newInstance(spec);
			consumer.addStreamHandler(this);
			
		} catch (Exception e) {
			throw new CacheException("Exception starting cache kafka consumer consumer " + e.toString(),e);
		}
		
		
		
	}
	
	public long getSignalCount() { 
		return signalCount.get();
	}

	@Override
	public void stashStarting(CacheStream stash) throws CacheException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stashStarted(CacheStream stash) throws CacheException {
	
		
	}

	@Override
	public void stashDisposing(CacheStream stash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stashDisposed(CacheStream stash) {
		consumer.dispose();
		this.publisher.interrupt();
		
	}
	
	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GStreamEvent event = GStreamEvent.parseFrom(record.value());
			if(event.getType() != GStreamEventType.EntitySignal) { 
				logger.error(MarkerFactory.getMarker("Data"), "snapshot consumer not getting snopshot event " + event.getType().name() );
			} else { 
				snapshotQueue.put(event.getEntitySignal());
			}
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Data"),"Exception parsing GStreamEvent in Kafka Cache Snapshot Consumer " 	+ e.toString());
		}
		
	}
	
	  class SnapshotPublisher extends Thread { 
			
			public void run() { 
				while(!interrupted()) { 
					try {
						GEntitySignal snapshot = snapshotQueue.take();
						// okay we need time 
						LocalDateTime dt = DProtoHelper.toLocalDateTime(snapshot.getTime(), type.getTimeZone());
						try {
							CacheValueSet valueSet = CacheEventHelper.varsToValueSet(snapshot.getVarsList());
							cache.insertSignal(snapshot.getId(), snapshot.getIdentifier(), snapshot.getEntityId(), snapshot.getEntityIdentifier(),  dt, valueSet);
							signalCount.incrementAndGet();
						} catch (Exception e) {
							logger.error(MarkerFactory.getMarker("Data"), "Building cache value set from snapshot failed " + e.toString());
							continue;
						}
						
						
						
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error(MarkerFactory.getMarker("Data"), "outer exception in snapshot cache consumer publixher " + e.toString());;
					}
				}
			}
		}

	

}
