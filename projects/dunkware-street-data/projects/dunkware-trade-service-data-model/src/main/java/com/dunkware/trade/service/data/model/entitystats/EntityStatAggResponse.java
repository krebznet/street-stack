package com.dunkware.trade.service.data.model.entitystats;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntityStatAggResponse {

	// did we have enough days of data? 
	private String requestId; 
	private List<EntityStatAgg> aggs = new ArrayList<EntityStatAgg>();
	private int unresolvedCount; 
	private int errorCount;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public List<EntityStatAgg> getAggs() {
		return aggs;
	}
	public void setAggs(List<EntityStatAgg> aggs) {
		this.aggs = aggs;
	}
	public int getUnresolvedCount() {
		return unresolvedCount;
	}
	public void setUnresolvedCount(int unresolvedCount) {
		this.unresolvedCount = unresolvedCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	} 
	
	
	
	
	
	
}
