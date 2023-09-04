package com.dunkware.trade.net.data.server.stream.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.trade.service.data.model.domain.EntitySnapshot;
import com.google.common.util.concurrent.AtomicDouble;

public class SnapshotWriterMetrics {
	
	private Map<Integer, AtomicInteger> entitySnapshots = new ConcurrentHashMap<Integer, AtomicInteger>();
	private AtomicLong snapshots = new AtomicLong();
	private int lastInsertSize = 0; 
	private double lastInsertSeconds;
	private EntitySnapshot lastInsertEntity = null;
	private AtomicInteger insertCount = new AtomicInteger(0);
	private AtomicInteger consumeCount = new AtomicInteger(0);
	private AtomicDouble pauseTime = new AtomicDouble();
	private DStopWatch pauseStopWatch = DStopWatch.create();
	private int pauseCount;
	private List<String> errors = new ArrayList<String>();

	private DDateTime startTime;
	private SnapshotWriter writer;

	private DDateTime stopTime; 
	
	public void start(SnapshotWriter writer) {
		this.writer = writer;
		startTime = DDateTime.now(writer.getController().getDescriptor().getTimeZone());

	}

	public void error(String error) {
		this.errors.add(error);
	}

	public void snapshotConsume(EntitySnapshot snapshot) {
		this.consumeCount.incrementAndGet();

	}

	
	public void snapshotInsert(List<EntitySnapshot> snapshots, int size, double seconds) {
		this.insertCount.addAndGet(snapshots.size());
		for (EntitySnapshot snapshot : snapshots) {
			if(entitySnapshots.get(snapshot.getEntityId()) == null) {
				entitySnapshots.put(snapshot.getEntityId(), new AtomicInteger(1));
				
			} else { 
				entitySnapshots.get(snapshot.getEntityId()).incrementAndGet();
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
