package com.dunkware.xstream.model.value;

import com.dunkware.xstream.model.spec.StreamSpecEntitySignal;
import com.dunkware.xstream.model.util.TimeRangeSession;

public class EntitySignalCountSession {

	private TimeRangeSession timeRange; 
	private StreamSpecEntitySignal signal;

	public TimeRangeSession getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeSession timeRange) {
		this.timeRange = timeRange;
	}
	public StreamSpecEntitySignal getSignal() {
		return signal;
	}
	public void setSignal(StreamSpecEntitySignal signal) {
		this.signal = signal;
	}
	
	
	
}
