package com.dunkware.utils.tick.reactor;

import com.dunkware.utils.tick.reactor.blueprint.ReactorBlueprint;
import com.dunkware.utils.tick.reactor.impl.TickReactor;

public class TickReactorFactory {

	
	public static TickReactor createReactor(ReactorBlueprint blueprint) throws TickReactorException { 
		TickReactor reactor = new TickReactor(blueprint);
		return reactor;
	}
}
