package com.dunkware.trade.service.stream.server.blueprint.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;

public class EStreamBlueprintEvent extends DEvent {
	
	private StreamBlueprint blueprint; 
	
	public EStreamBlueprintEvent(StreamBlueprint blueprint) { 
		this.blueprint = blueprint;
	}


}
