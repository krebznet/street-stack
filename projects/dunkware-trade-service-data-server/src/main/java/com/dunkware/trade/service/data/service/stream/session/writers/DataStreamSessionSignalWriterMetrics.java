package com.dunkware.trade.service.data.service.stream.session.writers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSignalWriterSessionStats;
import com.dunkware.xstream.xScript.SignalType;

public class DataStreamSessionSignalWriterMetrics {
	
	private Map<String,SignalType> signalCountMap = new ConcurrentHashMap<String,SignalType>();
	private int signalConsumeCount; 
	private int signalWriteCount;
	
	private DataStreamSessionSignalWriter writer;
	
	private int lastBatchWriteSize = 0;
	private double lastBatchWriteSpeed = 0;
	
	private GEntitySignal lastWriteSignal;
	
	public DataStreamSessionSignalWriterMetrics() {
		
	}
	
	public void signalConsumed(GEntitySignal signal) { 
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
			LocalDateTime lastTime = DProtoHelper.toLocalDateTime(lastWriteSignal.getTime(), writer.getSession().getStream().getTimeZone());
			stats.setLastSignalWriteTime(DDateTime.from(lastTime));
		}
		return stats;
	}
	

	public double getLastBatchWriteSpeed() {
		return lastBatchWriteSpeed;
	}

	public void setLastBatchWrite(List<GEntitySignal> signals, int size, double speed) { 
		this.lastBatchWriteSize = size; 
		this.lastBatchWriteSpeed = speed; 
		signalWriteCount = signalWriteCount + signals.size();
		lastWriteSignal = signals.get(signals.size() - 1);
		
	}
	public void start(DataStreamSessionSignalWriter writer) {
		this.writer = writer;
		
	}
	
	public void stop() { 
		
	}
	
	

}
