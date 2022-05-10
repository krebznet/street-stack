package com.dunkware.trade.service.data.service.stream.session.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DProtoHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamSessionStop;
import com.dunkware.trade.service.data.json.stream.writer.DataStreamSnapshotWriterSessionStats2;
import com.google.common.util.concurrent.AtomicDouble;

public class DataStreamSessionSnapshotWriterMetrics2 {

	private Map<Integer, AtomicInteger> entitySnapshots = new ConcurrentHashMap<Integer, AtomicInteger>();
	private AtomicLong snapshots = new AtomicLong();
	private int lastInsertSize = 0; 
	private double lastInsertSeconds;
	private GEntitySnapshot lastInsertEntity = null;
	private AtomicInteger insertCount = new AtomicInteger(0);
	private AtomicInteger consumeCount = new AtomicInteger(0);
	private AtomicDouble pauseTime = new AtomicDouble();
	private DStopWatch pauseStopWatch = DStopWatch.create();
	private int pauseCount;
	private List<String> errors = new ArrayList<String>();

	private DDateTime startTime;
	private DataStreamSessionSnapshotWriter2 writer;

	private DDateTime stopTime; 
	
	public void start(DataStreamSessionSnapshotWriter2 writer) {
		this.writer = writer;
		startTime = DDateTime.now(writer.getSession().getStream().getTimeZone());

	}

	public void error(String error) {
		this.errors.add(error);
	}

	public void snapshotConsume(GEntitySnapshot snapshot) {
		this.consumeCount.incrementAndGet();

	}

	public void sessionStopEvent(GStreamSessionStop stop) {

	}

	public void snapshotInsert(List<GEntitySnapshot> snapshots, int size, double seconds) {
		this.insertCount.addAndGet(snapshots.size());
		for (GEntitySnapshot gEntitySnapshot : snapshots) {
			if(entitySnapshots.get(Integer.valueOf(gEntitySnapshot.getId())) == null) { 
				entitySnapshots.put(Integer.valueOf(gEntitySnapshot.getId()), new AtomicInteger(1));
				
			} else { 
				entitySnapshots.get(Integer.valueOf(gEntitySnapshot.getId())).incrementAndGet();
			}
		}
	}

	public void consumerPaused() {
		pauseCount++;
		pauseStopWatch.start();
	}

	public DDateTime getStopTime() {
		return stopTime;
	}

	public void setStopTime(DDateTime stopTime) {
		this.stopTime = stopTime;
	}

	public DataStreamSnapshotWriterSessionStats2 getStats() {
		
		DataStreamSnapshotWriterSessionStats2 stats = new DataStreamSnapshotWriterSessionStats2();
		stats.setEntitiyCount(this.entitySnapshots.size());
		stats.setInserteCount(this.insertCount.get());;
		stats.setConsumCount(this.consumeCount.get());
		stats.setConsumerPauseCount(pauseCount);
		stats.setConsumerPauseTime(pauseTime.get());
		if(lastInsertEntity != null) {
			// lastWriteTime
			stats.setLastWriteSize(lastInsertSize);
			stats.setLastWriteSeconds(lastInsertSeconds);
			stats.setLastWriteTime(DDateTime.from(DProtoHelper.toLocalDateTime(lastInsertEntity.getTime(), 
					writer.getSession().getStream().getTimeZone())));
		}
		stats.setStartTime(this.startTime);
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

	

}
