package com.dunkware.trade.service.stream.json.query;

public class WebStreamCriteriaValue {
	
	private String valueType; 
	private String identifier; 
	private String timeRange; 
	private String time; 
	private String timeUnit; 
	private String startTime; 
	private String stopTime; 
	private String sessionAggregation;
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getSessionAggregation() {
		return sessionAggregation;
	}
	public void setSessionAggregation(String sessionAggregation) {
		this.sessionAggregation = sessionAggregation;
	}
	
	

}
