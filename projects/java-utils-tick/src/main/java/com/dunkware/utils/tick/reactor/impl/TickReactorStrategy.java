package com.dunkware.utils.tick.reactor.impl;

import com.dunkware.utils.tick.proto.TickProto.Tick;
import com.dunkware.utils.tick.reactor.TickReactorException;
import com.dunkware.utils.tick.reactor.blueprint.ReactoryStrategy;

public class TickReactorStrategy {

	private TickReactor reactor; 
	private ReactoryStrategy  strategy;
	
	private TickReactorStrategyType type;
	
	private TickReactorTickset tickSet; 
	
	public void init(TickReactor reactor, ReactoryStrategy strategy) throws TickReactorException { 
		this.reactor = reactor; 
		this.strategy = strategy; 
		this.tickSet = reactor.getTickSet(strategy.getTickset());
		type = reactor.createStrategyType(strategy);
		this.type.init(reactor, strategy);
	}
	
	public void run() { 
		try {
			for (TickReactorTick tick : tickSet.getTicks()) {
				boolean changed = type.updateReactorTick(tick);
				if(changed) { 
					Tick realTick = tick.createTick();
					reactor.newTick(realTick);
				}
			}	
		} catch (Exception e) {
			System.err.println("add logging here tick reactory strategy "  + e.toString());

		}
		
	}
	
}
