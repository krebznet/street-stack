package com.dunkware.trade.service.stream.ingest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.stream.cluster.proto.injest.StreamIngestorStats;
import com.dunkware.xstream.model.snapshot.SnapshotValue;

public abstract class StreamIngestor {

	protected BlockingQueue<SnapshotValue> snapshotQueue = new LinkedBlockingQueue<SnapshotValue>();
	protected AtomicLong writeCount = new AtomicLong(0);
	protected AtomicInteger writeExceptions = new AtomicInteger(0);
	protected StreamIngestService injestService;
	
	public StreamIngestorStats getStats() { 
		StreamIngestorStats stats = new StreamIngestorStats();
		stats.setName(getName());
		stats.setQueueSize(snapshotQueue.size());
		stats.setWriteCount(writeCount.get());
		stats.setWriteExceptions(writeExceptions.get());
		return stats;
	}
	
	public void start(StreamIngestService injestService) throws Exception { 
		this.injestService = injestService;
		doStart();
	}
	
	public abstract void doStart() throws Exception;
	
	public abstract String getName();
	
	
	public void injest(SnapshotValue value) { 
		this.snapshotQueue.add(value);
	}
	
	public void consume(SnapshotValue value) { 
		snapshotQueue.add(value);
	}
	
	
	/**
	 * Tells it to write until no items left in the queue 
	 * 
	 * @throws Exception
	 */
	public abstract void dispose() throws Exception; 
	
	public abstract boolean disposed();
	
	
	
}
