package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.trade.service.beach.protocol.play.Play;
import com.dunkware.trade.service.beach.server.entities.BeachPlayEnt;

public class BeachPlay {
	
	private BeachAccount account; 
	private BeachPlayEnt entity;
	private Play model; 
	
	void load(BeachAccount account, BeachPlayEnt ent) { 
		this.account = account; 
		this.entity = ent;
		
		
	}
	
	

}
