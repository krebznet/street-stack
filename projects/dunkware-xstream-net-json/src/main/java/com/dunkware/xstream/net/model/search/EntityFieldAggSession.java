package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntityField;

public class EntityFieldAggSession {

	private TimeRangeSession timeRange; 
	private EntityField field; 
	private EntityFIeldAggSessionFunction function;
	
	public TimeRangeSession getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeSession timeRange) {
		this.timeRange = timeRange;
	}
	public EntityField getField() {
		return field;
	}
	public void setField(EntityField field) {
		this.field = field;
	}
	public EntityFIeldAggSessionFunction getFunction() {
		return function;
	}
	public void setFunction(EntityFIeldAggSessionFunction function) {
		this.function = function;
	} 
	
	
}
