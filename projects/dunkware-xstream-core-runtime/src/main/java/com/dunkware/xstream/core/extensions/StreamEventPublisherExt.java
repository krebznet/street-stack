package com.dunkware.xstream.core.extensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamRowListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.util.XStreamEventHelper;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = StreamEventPublisherExtType.class)
public class StreamEventPublisherExt implements XStreamExtension, XStreamRowListener {

	private XStream stream; 
	private StreamEventPublisherExtType type;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private BlockingQueue<GStreamEvent> publishQueue = new LinkedBlockingQueue<GStreamEvent>();
	// listen for signals; 
	// need status here -- Signal Event Count - Snapshot Event Count - 
	
	private DKafkaByteProducer kafkaProducer; 
	private EventPublisher eventPublisher;
	private EntitySnapshotBuilder snapshotRunnable;
	
	private DKafkaByteProducer signalProducer; 
	
	private Map<String,List<XStreamRowSignal>> snapshotSignals = new ConcurrentHashMap<String,List<XStreamRowSignal>>();
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.stream = stream;
		this.type = (StreamEventPublisherExtType)type;
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(this.type.getKafkaBrokers(), this.type.getSnapshotTopic(), this.type.getKafkaIdentifier());
		} catch (Exception e) {
			logger.error("Exception Creating Kafka Producer From Brokers " + this.type.getKafkaBrokers() + e.toString(),e);
			throw new XStreamException("Stream Event Publisher Init Kafka Connection Failed " + e.toString());
		}
		try {
			signalProducer = DKafkaByteProducer.newInstance(this.type.getKafkaBrokers(), this.type.getSignalTopic(), this.type.getKafkaIdentifier());
		} catch (Exception e) {
			logger.error("Exception Creating Kafka Producer From Brokers " + this.type.getKafkaBrokers() + e.toString(),e);
			throw new XStreamException("Stream Event Publisher Init Kafka Connection Failed " + e.toString());
		}
		
	}

	@Override
	public void preStart() throws XStreamException {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void start() throws XStreamException {
		stream.addRowListener(this);
		eventPublisher = new EventPublisher();
		eventPublisher.start();
		snapshotRunnable = new EntitySnapshotBuilder();
		stream.getClock().scheduleRunnable(snapshotRunnable, 1);
	}

	@Override
	public void preDispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		int sleepCount = 0;
		while(!publishQueue.isEmpty() == false) { 
			try {
				Thread.sleep(1000);
				sleepCount++;
			} catch (Exception e) {
				// TODO: handle exceptiond
			}
			if(sleepCount > 30) { 
				logger.error("Disposing Stream Event Publisher Timed out on Event Publish Queeu, look at code");
				break;
			}
		}
		
		EventPublisher.interrupted();
		kafkaProducer.dispose();
		stream.getClock().unscheduleRunnable(snapshotRunnable);
		
		
	}

	@Override
	public void rowSignal(final XStreamRow row, final XStreamRowSignal signal) {
		// add it to our snapshot queue 
		if(snapshotSignals.get(row.getId()) == null) { 
			List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
			this.snapshotSignals.put(row.getId(), signals);
		} else { 
			List<XStreamRowSignal> signals = snapshotSignals.get(row.getId());
			signals.add(signal);
			snapshotSignals.put(row.getId(), signals);
			
		}
		stream.getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					GStreamEvent event = XStreamEventHelper.buildEntitySignalEvent(row, signal);
					signalProducer.sendBytes(event.toByteArray());
				} catch (Exception e) {
					logger.error("Exception creating and sending signal eventl " + e.toString());
					// TODO: handle exception
				}
				
			}
		});
		
	}
	
	
	
	public class EntitySnapshotBuilder implements Runnable { 
		public void run() { 
			try {
				for (XStreamRow row : stream.getRows()) {
					try {
						// so here #1 we need to pull signals of the queue 
						// update the schema to have signals 
						// then clear the queue 
						List<XStreamRowSignal> signals = snapshotSignals.remove(row.getId());
						if(signals == null) { 
							signals = new ArrayList<XStreamRowSignal>();
						}
						
						GStreamEvent event = XStreamEventHelper.buildEntitySnapshotEvent(row, DTimeZone.toZoneId(stream.getInput().getTimeZone()),signals);
						publishQueue.add(event);
					} catch (Exception e) {
						logger.error("Exception creating entity snapshot event " + e.toString());
						// TODO: handle exception
					}
				}
			} catch (Exception e) {
				logger.error("Exception Taking Row Snapshots " + e.toString());
			}
		}
	}

	
	
	public class EventPublisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				GStreamEvent event = null;
				try {
					 event = publishQueue.take();
				} catch (Exception e) {
					logger.error("Exception taking event from  " + e.toString());
					continue;
				}
				
				try {
					if(event.getType() == GStreamEventType.EntitySnapshot) {
						byte[] fuck = event.toByteArray();
						
						GStreamEvent des = GStreamEvent.parseFrom(fuck);
						kafkaProducer.sendBytes(event.toByteArray());
					}
				
				} catch (Exception e) {
					logger.error("Exception Publishing Stream Event " + e.toString());
				}
				
			}
		}
	}
	
	// needs a row signal map 
	// <entiyidentifier,List<signals>)
	
	
	

}
