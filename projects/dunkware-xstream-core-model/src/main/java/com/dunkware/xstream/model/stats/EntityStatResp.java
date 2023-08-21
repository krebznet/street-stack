package com.dunkware.xstream.model.stats;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class EntityStatResp {
	
	private EntityStatRespType type; 
	private Number value; 
	private DTime time; 
	private DDate date; 
	private String exception;
	
	public EntityStatRespType getType() {
		return type;
	}
	public void setType(EntityStatRespType type) {
		this.type = type;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public DTime getTime() {
		return time;
	}
	public void setTime(DTime time) {
		this.time = time;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	} 
	
	
	
	

}
