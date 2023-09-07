package com.dunkware.xstream.model.stats.entity.agg;

import java.time.LocalDateTime;

public class EntityAggResp {
	
	private EntityAggRespCode respType; 
	private Number value;
	private LocalDateTime dateTime; 
	private String exception;
	
	public EntityAggRespCode getRespType() {
		return respType;
	}
	public void setRespType(EntityAggRespCode respType) {
		this.respType = respType;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	} 
	
	
	
	
}
