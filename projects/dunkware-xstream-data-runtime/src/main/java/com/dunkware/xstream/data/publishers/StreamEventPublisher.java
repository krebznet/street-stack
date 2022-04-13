package com.dunkware.xstream.data.publishers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;

public class StreamEventPublisher {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static StreamEventPublisher newInstance(String kafkaBrokers, String kafkaTopic, String identifier) throws Exception { 
		return new StreamEventPublisher(kafkaBrokers, kafkaTopic, identifier);
	}
	
	private volatile long sessionStartCount;
	private volatile long sessionStopCount; 
	private volatile long signalCount; 
	private volatile long snapshotCount; 
	private volatile long eventCount; 
	
	private DKafkaByteProducer kafkaProducer;
	
	private BlockingQueue<GStreamEvent> eventQueue = new LinkedBlockingQueue<GStreamEvent>();
	
	private Publisher publisher;
	
	private String brokers; 
	private String topic; 
	private String identifier; 
	
	private StreamEventPublisher(String brokers, String topic, String identifier) throws Exception { 
		this.brokers = brokers;
		this.topic = topic;
		this.identifier = identifier;
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(brokers, topic, identifier);	
		} catch (Exception e) {
			throw new Exception("Exception creating stream event publisher kafka publisher " + e.toString());
		}
		publisher = new Publisher();
		publisher.start();
	}
	
	public void sendEvent(GStreamEvent event) throws Exception { 
		try {
			eventQueue.add(event);
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}
	
	public StreamEventPublisherStats getStats() { 
		StreamEventPublisherStats stats = new StreamEventPublisherStats();
		stats.setBrokers(brokers);
		stats.setEventCount(eventCount);
		stats.setIdentifier(identifier);
		stats.setSnapshotCount(snapshotCount);
		stats.setSignalCount(signalCount);
		stats.setSessionStartCount(sessionStartCount);
		stats.setSessionStopCount(sessionStopCount);
		return stats;
	}
	
	public void dispose() { 
		int sleepcount = 0;
		while(eventQueue.isEmpty() == false) { 
			try {
				Thread.sleep(500);
				sleepcount++;
				if(sleepcount > 20) { 
					logger.error("Disposing Stream Event Publisher After 20 seconds with non empty q size " + eventQueue.size());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		kafkaProducer.dispose();
		publisher.interrupt();
	}
	
	private class Publisher extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					GStreamEvent event = eventQueue.take();
					eventCount++;
					if(event.getType() == GStreamEventType.EntitySignal) { 
						signalCount++;
					}
					if(event.getType() == GStreamEventType.EntitySnapshot) {
						snapshotCount++;
					}
					if(event.getType() == GStreamEventType.SessionStart) { 
						sessionStartCount++;
					}
					if(event.getType() == GStreamEventType.SessionStop) { 
						sessionStopCount++;
					}
					kafkaProducer.sendBytes(event.toByteArray());
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception Sending Stream Event In Publisher Thread " + e.toString());
				}
				
			}
		}
	}

}
