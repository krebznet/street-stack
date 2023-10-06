package com.dunkware.trade.service.data.model.entitystats;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatResponse {
	
	private List<EntityStat> values = new ArrayList<EntityStat>(); 
	private double time; 
	private String exception;
	
	public List<EntityStat> getValues() {
		return values;
	}
	public void setValues(List<EntityStat> values) {
		this.values = values;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	} 
	
	
	
	

}
