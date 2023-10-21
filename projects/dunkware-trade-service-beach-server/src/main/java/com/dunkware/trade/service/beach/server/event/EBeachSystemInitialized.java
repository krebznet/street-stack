package com.dunkware.trade.service.beach.server.event;

import com.dunkware.trade.service.beach.server.runtime.BeachSystem;

public class EBeachSystemInitialized extends EBeachSystemEvent {

	public EBeachSystemInitialized(BeachSystem system) {
		super(system);
	}

	
}
