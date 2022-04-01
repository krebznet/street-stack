package com.dunkware.xstream.data.cache;

import java.time.LocalDateTime;

public interface CacheEntitySignal {
	
	public int getId(); 
	
	public String getIdentifier(); 
	
	public int getEntityId();
	
	public String getEntityIdentifier();
	
	LocalDateTime getTime();
	
	String getType(); 
	
	CacheValueSet getVars();
	
	CacheStream getStream();
	
	

}
