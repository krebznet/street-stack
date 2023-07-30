package com.dunkware.xstream.model.stats;

public class EntityStatResp {
	
	private EntityStatRespType type; 
	private Number value; 
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
	
	

}
