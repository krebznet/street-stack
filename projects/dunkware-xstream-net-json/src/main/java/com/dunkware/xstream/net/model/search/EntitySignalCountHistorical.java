package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntitySignal;

public class EntitySignalCountHistorical {

	private TimeRangeHistorical timeRange; 
	private EntitySignal signal;
	public TimeRangeHistorical getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeHistorical timeRange) {
		this.timeRange = timeRange;
	} 
	public EntitySignal getSignal() {
		return signal;
	}
	public void setSignal(EntitySignal signal) {
		this.signal = signal;
	 }
	
	
}
