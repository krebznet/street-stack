package com.dunkware.xstream.data.consumer.core;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamEvent;
import com.dunkware.net.proto.stream.GStreamEventType;
import com.dunkware.xstream.data.consumer.StreamEventConsumer;
import com.dunkware.xstream.data.consumer.StreamEventConsumerConfig;
import com.dunkware.xstream.data.consumer.StreamEventConsumerException;
import com.dunkware.xstream.data.consumer.StreamEventHandler;

public class KafkaStreamEventConsumer implements StreamEventConsumer, DKafkaByteHandler2 {
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private KafkaStreamEventConsumerConfig config;
	private List<StreamEventHandler> handlers = new ArrayList<StreamEventHandler>();
	private Semaphore handlerLock = new Semaphore(1);
	private DKafkaByteConsumer2 consumer;
	

	
	
	
	public void addEventHandler(StreamEventHandler handler) { 
		try {
			handlerLock.acquire();
			handlers.add(handler);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			handlerLock.release();
		}
	}
	
	
	public void removeEventHandler(StreamEventHandler handler) { 
		try {
			handlerLock.acquire();
			handlers.remove(handler);
		} catch (Exception e) {
			// TODO: handle exception
		} finally { 
			handlerLock.release();
		}
	}
	
	public void pauseConsumer() throws Exception { 
		consumer.pauseConsumer();
	}
	
	public void resumeConsumer() throws Exception { 
		consumer.resumeConsumer();
	}
	
	
	@Override
	public void start(StreamEventConsumerConfig config) throws StreamEventConsumerException {
		this.config = (KafkaStreamEventConsumerConfig)config;
		this.consumer = DKafkaByteConsumer2.newInstance(this.config.getConsumerSpec());
		try {
			consumer.start();
			consumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new StreamEventConsumerException("Exception starting kafka event consumer " + e.toString());
		}
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		GStreamEvent event = null;
		try {
			event = GStreamEvent.parseFrom(record.value());
		} catch (Exception e) {
			logger.error("Exception parsing event message " + e.toString());
		}
		try {
			
			if(event.getType() == GStreamEventType.EntitySignal) {
				if(logger.isTraceEnabled()) { 
					GEntitySignal sig = event.getEntitySignal();
					logger.trace("Signal {} Entity {} Time {} ",sig.getIdentifier(),sig.getEntityIdentifier(),
							DunkTime.format(DProtoHelper.toLocalDateTime(sig.getTime(), config.getTimeZone()),DunkTime.YYYY_MM_DD_HH_MM_SS));
				}
				handlerLock.acquire();
				GEntitySignal sig = event.getEntitySignal();
				for (StreamEventHandler streamEventHandler : handlers) {
					streamEventHandler.entitySignal(sig);;
				}
				handlerLock.release();
			}
			if(event.getType() == GStreamEventType.EntitySnapshot) {
				handlerLock.acquire();
				GEntitySnapshot snap = event.getEntitySnapshot();
				String snapshotTimestamp = DunkTime.format(DProtoHelper.toLocalDateTime(snap.getTime(), config.getTimeZone()),DunkTime.YYYY_MM_DD_HH_MM_SS);
				if(logger.isTraceEnabled()) { 
					logger.debug("Snapshot time {}",snapshotTimestamp);
				}

			
				for (StreamEventHandler streamEventHandler : handlers) {
					streamEventHandler.entitySnapshot(snap);
				}
				handlerLock.release();
			}
		} catch (Exception e) {
			if (e instanceof InterruptedException){
				return;
			}
			logger.error("Exception routing consumer record to stream event handlers " +e.toString(),e);
		}
	}
	
	
	
	private class StashUpdater implements StreamEventHandler {

		@Override
		public void entitySnapshot(GEntitySnapshot snapshot) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void entitySignal(GEntitySignal signal) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	

}
