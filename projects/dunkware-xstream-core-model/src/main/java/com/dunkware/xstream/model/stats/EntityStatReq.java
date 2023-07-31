package com.dunkware.xstream.model.stats;

import java.time.LocalDate;

public class EntityStatReq {
	
	private String entity; 
	private String stream; 
	private LocalDate date; 
	private int relativeDays;
	private String target;
	
	private EntityStatReqType type;

	
	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getRelativeDays() {
		return relativeDays;
	}

	public void setRelativeDays(int relativeDays) {
		this.relativeDays = relativeDays;
	}

	public EntityStatReqType getType() {
		return type;
	}

	public void setType(EntityStatReqType type) {
		this.type = type;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	} 
	
	
	
	

}
