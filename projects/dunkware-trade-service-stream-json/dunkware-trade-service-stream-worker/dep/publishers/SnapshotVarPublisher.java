package com.dunkware.trade.service.stream.worker.session.publishers;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamListener;
import com.dunkware.xstream.model.snapshot.SnapshotVariable;

//@AStreamWorkerExtension
public class SnapshotVarPublisher implements StreamWorkerExtension, XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("SnapshotVarPublisher");


	private BlockingQueue<SnapshotVariable> publishQueue = new LinkedBlockingQueue<SnapshotVariable>();

	private StreamWorker worker;
	

	private DKafkaByteProducer producer;
	
	
	private AtomicLong snapshotCounter = new AtomicLong(0);
	
	private volatile boolean disposed = false; 
	
	private Map<Integer,RowVarSnapshotCollector> snapshotCollecotrs = new ConcurrentHashMap<Integer,RowVarSnapshotCollector>();
	

	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		String brokers = worker.getStreamDescriptor().getKafkaBrokers();
		String topicName = "system_stream_snapshot_var";
		producer = DKafkaByteProducer.newInstance(brokers,topicName,worker.getStartReq().getWorkerId());
		
	}

	@Override
	public void start() throws Exception {
		worker.getStream().addStreamListener(this);
		
	}

	@Override
	public void stop() throws Exception {
		disposed = true;
		for (RowVarSnapshotCollector collector : snapshotCollecotrs.values()) {
			worker.getStream().getClock().unscheduleRunnable(collector);
		}
		producer.dispose();
	}
	
	
	
	// implement a runanble for each row;
	@Override
	public void rowInsert(XStreamEntity row) {
		// new row
		if(snapshotCollecotrs.get(row.getIdentifier()) == null) {
			// create a snapshto collector schedule it to run every 1 second
			RowVarSnapshotCollector collector = new RowVarSnapshotCollector(row);
			row.getStream().getClock().scheduleRunnable(collector, 1);
			snapshotCollecotrs.put(row.getIdentifier(), collector);
			logger.info(marker, "Adding Row Var Snapshot Collector for " + row.getId());
		}
		
	} 



	public long getSnapshotCount() { 
		return snapshotCounter.get();
	}



	private class RowVarSnapshotCollector implements Runnable { 
		
		XStreamEntity entity; 
		
		public RowVarSnapshotCollector(XStreamEntity entity) { 
			this.entity = entity; 
			
		}
		
		
		
		/**
		 * Oay every 1 second, we should have a common kafka publisher 
		 * we should create the topic upfront with a fixed number of partiiosn. 
		 */
		public void run() { 
			long timestamp = entity.getStream().getClock().getTimestamp();
			Map<Integer,Number> snapshots = entity.numericVarSnapshot();
			for (Integer varid : snapshots.keySet()) {
				SnapshotVariable in = new SnapshotVariable();
				in.setEntity(entity.getIdentifier());
				in.setStream(((int)entity.getStream().getInput().getId()));
				in.setTimestamp(timestamp);
				Number numberValue = snapshots.get(varid);
				in.setValue(numberValue.doubleValue());
				in.setVar(varid);
				in.setTimestamp(timestamp);
				try {
					byte[] bytes = DJson.serialize(in).getBytes();
					producer.sendBytes(bytes);
					snapshotCounter.incrementAndGet();
				} catch (Exception e) {
					logger.error(marker,"Exception sending SnapshotVariable " + e.toString());
				}
			}
			
		}
		
	}

	

}
