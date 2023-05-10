package com.dunkware.trade.service.beach.server.runtime;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.trade.service.beach.server.entities.BeachAccountEnt;

public class BeachAccount {

	private BeachAccountEnt entity;
	private BeachBroker broker; 
	
	private ConcurrentHashMap<Long,BeachPlay> plays = new ConcurrentHashMap<Long, BeachPlay>();
	
	public void load(BeachBroker broker, BeachAccountEnt ent) { 
		this.entity = ent; 
		this.broker = broker;
	}
	
	// activeTrades 
	
	// plays 
	
	
	// lookup all trades so far and compute 
		// capital traded
		// trade count 
		// realizedGainLoss
	
	// 
}
