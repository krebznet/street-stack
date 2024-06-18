package com.dunkware.stream.data.cassy.builders;

import java.time.LocalDate;
import java.time.LocalTime;

import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatKey;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;

public class SessionEntityStatRowBuilder {
	
	public static SessionEntityStatRowBuilder newBuilder() { 
		return new SessionEntityStatRowBuilder();
	}
	
	private SessionEntityStatRowBuilder() { 
		
	}
	
	private LocalDate date; 
	private int entity; 
	private int stream; 
	private int element; 
	private int stat; 
	private double value;
	private LocalTime time = null;
	
	public SessionEntityStatRowBuilder streamEntityVarStatValue(int stream, int entity, int element, int stat, double value) { 
		this.stream = stream; 
		this.entity = entity; 
		this.element = element; 
		this.stat = stat; 
		this.value = value; 
		return this;
		
	}
	
	
	public  SessionEntityStatRowBuilder time(LocalTime time) { 
		this.time = time;
		return this;
	}
	
	public SessionEntityStatRowBuilder date(LocalDate date) { 
		this.date = date;
		return this;
				
	}
	
	public DBSessionEntityStatRow build()  { 
		
		DBSessionEntityStatKey key = new DBSessionEntityStatKey(stream, date, entity, stat);
		DBSessionEntityStatRow row = new DBSessionEntityStatRow(key, element, value, time);
		return row;
		
	}

}
