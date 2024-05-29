package com.dunkware.stream.data.lib.stats.entity;

import java.time.LocalDate;

public class EntityStatReq {
	
	public static final int AggHigh = 1;
	public static final int AggSum = 2; 
	public static final int AggLow = 3; 
	
	
	public static EntityStatReq relativeHigh(int stream, LocalDate date, int days, int entity, int element, int stat) { 
		return new EntityStatReq(AggHigh,stream,date,days,entity,element,stat);
	}
	
	
	private int agg; 
	private LocalDate date; 
	private int days; 
	private int entity; 
	private int element; 
	private int stat;
	private int stream;



	public EntityStatReq(int agg, int stream, LocalDate date, int days, int entity, int element, int stat) {
		super();
		this.agg = agg;
		this.stream = stream;
		this.date = date;
		this.days = days;
		this.entity = entity;
		this.element = element;
		this.stat = stat;
	}

	
	

	public int getStream() {
		return stream;
	}




	public void setStream(int stream) {
		this.stream = stream;
	}




	public int getAgg() {
		return agg;
	}


	public void setAgg(int agg) {
		this.agg = agg;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getDays() {
		return days;
	}


	public void setDays(int days) {
		this.days = days;
	}


	public int getEntity() {
		return entity;
	}


	public void setEntity(int entity) {
		this.entity = entity;
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


	
	
	
	
	
	

}
