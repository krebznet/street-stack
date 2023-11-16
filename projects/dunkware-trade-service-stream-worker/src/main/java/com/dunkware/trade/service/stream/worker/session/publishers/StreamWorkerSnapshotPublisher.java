package com.dunkware.trade.service.stream.worker.session.publishers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamClock;
import com.dunkware.xstream.api.XStreamClockListener;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;


public class StreamWorkerSnapshotPublisher implements XStreamClockListener  {
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSnapshotPublisher");
	
	
	private BlockingQueue<StreamEntitySnapshot> snapshots = new LinkedBlockingQueue<StreamEntitySnapshot>();
	
	private StreamWorker channel; 
	private XStream stream;
	
	private SnapshotKafkaPublisher publisher;
	
	
	private DKafkaByteProducer producer;
	
	public void init(StreamWorker channel) throws Exception { 
		this.channel = channel;
		String brokers = channel.getStartReq().getKafkaBrokers();
		String topicName = "stream_us_equity_session_snapshot";
		producer = DKafkaByteProducer.newInstance(brokers,topicName,channel.getStartReq().getWorkerId());
	}
	
	public void streamStart(XStream stream) { 
		this.stream = stream;
		this.stream.getClock().addListener(this);
		publisher = new SnapshotKafkaPublisher();
		publisher.start();
	}
	
	public void streamStop() { 
		producer.dispose();
		publisher.interrupt();
	}

	@Override
	public void timeUpdate(XStreamClock clock, LocalDateTime time) {
		try {
			List<XStreamEntity> ents = stream.getRows();
			for (XStreamEntity ent : ents) {
				StreamEntitySnapshot snap = new StreamEntitySnapshot();
				//snap.setEntityId(ent.getIdentifier());
				snap.setDateTime(clock.getLocalDateTime());
				Map<Integer,Object> vars = new HashMap<Integer,Object>();
				for (XStreamEntityVar var :ent.getVars()) {
					if(var.getSize() == 0) { 
						
					} else { 
						vars.put(var.getVarType().getCode(), var.getValue(0));
					}
				}
				//snap.setVars(vars);
				snapshots.add(snap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private class SnapshotKafkaPublisher extends Thread { 
		
		public void run() { 
			try {
				while(!interrupted()) { 
					try {
						StreamEntitySnapshot snap = snapshots.take();
						byte[] bytes = DJson.serialize(snap).getBytes();
						producer.sendBytes(bytes);
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							return;
						}
						logger.error(marker, "Exception publishing snapshot {}",e.toString());
					}
					
				}
			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					return;
				}
				logger.error(marker, "Exception publishing snapshot {}",e.toString());
			}
			
			
		}
	}

	
}
