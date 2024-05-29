package com.dunkware.stream.data.lib.stats.entity;

import java.time.LocalDate;

public class EntityStatDecrementer {
	
	public static void main(String[] args) {
		EntityStatDecrementer d = build(1, LocalDate.of(2024, 10, 30), 3, 32, 1);
		for(int i = 0; i < 100; i++) { 
			System.out.println(d.nextKey());
		}
	}
	
	public static EntityStatDecrementer build(int stream, LocalDate date, int entity, int element, int stat) {
		return new EntityStatDecrementer(stream, date, entity, element, stat);
	}
	
	private int stream;
	private int entity;
	private int element;
	private int stat;
	private LocalDate date;
	private LocalDate lastDate;
	
	
	public EntityStatDecrementer(int stream, LocalDate date, int entity, int element, int stat) {
		super();
		this.date = date;
		this.stream = stream;
		this.entity = entity;
		this.element = element;
		this.stat = stat;
		lastDate = date;
		
	}
	
	
	public String nextKey() { 
		String key = EntityStatHelper.buildStatKey(stream, lastDate, entity, stat, element);
		lastDate = lastDate.minusDays(1);
		return key;
	}
	
	

}
