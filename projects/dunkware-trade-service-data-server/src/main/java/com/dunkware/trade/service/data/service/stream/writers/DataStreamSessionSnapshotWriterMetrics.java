package com.dunkware.trade.service.data.service.stream.writers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.core.runtime.core.helpers.GProtoHelper;
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
	
	private DataStreamSessionSnapshotWriter writer;
	
	public DataStreamSessionSnapshotWriterMetrics(DataStreamSessionSnapshotWriter writer) {
		
	}
	
	public void start(DataStreamSessionSnapshotWriter writer) { 
		this.writer = writer;
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
	
	public void bucketWrite(DataStreamSessionSnapshotWriterBucket bucket) { 
		if(entityBucketCounts.get(bucket.getIdentifier()) == null ) { 
			entityBucketCounts.put(bucket.getIdentifier(), new AtomicLong(1));
		} else { 
			entityBucketCounts.get(bucket.getIdentifier()).incrementAndGet();
		}
	}
	
	public void sessionStopEvent(GStreamSessionStop stop) {
		
	}
	
	public void bucketWriteBatch(int size, double speed, DataStreamSessionSnapshotWriterBucket lastBucket) {
		this.lastWriteBucket = lastBucket;
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
	
	
}
