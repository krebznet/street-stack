package com.dunkware.xstream.model.search;

public class TimeRangeSession {

	private TimeRangeSessionType type; 
	private int relativeValue; 
	private TimeUnit relativeUnit; 
	private Time absoluteStart; 
	private Time absoluteEnd;
	 
	public TimeRangeSessionType getType() {
		return type;
	}
	public void setType(TimeRangeSessionType type) {
		this.type = type;
	}
	public int getRelativeValue() {
		return relativeValue;
	}
	public void setRelativeValue(int relativeValue) {
		this.relativeValue = relativeValue;
	}
	public TimeUnit getRelativeUnit() {
		return relativeUnit;
	}
	public void setRelativeUnit(TimeUnit relativeUnit) {
		this.relativeUnit = relativeUnit;
	}
	public Time getAbsoluteStart() {
		return absoluteStart;
	}
	public void setAbsoluteStart(Time absoluteStart) {
		this.absoluteStart = absoluteStart;
	}
	public Time getAbsoluteEnd() {
		return absoluteEnd;
	}
	public void setAbsoluteEnd(Time absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	} 
	
	
}
