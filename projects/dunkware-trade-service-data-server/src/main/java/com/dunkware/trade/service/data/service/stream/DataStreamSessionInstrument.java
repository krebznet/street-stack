package com.dunkware.trade.service.data.service.stream;

import com.dunkware.net.proto.stream.GEntitySignal;
import com.dunkware.net.proto.stream.GEntitySnapshot;
import com.dunkware.trade.service.data.service.repository.DataStreamInstrumentEntity;
import com.dunkware.trade.service.data.service.stream.session.DataStreamSession;

public class DataStreamSessionInstrument {
	
	private DataStreamSession session;
	private DataStreamInstrumentEntity entity;
	private boolean dirty = false;
	
	public void start(DataStreamSession session, DataStreamInstrumentEntity entity) {
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
	
	public DataStreamInstrumentEntity getEntity() { 
		return entity;
	}
	
	
	
	

}
