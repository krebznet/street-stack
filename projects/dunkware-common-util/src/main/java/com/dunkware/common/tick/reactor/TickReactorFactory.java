package com.dunkware.common.tick.reactor;

import com.dunkware.common.tick.reactor.blueprint.ReactorBlueprint;
import com.dunkware.common.tick.reactor.impl.TickReactor;

public class TickReactorFactory {

	
	public static TickReactor createReactor(ReactorBlueprint blueprint) throws TickReactorException { 
		TickReactor reactor = new TickReactor(blueprint);
		return reactor;
	}
}
