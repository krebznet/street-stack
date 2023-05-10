package com.dunkware.trade.service.beach.server.runtime;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.service.beach.server.entities.BeachBrokerEnt;

public class BeachBroker {
	
	private BeachBrokerEnt entity;
	private Map<Long,BeachAccount> accounts = new ConcurrentHashMap<Long,BeachAccount>();

	public void load(BeachBrokerEnt entity) { 
		this.entity = entity; 
		// LOAD CONNECT TO BROKER RIGHT? 
		
	}
	
	public Collection<BeachAccount> getAccounts() { 
		return accounts.values();
	}
	
	
}
