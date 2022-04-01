package com.dunkware.common.tick.reactor.impl.stategies;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.common.tick.reactor.blueprint.ReactoryStrategy;
import com.dunkware.common.tick.reactor.blueprint.strategy.DefaultStrategyType;
import com.dunkware.common.tick.reactor.impl.TickReactor;
import com.dunkware.common.tick.reactor.impl.TickReactorStrategyType;
import com.dunkware.common.tick.reactor.impl.TickReactorTick;
import com.dunkware.common.tick.reactor.impl.TickReactorUpdater;

public class TickReactorDefaultStrategy implements TickReactorStrategyType {

	private DefaultStrategyType blueprintType;
	private TickReactor reactor;
	private List<TickReactorUpdater> updaters = new ArrayList<TickReactorUpdater>();

	@Override
	public void init(TickReactor reactor, ReactoryStrategy blueprintType) throws TickReactorException {
		try {
			this.blueprintType = (DefaultStrategyType) blueprintType;
			this.reactor = reactor;
		} catch (Exception e) {
			throw new TickReactorException("Exception init tick reactor default strategy type");
		}
		for (ReactorUpdater reactorUpdater : this.blueprintType.getUpdaters()) {

			TickReactorUpdater updater = reactor.createUpdater(reactorUpdater);
			updater.init(reactorUpdater);
			updaters.add(updater);
		}

	}

	@Override
	public boolean updateReactorTick(TickReactorTick tick) {
		boolean changed = false;
		for (TickReactorUpdater updater : updaters) {
			boolean retValue = updater.updateReactorTick(tick);
			if (!changed && retValue) {
				changed = true;
			}
		}
		return changed;
	}

}
