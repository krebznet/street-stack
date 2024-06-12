package com.dunkware.xstream.model.stats.entity.agg;

import java.time.LocalDate;

public class EntityAggReq {
	
	
	
	// used for relative req type 
	private LocalDate relativeDate;
	private int relativeDays;
	
	// use dfor range req type  
	private LocalDate startDate; 
	private LocalDate enLocalDate; 
	
	private int entity; 
	private int stat; 
	private int aggType; 
	
}
