package com.dunkware.xstream.model.search;

import com.dunkware.xstream.model.spec.StreamSpecEntityField;

public class EntityFieldAggHistorical {

	private StreamSpecEntityField field;
	private TimeRangeHistorical timeRange; 
	private EntityFieldAggHistoricalFunction function;
	
	public StreamSpecEntityField getField() {
		return field;
	}
	public void setField(StreamSpecEntityField field) {
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
