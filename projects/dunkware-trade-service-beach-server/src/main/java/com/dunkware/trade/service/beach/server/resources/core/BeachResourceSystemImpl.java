package com.dunkware.trade.service.beach.server.resources.core;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.service.beach.server.resources.BeachResourceSystem;

public class BeachResourceSystemImpl implements BeachResourceSystem {

	private SystemType type; 
	private String name; 
	private long id; 
	
	public BeachResourceSystemImpl(SystemType type, String name, long id) { 
		this.type = type; 
		this.name = name; 
		this.id = id; 
	}
	
	@Override
	public SystemType getType() {
		return type; 
	}

	@Override
	public String getName() {
		return name; 
	}

	@Override
	public long getId() {
		return id; 
	}

	
}
