package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntitySignal;

public class EntitySignalCountSession {

	private TimeRangeSession timeRange; 
	private EntitySignal signal;

	public TimeRangeSession getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeSession timeRange) {
		this.timeRange = timeRange;
	}
	public EntitySignal getSignal() {
		return signal;
	}
	public void setSignal(EntitySignal signal) {
		this.signal = signal;
	}
	
	
	
}
