package com.dunkware.trade.service.data.json.search;

import java.util.ArrayList;
import java.util.List;

public class EntitySignalSearchQuery {
	
	private List<String> entities = new ArrayList<String>();
	private List<EntityVarCriteria> varCriterias = new ArrayList<EntityVarCriteria>();
	private List<String> signalTypes = new ArrayList<String>();
	private CalendarRange calendarRange;
	private String streamIdentifier;
	
	public List<String> getEntities() {
		return entities;
	}
	public void setEntities(List<String> entities) {
		this.entities = entities;
	}
	public List<EntityVarCriteria> getVarCriterias() {
		return varCriterias;
	}
	public void setVarCriterias(List<EntityVarCriteria> varCriterias) {
		this.varCriterias = varCriterias;
	}
	public List<String> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<String> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public CalendarRange getCalendarRange() {
		return calendarRange;
	}
	public void setCalendarRange(CalendarRange calendarRange) {
		this.calendarRange = calendarRange;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	} 
	
	
	

}
