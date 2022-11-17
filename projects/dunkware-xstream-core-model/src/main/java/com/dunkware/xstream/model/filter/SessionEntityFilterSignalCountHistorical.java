package com.dunkware.xstream.model.filter;

import com.dunkware.xstream.model.util.Condition;
import com.dunkware.xstream.model.util.TimeRangeHistorical;

public class SessionEntityFilterSignalCountHistorical {

	private TimeRangeHistorical timeRange; 
	private Condition condition; 
	private SessionEntityFilterSignalCountHistorical signalCount;
	public TimeRangeHistorical getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeHistorical timeRange) {
		this.timeRange = timeRange;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public SessionEntityFilterSignalCountHistorical getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(SessionEntityFilterSignalCountHistorical signalCount) {
		this.signalCount = signalCount;
	} 
	
	
	
}
