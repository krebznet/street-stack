package com.dunkware.xstream.model.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StreamEntityStats {

	private String entityIdent; 
	private int entityId; 
	
	private List<StreamEntityDayStats> days = new ArrayList<StreamEntityDayStats>();

	public String getEntityIdent() {
		return entityIdent;
	}

	public void setEntityIdent(String entityIdent) {
		this.entityIdent = entityIdent;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public List<StreamEntityDayStats> getDays() {
		return days;
	}

	public void setDays(List<StreamEntityDayStats> days) {
		this.days = days;
	}
	
	@JsonIgnore
	public boolean hasDate(LocalDate date) { 
		for (StreamEntityDayStats day : days) {
			if(day.getDate().getDayOfYear() == date.getDayOfYear()) { 
				return true; 
			}
		}
		return false;
	}
	
	
	
	
}
