package com.dunkware.trade.service.data.service.stream.writers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.data.json.stream.session.DataStreamSessionSnapshotWriterStats;
import com.google.common.util.concurrent.AtomicDouble;

public class DataStreamSessionSnapshotWriterMetrics {
	
	private AtomicLong snapshotConsumeCount = new AtomicLong();
	private GEntitySnapshot lastConsumeSnapshot = null;
	private GEntitySnapshot lastWriteSnapshot = null; 
	private AtomicLong snapshotWriteCount;
	private AtomicLong snapshotWriteBucketCount;
	private GStreamSessionStop stopEvent = null;
	
	private double lastBucketWriteSpeed = 0;
	private double lastBucketWriteSize = 0;
	
	private Map<String,AtomicLong> entityBucketCounts = new ConcurrentHashMap<String,AtomicLong>();
	private Map<String,AtomicLong> entitySnapshotConsumed = new ConcurrentHashMap<String,AtomicLong>();
	private AtomicDouble pauseTime = new AtomicDouble();
	private DStopWatch pauseStopWatch = DStopWatch.create();
	
	private DataStreamSessionSnapshotWriterBucket lastWriteBucket;
	private int pauseCount; 
	
	private List<String> errors = new ArrayList<String>();
	
	private DataStreamSessionSnapshotWriter writer;
	
	
	
	public void start(DataStreamSessionSnapshotWriter writer) { 
		this.writer = writer;
	}
	
	public void error(String error) { 
		this.errors.add(error);
	}

	public void snapshotConsumed(GEntitySnapshot snapshot) { 
		snapshotConsumeCount.incrementAndGet();
		 lastConsumeSnapshot = snapshot;
		 if(entitySnapshotConsumed.get(snapshot.getIdentifier()) == null) { 
			 entitySnapshotConsumed.put(snapshot.getIdentifier(), new AtomicLong(1));
		 }
		 else { 
			 entitySnapshotConsumed.get(snapshot.getIdentifier()).incrementAndGet();
		 }
	}
	
	
	public void sessionStopEvent(GStreamSessionStop stop) {
		
	}
	
	public void bucketWriteBatch(int size, double speed ) {;
		lastBucketWriteSpeed = speed;
		lastBucketWriteSize = size;
	}
	
	public void consumerPaused() { 
		pauseCount++;
		pauseStopWatch.start();
	}
	
	
	
	public DataStreamSessionSnapshotWriterStats getStats() { 
		DataStreamSessionSnapshotWriterStats stats = new DataStreamSessionSnapshotWriterStats();
		stats.setPauseCount(pauseCount);
		stats.setPauseTime(pauseTime.get());
		stats.setSnapshotConsumeCount(snapshotConsumeCount.get());
		stats.setEntityCount(entitySnapshotConsumed.keySet().size());
		stats.setLastBucketWriteSpeed(lastBucketWriteSpeed);
		stats.setLastBucketWriteSpeed(lastBucketWriteSpeed);;
		stats.setLastBucketWriteSpeed(lastBucketWriteSpeed);
		stats.setMongoURL(writer.getMongoURL());
		stats.setMongoDatabase(writer.getMongoDatabase());
		stats.setMongoCollection(writer.getMongoCollection());
		//stats.setLastSnapshotWriteTime(DunkTime.formatHHMMSS(GProtoHelper.toCalendarDateTimeRange(lastConsumeSnapshot.getTime(), writer.getSession().getStream().getTimeZone())));
		return stats;
		//stats.set
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
	
	public int getErrorCount() { 
		return errors.size();
	}

	public AtomicLong getSnapshotConsumeCount() {
		return snapshotConsumeCount;
	}

	public GEntitySnapshot getLastConsumeSnapshot() {
		return lastConsumeSnapshot;
	}

	public GEntitySnapshot getLastWriteSnapshot() {
		return lastWriteSnapshot;
	}

	public AtomicLong getSnapshotWriteCount() {
		return snapshotWriteCount;
	}

	public AtomicLong getSnapshotWriteBucketCount() {
		return snapshotWriteBucketCount;
	}

	public double getLastBucketWriteSpeed() {
		return lastBucketWriteSpeed;
	}

	public double getLastBucketWriteSize() {
		return lastBucketWriteSize;
	}
	
	public LocalDateTime getLastSnapshotConsumeTime() { 
		if(lastConsumeSnapshot != null) { 
			return DProtoHelper.toLocalDateTime(lastConsumeSnapshot.getTime(), writer.getSession().getStream().getTimeZone());
		}
		return null;
	}

	public Map<String, AtomicLong> getEntityBucketCounts() {
		return entityBucketCounts;
	}

	public Map<String, AtomicLong> getEntitySnapshotConsumed() {
		return entitySnapshotConsumed;
	}

	public DataStreamSessionSnapshotWriterBucket getLastWriteBucket() {
		return lastWriteBucket;
	}
	
	public LocalDateTime lastBucketWriteTime() { 
		return DProtoHelper.toLocalDateTime(lastWriteSnapshot.getTime(), writer.getSession().getStream().getTimeZone());
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	
	
	
}
