package com.dunkware.xstream.net.core.container.ext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.container.ContainerExtType;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerExtension;
import com.dunkware.xstream.net.core.container.anot.ACacheExtension;

@ACacheExtension(type = ContainerKafkaStreamEventConsumerExtType.class)
public class ContainerKafkaStreamEventConsumerExt implements ContainerExtension, DKafkaByteHandler2 {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Container cache; 
	private ContainerKafkaStreamEventConsumerExtType type;
	
	private DKafkaByteConsumer2 consumer;
	
	private AtomicLong signalCount = new AtomicLong();
	
	private BlockingQueue<GStreamEvent> snapshotQueue = new LinkedBlockingQueue<GStreamEvent>();
	
	private EventHandler eventHandler;
	
	
	@Override
	public void init(Container stash, ContainerExtType type) throws ContainerException {
		this.cache = stash; 
		this.type = (ContainerKafkaStreamEventConsumerExtType)type;
		DKafkaByteConsumer2Spec spec = null;
		try {
			spec = DKafkaByteConsumer2SpecBuilder.newBuilder(ConsumerType.AllPartitions, OffsetType.Earliest).addBroker(this.type.getKafkaBrokers())
			.addTopic(this.type.getKafkaTopic()).setClientAndGroup("ss", "dfsd").build();
		} catch (Exception e) {
			throw new ContainerException("Exception building cache kafka snapshot consumer spec " + e.toString(),e);
		}
		try {
			consumer = DKafkaByteConsumer2.newInstance(spec);
			consumer.addStreamHandler(this);
			
		} catch (Exception e) {
			throw new ContainerException("Exception starting cache kafka consumer consumer " + e.toString(),e);
		}
		
		
		
	}
	
	public long getSignalCount() { 
		return signalCount.get();
	}

	@Override
	public void containerStarting(Container container) throws ContainerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerStarted(Container container) throws ContainerException {
	
		
	}

	@Override
	public void containerDisposing(Container container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void containerDisposed(Container stash) {
		consumer.dispose();
		this.eventHandler.interrupt();
		
	}
	
	
	
	@Override
	public void newSession(Container container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			GStreamEvent event = GStreamEvent.parseFrom(record.value());
			snapshotQueue.add(event);
		} catch (Exception e) {
			logger.error(MarkerFactory.getMarker("Data"),"Exception parsing GStreamEvent in Kafka Cache Snapshot Consumer " 	+ e.toString());
		}
		
	}
	
	  class EventHandler extends Thread { 
			
			public void run() { 
				while(!interrupted()) { 
					try {
						GStreamEvent event = snapshotQueue.take();
						// okay we need time 
						if(event.getType() == GStreamEventType.EntitySignal) {
							cache.consumeStreamSignal(event.getEntitySignal());
						}
						if(event.getType() == GStreamEventType.EntitySnapshot) { 
							cache.consumeStreamSnapshot(event.getEntitySnapshot());
						}
						if(event.getType() == GStreamEventType.TimeUpdate) { 
							cache.consumeStreamTime(event.getTimeUpdate());;
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
