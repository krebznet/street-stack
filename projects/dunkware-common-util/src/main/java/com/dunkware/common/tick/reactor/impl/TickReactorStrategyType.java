package com.dunkware.common.tick.reactor.impl;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.blueprint.ReactoryStrategy;

public interface TickReactorStrategyType {
	
	
	public void init(TickReactor reactor, ReactoryStrategy blueprintType) throws TickReactorException;
	
	
	public boolean updateReactorTick(TickReactorTick tick);

}
