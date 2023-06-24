package com.dunkware.trade.service.beach.server.runtime.core.events;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.beach.server.runtime.BeachPlay;

public class EBeachPlayUpdate extends DEvent {
	
	private BeachPlay play;

	public EBeachPlayUpdate(BeachPlay play) {
		this.play = play;
	}
	
	public BeachPlay getPlay() { 
		return play;
	}
}
