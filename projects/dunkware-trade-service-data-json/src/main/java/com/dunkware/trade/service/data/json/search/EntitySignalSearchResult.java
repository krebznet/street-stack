package com.dunkware.trade.service.data.json.search;

import com.dunkware.common.util.dtime.DDateTime;

public class EntitySignalSearchResult {
	
	private DDateTime time; 
	private String identifier; 
	private String entityIdentifier;
	
	public DDateTime getTime() {
		return time;
	}
	public void setTime(DDateTime time) {
		this.time = time;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getEntityIdentifier() {
		return entityIdentifier;
	}
	public void setEntityIdentifier(String entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}
	
	

}
