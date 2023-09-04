package com.dunkware.xstream.model.stats.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityStats {

	private int entityId; 
	private String entityIdentifier; 
	private Map<Integer,EntityStat> sats = new ConcurrentHashMap<Integer,EntityStat>();
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getEntityIdentifier() {
		return entityIdentifier;
	}
	public void setEntityIdentifier(String entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}
	public Map<Integer, EntityStat> getSats() {
		return sats;
	}
	public void setSats(Map<Integer, EntityStat> sats) {
		this.sats = sats;
	}

	

}
