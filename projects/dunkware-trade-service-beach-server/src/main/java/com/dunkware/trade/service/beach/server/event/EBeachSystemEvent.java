package com.dunkware.trade.service.beach.server.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.system.BeachSystem;

public class EBeachSystemEvent extends DEvent {

	private BeachSystem system; 
	public EBeachSystemEvent(BeachSystem system) { 
		this.system = system; 
	}
	
	public BeachSystem getSystem() { 
		return system;
	}
}
