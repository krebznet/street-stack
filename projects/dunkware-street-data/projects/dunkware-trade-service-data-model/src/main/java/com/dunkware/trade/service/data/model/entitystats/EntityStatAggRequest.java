package com.dunkware.trade.service.data.model.entitystats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityStatAggRequest {
	
	
	private String request; 
	private EntityStatAggType aggType;  
	private LocalDate date;
	private int daysBack; 
	private List<Integer> entities = new ArrayList<Integer>();
	private int element; 
	private int stat; 
	
	public EntityStatAggRequest() { 
		
	}
	
	
	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}





	public int getDaysBack() {
		return daysBack;
	}

	public void setDaysBack(int daysBack) {
		this.daysBack = daysBack;
	}

	
	public List<Integer> getEntities() {
		return entities;
	}


	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}


	public int getElement() {
		return element;
	}

	public void setElement(int element) {
		this.element = element;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}


	public EntityStatAggType getAggType() {
		return aggType;
	}


	public void setAggType(EntityStatAggType aggType) {
		this.aggType = aggType;
	}


	public String getRequest() {
		return request;
	}


	public void setRequest(String request) {
		this.request = request;
	}
	
	
	
	
	

}
