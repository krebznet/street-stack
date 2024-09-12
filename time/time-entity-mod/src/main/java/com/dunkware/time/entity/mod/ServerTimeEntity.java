package com.dunkware.time.entity.mod;

import java.util.Map;

import com.dunkware.time.entity.model.data.TimeEntity;
import com.dunkware.time.entity.model.type.TimeEntityType;

public interface ServerTimeEntity {

	public String getTypeName();
	
	public String getIdentifier();
	
	public long getId();
	
	public int getVersion();
	
	public Map<String,Object> getProperties();
	
	public TimeEntityType getTypeDTO();
	
	public TimeEntity getDTO();
	
	
}


// stream-service
// stream-model
// stream-
// 
// tick-model
// T
// tick-client 
// 	TickStream