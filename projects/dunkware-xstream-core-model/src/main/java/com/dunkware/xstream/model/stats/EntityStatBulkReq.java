package com.dunkware.xstream.model.stats;

import java.time.LocalDate;
import java.util.List;

public class EntityStatBulkReq {

	private List<String> entities; 
	private LocalDate date; 
	private int relativeDays;
	private String target;
	private String agg;
	private EntityStatReqType type;
	
	public List<String> getEntities() {
		return entities;
	}
	public void setEntities(List<String> entities) {
		this.entities = entities;
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAgg() {
		return agg;
	}
	public void setAgg(String agg) {
		this.agg = agg;
	}
	public EntityStatReqType getType() {
		return type;
	}
	public void setType(EntityStatReqType type) {
		this.type = type;
	}
	
	
	
	
}
