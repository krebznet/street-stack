package com.dunkware.xstream.model.stats.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityStats {

	private int entityId; 
	private String entityIdentifier; 
	private List<EntityStat> stats = new ArrayList<EntityStat>();
	
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
	public List<EntityStat> getStats() {
		return stats;
	}
	public void setStats(List<EntityStat> stats) {
		this.stats = stats;
	}
	
	
	
	

}
