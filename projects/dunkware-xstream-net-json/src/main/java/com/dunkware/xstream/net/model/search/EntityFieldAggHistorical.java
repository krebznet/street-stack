package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntityField;

public class EntityFieldAggHistorical {

	private EntityField field;
	private TimeRangeHistorical timeRange; 
	private EntityFieldAggHistoricalFunction function;
	
	public EntityField getField() {
		return field;
	}
	public void setField(EntityField field) {
		this.field = field;
	}
	public TimeRangeHistorical getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(TimeRangeHistorical timeRange) {
		this.timeRange = timeRange;
	}
	public EntityFieldAggHistoricalFunction getFunction() {
		return function;
	}
	public void setFunction(EntityFieldAggHistoricalFunction function) {
		this.function = function;
	} 
	
	
}
