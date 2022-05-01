package com.dunkware.trade.service.data.service.stream.session.writers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats;
import com.google.common.util.concurrent.AtomicDouble;

public class DataStreamSessionSnapshotWriterMetrics {

	private Map<String, Integer> entitySnapshotCounts = new ConcurrentHashMap<String, Integer>();
	private Map<String, Integer> entitySnapshotWrites = new ConcurrentHashMap<String, Integer>();

	private AtomicLong snapshotConsumeCount = new AtomicLong();
	private GEntitySnapshot lastConsumeSnapshot = null;
	private GEntitySnapshot lastWriteSnapshot = null;
	private long snapshotWriteCount = 0;
	private int snapshotWriteBucketCount;
	private GStreamSessionStop stopEvent = null;

	private double lastBucketWriteSpeed = 0;
	private int lastBucketWriteSize = 0;

	private Map<String, AtomicLong> entityBucketCounts = new ConcurrentHashMap<String, AtomicLong>();
	private Map<String, AtomicLong> entitySnapshotConsumed = new ConcurrentHashMap<String, AtomicLong>();
	private AtomicDouble pauseTime = new AtomicDouble();
	private DStopWatch pauseStopWatch = DStopWatch.create();

	private DataStreamSessionSnapshotWriterBucket lastWriteBucket;
	private int pauseCount;

	private List<String> errors = new ArrayList<String>();

	private DDateTime startTime;
	private DataStreamSessionSnapshotWriter writer;

	public void start(DataStreamSessionSnapshotWriter writer) {
		this.writer = writer;
		startTime = DDateTime.now(writer.getSession().getStream().getTimeZone());

	}

	public void error(String error) {
		this.errors.add(error);
	}

	public void snapshotConsumed(GEntitySnapshot snapshot) {
		snapshotConsumeCount.incrementAndGet();
		lastConsumeSnapshot = snapshot;
		if(entitySnapshotCounts.get(snapshot.getIdentifier()) == null ) { 
			entitySnapshotCounts.put(snapshot.getIdentifier(), 1);
		} else { 
			int count = entitySnapshotCounts.get(snapshot.getIdentifier());
			entitySnapshotCounts.put(snapshot.getIdentifier(), count + 1);
		}

	}

	public void sessionStopEvent(GStreamSessionStop stop) {

	}

	public void bucketWriteBatch(List<DataStreamSessionSnapshotWriterBucket> buckets, int size, double speed) {
		lastBucketWriteSpeed = speed;
		lastBucketWriteSize = size;
		for (DataStreamSessionSnapshotWriterBucket bucket : buckets) {
			for (GEntitySnapshot snapshot : bucket.getSnapshots()) {
				snapshotWriteCount++;
				if(entitySnapshotWrites.get(snapshot.getIdentifier()) == null) {
					entitySnapshotWrites.put(snapshot.getIdentifier(), 1);
				} else { 
					int count = entitySnapshotWrites.get(snapshot.getIdentifier());
					entitySnapshotWrites.put(snapshot.getIdentifier(), count + 1);
				}
			}
		}
	}

	public void consumerPaused() {
		pauseCount++;
		pauseStopWatch.start();
	}

	public DataStreamSnapshotWriterSessionStats getStats() {
		DataStreamSnapshotWriterSessionStats stats = new DataStreamSnapshotWriterSessionStats();
		stats.setConsumerPauseCount(pauseCount);
		stats.setConsumerPauseTime(pauseTime.get());
		stats.setSnapshotConsumeCount(snapshotConsumeCount.get());
		stats.setEntityCount(entitySnapshotConsumed.keySet().size());
		stats.setLastWriteDuration(lastBucketWriteSpeed);
		stats.setLastWriteSize(lastBucketWriteSize);
		stats.setBucketCount(snapshotWriteBucketCount);
		stats.setSnapshotWriteCount(snapshotWriteCount);
		if(lastWriteBucket != null) { 
			lastWriteBucket.getStop();
			stats.setLastWriteStreamTime(DDateTime.from(lastWriteBucket.getStop()));
		}
		stats.setEntityCount(entitySnapshotCounts.keySet().size());
		stats.setMongoCollection(writer.getMongoCollection());
		stats.setMongoDatabase(writer.getMongoDatabase());
		stats.setQueueSize(writer.getQueueSize());
		stats.setSnapshotWriteCount(snapshotWriteCount);
		stats.setStartTime(startTime);
		stats.setProblemCount(this.errors.size());
		stats.setLastWriteStreamTime(DDateTime.from(writer.getSession().getSessionTime()));
		LocalDateTime d = LocalDateTime.now();
		LocalDateTime s = LocalDateTime.now();
		if(lastBucketWriteTime() != null) {
		int seconds = (int) ChronoUnit.SECONDS.between(lastBucketWriteTime(), writer.getSession().getSessionTime());
		double minutes = seconds / 60;
		stats.setWriteLagTime(minutes);
		}
		return stats;
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

	public long getSnapshotWriteCount() {
		return snapshotWriteCount;
	}

	public long getSnapshotWriteBucketCount() {
		return snapshotWriteBucketCount;
	}

	public double getLastBucketWriteSpeed() {
		return lastBucketWriteSpeed;
	}

	public double getLastBucketWriteSize() {
		return lastBucketWriteSize;
	}

	public LocalDateTime getLastSnapshotConsumeTime() {
		if (lastConsumeSnapshot != null) {
			return DProtoHelper.toLocalDateTime(lastConsumeSnapshot.getTime(),
					writer.getSession().getStream().getTimeZone());
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
		if(lastWriteSnapshot == null) { 
			return null;
		}
		return DProtoHelper.toLocalDateTime(lastWriteSnapshot.getTime(), writer.getSession().getStream().getTimeZone());
	}

	public List<String> getErrors() {
		return errors;
	}
	
	public void bucketWrite(DataStreamSessionSnapshotWriterBucket bucket) { 
		
	}

}
