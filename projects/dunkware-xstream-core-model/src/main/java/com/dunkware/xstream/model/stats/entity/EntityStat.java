package com.dunkware.xstream.model.stats.entity;

import java.lang.instrument.Instrumentation;
import java.time.LocalDate;
import java.time.LocalTime;

import com.dunkware.common.util.time.DunkTime;

public class EntityStat {
	
	private int stat; 
	private LocalDate date; 
	private Number value; 
	private int element;
	private int entity;
	private LocalTime time;
	
    private static volatile Instrumentation globalInstrumentation;

    
	public static void main(String[] args) {
		EntityStat stat = new EntityStat();
		//stat.si
		//stat.setDate(LocalDate.now());
		stat.setValue(323.23);
		stat.setEntity(3);
		stat.setElement(3);
		stat.setTime(LocalTime.now());
		stat.setElement(3);;
		long size = globalInstrumentation.getObjectSize(stat);
		
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}

	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getElement() {
		return element;
	}
	public void setElement(int element) {
		this.element = element;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(entity);
		builder.append(",");
		builder.append(DunkTime.formatHHMMSS(this.time));
		builder.append(",");
		builder.append(this.stat);
		builder.append(",").append(this.element).append(",").append(this.value);
		if(this.time != null) { 
			builder.append(DunkTime.formatHHMMSS(this.time));
		}
		return builder.toString();
	}
	
	
	
	
	
	
	
	

}
