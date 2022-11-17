package com.dunkware.xstream.model.value;

import com.dunkware.xstream.model.spec.StreamSpecEntityField;
import com.dunkware.xstream.model.util.TimeRangeSession;

public class EntityFieldAggSession {

	private TimeRangeSession timeRange; 
	private StreamSpecEntityField field; 
	private EntityFIeldAggSessionFunction function;
	
	public TimeRangeSession getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeSession timeRange) {
		this.timeRange = timeRange;
	}
	public StreamSpecEntityField getField() {
		return field;
	}
	public void setField(StreamSpecEntityField field) {
		this.field = field;
	}
	public EntityFIeldAggSessionFunction getFunction() {
		return function;
	}
	public void setFunction(EntityFIeldAggSessionFunction function) {
		this.function = function;
	} 
	
	
}
