package com.dunkware.trade.net.data.server.stream.ingestors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSignalWriterSessionStats;
import com.dunkware.trade.service.data.model.domain.EntitySignal;
import com.dunkware.xstream.xScript.SignalType;

public class EntitySignalIngestorMetrics {

	private Map<String,SignalType> signalCountMap = new ConcurrentHashMap<String,SignalType>();
	private int signalConsumeCount; 
	private int signalWriteCount;
	
	private EntitySignalIngestor writer;
	
	private int lastBatchWriteSize = 0;
	private double lastBatchWriteSpeed = 0;
	
	private EntitySignal lastWriteSignal;
	
	public EntitySignalIngestorMetrics() {
		
	}
	
	public void signalConsumed(EntitySignal signal) { 
		signalConsumeCount++;
	}
	
	 
	public int getLastBatchWriteSize() {
		return lastBatchWriteSize;
	}

	public DataStreamSignalWriterSessionStats getStats() { 
		DataStreamSignalWriterSessionStats stats = new DataStreamSignalWriterSessionStats();
		stats.setSignalWriteCount(signalWriteCount);
		stats.setSignalConsumeCount(signalConsumeCount);
		if(lastWriteSignal != null) { 
			LocalDateTime lastTime = lastWriteSignal.getTime();
			stats.setLastSignalWriteTime(DDateTime.from(lastTime));
		}
		return stats;
	}
	

	public double getLastBatchWriteSpeed() {
		return lastBatchWriteSpeed;
	}

	public void setLastBatchWrite(List<EntitySignal> signals, int size, double speed) { 
		this.lastBatchWriteSize = size; 
		this.lastBatchWriteSpeed = speed; 
		signalWriteCount = signalWriteCount + signals.size();
		lastWriteSignal = signals.get(signals.size() - 1);
		
	}
	public void start(EntitySignalIngestor writer) {
		this.writer = writer;
		
	}
	
	public void stop() { 
		
	}
	
}
