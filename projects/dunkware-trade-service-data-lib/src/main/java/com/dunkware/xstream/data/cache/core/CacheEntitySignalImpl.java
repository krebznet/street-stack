package com.dunkware.xstream.data.cache.core;

import java.time.LocalDateTime;

import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheValueSet;

public class CacheEntitySignalImpl implements CacheEntitySignal {
	
	private int id; 
	private String identifier; 
	private LocalDateTime time; 
	
	private String type; 
	
	private String entityIdentifier; 
	private int entityId; 
	
	private CacheValueSet vars;
	private CacheStream stream; 
	
	public CacheEntitySignalImpl(CacheStream stream, int id, String ident, int entityId, String entityIdent, LocalDateTime time, String type, CacheValueSet vars) { 
		this.id = id; 
		this.identifier = ident;
		this.time = time; 
		this.type = type; 
		this.vars = vars; 
		this.stream = stream; 
		this.entityIdentifier = entityIdent;
	}
	
	
	@Override
	public CacheStream getStream() {
		return stream;
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public String getIdentifier() {
		return identifier;
	}
	@Override
	public int getEntityId() {
		return entityId;
	}
	@Override
	public String getEntityIdentifier() {
		return entityIdentifier;
	}
	@Override
	public LocalDateTime getTime() {
		return time;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public CacheValueSet getVars() {
		return vars;
	} 

	


	

}
