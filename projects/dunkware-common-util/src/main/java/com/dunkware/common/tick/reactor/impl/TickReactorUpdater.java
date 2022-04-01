package com.dunkware.common.tick.reactor.impl;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;

public interface TickReactorUpdater {

	public void init(ReactorUpdater type) throws TickReactorException;
	
	public boolean updateReactorTick(TickReactorTick tick);
}
