package com.dunkware.utils.tick.reactor.impl;

import com.dunkware.utils.tick.TickException;
import com.dunkware.utils.tick.reactor.TickReactorException;
import com.dunkware.utils.tick.reactor.blueprint.ReactorUpdater;

public interface TickReactorUpdater {

	public void init(ReactorUpdater type) throws TickReactorException;
	
	public boolean updateReactorTick(TickReactorTick tick) throws TickException;
}
