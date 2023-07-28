package com.dunkware.xstream.model.query;

public class XStreamHistoryTimeRange {

	private XStreamHIstoryTimeRangeType type; 
	private XStreamTimeUnit relativeTimeUnit; 
	private int realtiveTimeRange;
	
	public XStreamHIstoryTimeRangeType getType() {
		return type;
	}
	public void setType(XStreamHIstoryTimeRangeType type) {
		this.type = type;
	}
	public XStreamTimeUnit getRelativeTimeUnit() {
		return relativeTimeUnit;
	}
	public void setRelativeTimeUnit(XStreamTimeUnit relativeTimeUnit) {
		this.relativeTimeUnit = relativeTimeUnit;
	}
	public int getRealtiveTimeRange() {
		return realtiveTimeRange;
	}
	public void setRealtiveTimeRange(int realtiveTimeRange) {
		this.realtiveTimeRange = realtiveTimeRange;
	} 
	
	
	
}
