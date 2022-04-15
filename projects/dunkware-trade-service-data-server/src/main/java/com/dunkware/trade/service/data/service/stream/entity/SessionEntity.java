package com.dunkware.trade.service.data.service.stream.entity;

import java.util.List;

// then we will update these and mark
// them dirty as updated. 
public interface SessionEntity {
	
	
	public String getIdentifier();
	
	public int getId();
	
	public int getSnapshotCount();
	
	public int getSignalCount();
	
	public List<SessionEntityVar> getVars();
	

	
}
