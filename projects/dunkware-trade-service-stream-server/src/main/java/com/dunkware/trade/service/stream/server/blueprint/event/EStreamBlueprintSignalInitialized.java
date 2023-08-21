package com.dunkware.trade.service.stream.server.blueprint.event;

import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprint;
import com.dunkware.trade.service.stream.server.blueprint.StreamBlueprintSignal;

public class EStreamBlueprintSignalInitialized extends EStreamBlueprintEvent {

	private StreamBlueprintSignal signal;
	
	public EStreamBlueprintSignalInitialized(StreamBlueprint blueprint, StreamBlueprintSignal signal) {
		super(blueprint);
		this.signal = signal;
	}
	
	public StreamBlueprintSignal getSignal() { 
		return signal;
	}

}
