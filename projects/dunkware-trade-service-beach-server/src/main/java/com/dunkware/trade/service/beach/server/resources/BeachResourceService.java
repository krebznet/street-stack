package com.dunkware.trade.service.beach.server.resources;

import java.util.List;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public interface BeachResourceService {
	
	List<BeachResourceSystem> getSystems();
	
	public void insertSystem(String name, SystemType type) throws Exception; 
	
	public BeachResourceSystem getSystem(long id) throws Exception; 
	
	public void saveSystem(long id, SystemType type) throws Exception;

}
