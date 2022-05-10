package com.dunkware.trade.service.data.service.stream;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.service.repository.DataStreamInstrumentEntity;
import com.dunkware.trade.service.data.service.stream.session.DataStreamSession;

public class DataStreamSessionInstrument {
	
	private DataStreamSession session;
	private DataStreamInstrumentEntity entity;
	private boolean dirty = false;
	private AtomicInteger snapshotCount = new AtomicInteger(0);
	private AtomicInteger signalCount = new AtomicInteger(0);
	public void start(DataStreamSession session, DataStreamInstrumentEntity entity) {
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
	
	public DataStreamInstrumentEntity getEntity() { 
		return entity;
	}
	
	public int getSnapshotCount() { 
		return snapshotCount.get();
	}
	
	public int getSignalCount() { 
		return signalCount.get();
	}
	
	
	

}
