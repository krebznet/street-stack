package com.dunkware.xstream.model.search;

import com.dunkware.xstream.model.spec.StreamSpecEntitySignal;

public class EntitySignalCountHistorical {

	private TimeRangeHistorical timeRange; 
	private StreamSpecEntitySignal signal;
	public TimeRangeHistorical getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeHistorical timeRange) {
		this.timeRange = timeRange;
	} 
	public StreamSpecEntitySignal getSignal() {
		return signal;
	}
	public void setSignal(StreamSpecEntitySignal signal) {
		this.signal = signal;
	 }
	
	
}
