package com.dunkware.trade.service.stream.worker.session.container.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.net.proto.stream.GStreamTimeUpdate;

public class WorkerContainerMetrics {
	
	private AtomicInteger snapshotCount = new AtomicInteger(0);
	private Map<String,AtomicInteger> entitySnapshotCounts = new ConcurrentHashMap<String,AtomicInteger>();
	private AtomicInteger signalCount = new AtomicInteger(0);
	private AtomicInteger timeCount = new AtomicInteger(0);
	
	private GStreamTimeUpdate lastTime = null;
	private GEntitySnapshot lastSnapshot = null;
	
	private WorkerContainerImpl worker; 
	
	public WorkerContainerMetrics(WorkerContainerImpl container) { 
		this.worker = container;
	}
	
	public void entitySnapshot(GEntitySnapshot snapshot) { 
		lastSnapshot = lastSnapshot;
		snapshotCount.incrementAndGet();
		if(entitySnapshotCounts.get(snapshot.getIdentifier()) == null) { 
			AtomicInteger counter = new AtomicInteger(1);
			entitySnapshotCounts.put(snapshot.getIdentifier(), counter);
		} else { 
			entitySnapshotCounts.get(snapshot.getIdentifier()).incrementAndGet();
		}
	}
	
	public void timeUpdate(GStreamTimeUpdate update) {
		timeCount.incrementAndGet();
		lastTime = update; 
	}
	
	public void entitySignal(GEntitySignal signal) { 
		signalCount.incrementAndGet();
		
	}
	
	public WorkerContainerStats getStats() { 
		WorkerContainerStats stats = new WorkerContainerStats();
		stats.setEntityCount(entitySnapshotCounts.keySet().size());
		stats.setSignalCount(signalCount.get());
		stats.setSnapshotCount(snapshotCount.get());
		stats.setTimeCount(timeCount.get());
		if(lastTime != null) { 
			stats.setTime(DunkTime.toStringTimeStamp(DunkTime.toLocalDateTime(lastTime.getTime(), worker.getReq().getTimeZone())));	
		}
		if(lastSnapshot != null) { 
			stats.setLastSnapshot(DunkTime.toStringTimeStamp(DunkTime.toLocalDateTime(lastSnapshot.getTime(), worker.getReq().getTimeZone())));
		}
		return stats;
		
	}

}
