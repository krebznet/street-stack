package com.dunkware.trade.service.data.service.stream;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.service.repository.DataStreamSessionInstrumentEntity;

public class DataStreamSessionInstrument {
	
	private DataStreamSession session;
	private DataStreamSessionInstrumentEntity entity;
	private boolean dirty = false;
	
	public void start(DataStreamSession session, DataStreamSessionInstrumentEntity entity) {
		this.session = session;
		this.entity = entity;
	}
	
	public void snapshot(GEntitySnapshot snapshot) {
		entity.setSnapshotCount(entity.getSnapshotCount() + 1);
		dirty = true; 
	}
	
	public void signal(GEntitySignal signal) { 
		entity.setSignalCount(entity.getSignalCount() + 1);
		dirty = true;
	}
	
	public DataStreamSessionInstrumentEntity getEntity() { 
		return entity;
	}
	
	
	
	

}
