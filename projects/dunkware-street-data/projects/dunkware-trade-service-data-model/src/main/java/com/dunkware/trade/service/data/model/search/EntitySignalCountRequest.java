package com.dunkware.trade.service.data.model.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//SD-33-01 - request api 
public class EntitySignalCountRequest {

	private Integer entityId;
	private String Stream; 
	// check for null, if both null then we search all 
	private LocalDate searchRangeStart = null;
	private LocalDate searchRangeStop = null;
	
	// if empty then we return a list of EntitySignalCount for each 
	// signal type
	private List<Integer> signalTypes = new ArrayList<Integer>();
	
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getStream() {
		return Stream;
	}
	public void setStream(String stream) {
		Stream = stream;
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

	public List<Integer> getSignalTypes() {
		return signalTypes;
	}

	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
}
