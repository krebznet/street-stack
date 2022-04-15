package com.dunkware.trade.service.data.service.stream.writers;

import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.google.common.util.concurrent.AtomicDouble;

public class DataStreamSessionSnapshotWriterMetrics {
	
	private AtomicLong snapshotCount = new AtomicLong();
	private GStreamSessionStop stopEvent = null;
	
	private double lastBucketWriteSpeed = 0;
	private double lastBucketWriteSize = 0;
	
	private AtomicDouble pauseTime = new AtomicDouble();
	private DStopWatch pauseStopWatch = DStopWatch.create();
	
	private int pauseCount; 
	
	public DataStreamSessionSnapshotWriterMetrics(DataStreamSessionSnapshotWriter writer) {
		
	}

	public void snapshotConsumed(GEntitySnapshot snapshot) { 
		snapshotCount.incrementAndGet();
	}
	
	public void sessionStopEvent(GStreamSessionStop stop) {
		
	}
	
	public void bucketWriteBatch(int size, double speed) { 
		lastBucketWriteSpeed = speed;
	}
	
	public void consumerPaused() { 
		pauseCount++;
		pauseStopWatch.start();
		
		
	}
	
	public void consumerResumed() { 
		pauseStopWatch.stop();
		pauseTime.addAndGet(pauseStopWatch.getCompletedSeconds());
	}
	
	public int getPauseCount() { 
		return pauseCount;
	}
	
	public double getPauseTime() { 
		return pauseTime.get();
	}
}
