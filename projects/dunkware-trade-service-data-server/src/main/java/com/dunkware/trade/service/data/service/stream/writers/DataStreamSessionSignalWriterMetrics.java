package com.dunkware.trade.service.data.service.stream.writers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionSnapshotWriterStats;

public class DataStreamSessionSignalWriterMetrics {
	
	private Map<String,SignalType> signalTypes = new ConcurrentHashMap<String,SignalType>();
	
	private DataStreamSessionSignalWriter writer;
	
	private int lastBatchWriteSize = 0;
	private double lastBatchWriteSpeed = 0;
	
	
	
	public DataStreamSessionSignalWriterMetrics() {
		
	}
	
	
	 
	public int getLastBatchWriteSize() {
		return lastBatchWriteSize;
	}

	public DataStreamSessionSnapshotWriterStats getStats() { 
		DataStreamSessionSnapshotWriterStats stats = new DataStreamSessionSnapshotWriterStats();
		
		return stats;
	}
	

	public double getLastBatchWriteSpeed() {
		return lastBatchWriteSpeed;
	}

	public void setLastBatchWriteSpeed(double lastBatchWriteSpeed) {
		this.lastBatchWriteSpeed = lastBatchWriteSpeed;
	}

	public void setLastBatchWriteSize(int lastBatchWriteSize) {
		this.lastBatchWriteSize = lastBatchWriteSize;
	}


	public void start(DataStreamSessionSignalWriter writer) {
		this.writer = writer;
		
	}
	
	public void stop() { 
		
	}
	
	public void signal(GEntitySignal signal) {
		SignalType type = signalTypes.get(signal.getIdentifier());
		if(type == null) { 
			type = new SignalType();
			type.identifier = signal.getIdentifier();
			type.id = signal.getId();
			
			signalTypes.put(signal.getIdentifier(), type);
		} 
		type.incrementCount();
	}
	
	
	public static class SignalType {
			public  String identifier;
			public  int id; 
			public  long count;
			
			public  void incrementCount() { 
				count++;
			}
	}

}
