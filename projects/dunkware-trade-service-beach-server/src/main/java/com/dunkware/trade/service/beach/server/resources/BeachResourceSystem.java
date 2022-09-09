package com.dunkware.trade.service.beach.server.resources;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public interface BeachResourceSystem {

	SystemType getType();
	
	String getName(); 
	
	long getId();
	
}
