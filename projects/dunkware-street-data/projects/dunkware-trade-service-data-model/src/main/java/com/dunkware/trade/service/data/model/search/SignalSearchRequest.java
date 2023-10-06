package com.dunkware.trade.service.data.model.search;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//SD21-GIFT-02 We added a REST API where we expect the HTTP request body to deserialize to this, its like what we would in GraphQL except shema's and all that shit is ovekill
public class SignalSearchRequest {
	//SD21-GIFT-03  i we need a stream identifier or stream so we will use it a bit later
	private String stream; 
	
	//SD21-GIFT-04 You will understand the other fields as we go through the next gifts.  
	/**
	 * If not null we restrict or add filter on mongo query where the signal types are wrapped
	 * in a or with the ids in array. 
	 */
	private List<Integer> signalTypes = new ArrayList<Integer>();
	/**
	 * Same as above if nut null we filter so the entity id must be = 
	 * to one of the values in the list below;
	 */
	private List<Integer> entities  = new ArrayList<Integer>();
	private LocalDate searchRangeStart = null;
	private LocalDate searchRangeStop = null;
	private Integer limit = 10000;
	
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public List<Integer> getEntities() {
		return entities;
	}
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}

	public LocalDate getSearchRangeStart() {
		return searchRangeStart;
	}
	public void setSearchRangeStart(LocalDate searchRangeStart) {
		this.searchRangeStart = searchRangeStart;
	}
	public LocalDate getSearchRangeStop() {
		return searchRangeStop;
	}
	public void setSearchRangeStop(LocalDate searchRangeStop) {
		this.searchRangeStop = searchRangeStop;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	

}
