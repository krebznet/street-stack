package com.dunkware.utils.tick.reactor.impl;

import com.dunkware.utils.tick.TickException;
import com.dunkware.utils.tick.reactor.TickReactorException;
import com.dunkware.utils.tick.reactor.blueprint.ReactoryStrategy;

public interface TickReactorStrategyType {
	
	
	public void init(TickReactor reactor, ReactoryStrategy blueprintType) throws TickReactorException;
	
	
	public boolean updateReactorTick(TickReactorTick tick) throws TickException;

}
