package com.dunkware.trade.service.stream.server.controller.session.capture;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.stream.server.repository.StreamSessionEntEntity;

public class StreamSessionCaptureEnt {
	
	private StreamSessionCapture session;
	private StreamSessionEntEntity entity;
	private boolean dirty = false;
	private AtomicInteger snapshotCount = new AtomicInteger(0);
	private AtomicInteger signalCount = new AtomicInteger(0);
	public void start(StreamSessionCapture session, StreamSessionEntEntity entity) {
		this.session = session;
		this.entity = entity;
	}
	
	public void snapshot(GEntitySnapshot snapshot) {
		entity.setSnapshotCount(entity.getSnapshotCount() + 1);
		dirty = true; 
		snapshotCount.incrementAndGet();
	}
	
	public void signal(GEntitySignal signal) { 
		entity.setSignalCount(entity.getSignalCount() + 1);
		dirty = true;
		signalCount.incrementAndGet();
	}
	
	public StreamSessionEntEntity getEntity() { 
		return entity;
	}
	
	public int getSnapshotCount() { 
		return snapshotCount.get();
	}
	
	public int getSignalCount() { 
		return signalCount.get();
	}
	
	
	

}
