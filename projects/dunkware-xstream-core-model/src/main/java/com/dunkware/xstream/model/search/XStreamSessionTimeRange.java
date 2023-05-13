package com.dunkware.xstream.model.search;

public class XStreamSessionTimeRange {

	private XStreamSessionTimeRangeType type; 
	private XStreamTime relativeTime; 
	private XStreamTime absoluteTimeStart;
	private XStreamTime absoluteTimeEnd;
	
	public XStreamSessionTimeRangeType getType() {
		return type;
	}
	public void setType(XStreamSessionTimeRangeType type) {
		this.type = type;
	}
	public XStreamTime getRelativeTime() {
		return relativeTime;
	}
	public void setRelativeTime(XStreamTime relativeTime) {
		this.relativeTime = relativeTime;
	}
	public XStreamTime getAbsoluteTimeStart() {
		return absoluteTimeStart;
	}
	public void setAbsoluteTimeStart(XStreamTime absoluteTimeStart) {
		this.absoluteTimeStart = absoluteTimeStart;
	}
	public XStreamTime getAbsoluteTimeEnd() {
		return absoluteTimeEnd;
	}
	public void setAbsoluteTimeEnd(XStreamTime absoluteTimeEnd) {
		this.absoluteTimeEnd = absoluteTimeEnd;
	} 
	
	
}
