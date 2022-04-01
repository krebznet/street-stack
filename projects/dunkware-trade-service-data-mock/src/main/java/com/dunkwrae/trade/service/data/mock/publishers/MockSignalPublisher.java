package com.dunkwrae.trade.service.data.mock.publishers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSession;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionEntity;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignal;
import com.dunkwrae.trade.service.data.mock.util.MockMarker;
import com.dunkwrae.trade.service.data.mock.util.MockProtoUtil;


public class MockSignalPublisher {

	// DKafkaPublisher;

	private String brokers;
	private String topic;
	private String sessionId;

	private DKafkaByteProducer signalProducer;
	
	private AtomicLong publishCount = new AtomicLong();
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<GStreamEvent> queue = new LinkedBlockingQueue<GStreamEvent>();

	
	private Publisher publisher; 
	
	public void start(String sessionId, String brokers, String topic) throws Exception {
		this.brokers = brokers;
		this.topic = topic;
		this.sessionId = sessionId;
		try {
			signalProducer = DKafkaByteProducer.newInstance(brokers, topic, sessionId);

		} catch (Exception e) {
			throw new Exception("Exception connecting mock signal kafka producer " + e.toString(), e);
		}

		publisher = new Publisher();
		publisher.start();
	}
	
	public long getPublishCount() { 
		return publishCount.get();
	}
	
	public void dispose() { 
		int loopCount = 0; 
		while(queue.isEmpty() == false) { 
			try {
				Thread.sleep(500);
				loopCount++;
				if(loopCount > 10) { 
					// if its greater than 5 seconds 
					logger.error(MockMarker.getMarker(), "Disposing Signal Publisher Disposing with " + queue.size() + " pending envents");
				}
			} catch (Exception e) {
			}
		}
		publisher.interrupt();
		signalProducer.dispose();
	}

	public void publish(MockSessionEntity entity, MockSessionSignal signal, MockSession session) {
		GStreamEvent event = MockProtoUtil.entitySignalEvent(entity, signal, session);
		try {
			queue.put(event);
		} catch (Exception e) {
			logger.error(MockMarker.getMarker(), "Exception putting signal event in publish q " + e.toString());
		}

	}
	
	
	private class Publisher extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					try {
						GStreamEvent event = queue.take();
						try {
							signalProducer.sendBytes(event.toByteArray());
							publishCount.incrementAndGet();
						} catch (Exception e) {
							if (e instanceof InterruptedException) {
								return;
							}
							logger.error(MockMarker.getMarker(), "Exception publishing signal " + e.toString(),e);
						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error(MockMarker.getMarker(), "Exception taking signal to publish " + e.toString(),e);
						
					}
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error(MockMarker.getMarker(), "Outer Signal Publisher Exception " + e.toString());
			}
		}
	}

}
