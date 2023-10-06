package com.dunkware.trade.service.data.model.search;

import java.util.ArrayList;
import java.util.List;

//SD-33-02 - response api 
public class EntitySignalCountResponse {
	
	private Integer entityId;
	private double searchTime; 
	
	private List<EntitySignalCount> signalCounts = new ArrayList<EntitySignalCount>();

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public List<EntitySignalCount> getSignalCounts() {
		return signalCounts;
	}

	public void setSignalCounts(List<EntitySignalCount> signalCounts) {
		this.signalCounts = signalCounts;
	}

	public double getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(double searchTime) {
		this.searchTime = searchTime;
	}
	
	
	
	

}
