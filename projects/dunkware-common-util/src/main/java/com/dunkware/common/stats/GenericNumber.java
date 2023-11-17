package com.dunkware.common.stats;

import java.time.LocalTime;

public class GenericNumber {

	private Number value; 
	private LocalTime time; 
	
	public GenericNumber(Number value, LocalTime time) { 
		this.value = value;
		this.time = time; 
	}
	
	public Number getValue() { 
		return value; 
	}
	
	public LocalTime getTime() { 
		return time; 
	}
}
