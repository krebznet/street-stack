package com.dunkware.common.tick.reactor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.blueprint.ReactorBlueprint;
import com.dunkware.common.tick.reactor.blueprint.ReactorTickSet;
import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.common.tick.reactor.blueprint.ReactoryStrategy;
import com.dunkware.common.tick.reactor.blueprint.strategy.DefaultStrategyType;
import com.dunkware.common.tick.reactor.blueprint.updater.IncrementUpdaterType;
import com.dunkware.common.tick.reactor.blueprint.updater.TimeUpdaterType;
import com.dunkware.common.tick.reactor.impl.stategies.TickReactorDefaultStrategy;
import com.dunkware.common.tick.reactor.impl.updaters.TickReactorIncrementUpdater;
import com.dunkware.common.tick.reactor.impl.updaters.TickReactorTimeUpdater;
import com.dunkware.common.tick.stream.TickStream;
import com.dunkware.common.tick.stream.TickStreamFactory;

public class TickReactor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ReactorBlueprint blueprint;

	private ConcurrentHashMap<String, TickReactorTickset> ticksets = new ConcurrentHashMap<String, TickReactorTickset>();
	private List<TickReactorStrategy> strategies = new ArrayList<TickReactorStrategy>();

	private TickStream tickRouter;

	private TickReactorStatus status = TickReactorStatus.Initialized;

	private Runner runner = null;
	
	private AtomicBoolean paused = new AtomicBoolean(false);

	public TickReactor(ReactorBlueprint blueprint) throws TickReactorException {
		this.blueprint = blueprint;
		setup();
	}

	// That's all i can do there.

	private void setup() throws TickReactorException {
		// create the ticksets
		tickRouter = TickStreamFactory.newRouter();
		for (ReactorTickSet ts : blueprint.getTicksets()) {
			TickReactorTickset tickset = new TickReactorTickset();
			try {
				tickset.init(ts);
				ticksets.put(ts.getName(), tickset);
			} catch (Exception e) {
				throw new TickReactorException("Exception building tickset " + e.toString(), e);
			}
		}

		// create the strategies
		for (ReactoryStrategy rs : blueprint.getStrategies()) {
			TickReactorStrategy strat = new TickReactorStrategy();
			try {
				strat.init(this, rs);
				strategies.add(strat);
			} catch (Exception e) {
				throw new TickReactorException(
						"Exception creating tick reactor strategy " + rs.getName() + " " + e.toString(), e);
			}

		}

	}

	public TickStream getTickRouter() {
		return tickRouter;
	}

	public TickReactorStatus getStatus() {
		return status;
	}

	public TickReactorUpdater createUpdater(ReactorUpdater type) throws TickReactorException {
		if (type instanceof IncrementUpdaterType) {
			return new TickReactorIncrementUpdater();
		}
		if (type instanceof TimeUpdaterType) {
			return new TickReactorTimeUpdater();
		}
		throw new TickReactorException(
				"Reactor Updater Type " + type.getClass().getName() + " not handled in createUpdater method");
	}

	public TickReactorStrategyType createStrategyType(ReactoryStrategy strategy) throws TickReactorException {
		if (strategy instanceof DefaultStrategyType) {

			return new TickReactorDefaultStrategy();
		}
		throw new TickReactorException(
				"Reactor Strategy Type " + strategy.getClass().getName() + " not handled in create strategy type");
	}

	public void start() throws TickReactorException {
		if (status != TickReactorStatus.Initialized) {
			throw new TickReactorException("Invalid start status " + status.toString());
		}
		status = TickReactorStatus.Running;
		runner = new Runner();
		runner.start();
	}

	public TickReactorTickset getTickSet(String name) throws TickReactorException {
		if (!ticksets.containsKey(name)) {
			throw new TickReactorException("Tickset " + name + " not found");
		}
		return ticksets.get(name);
	}

	public void resume() throws TickReactorException {
		if (status != TickReactorStatus.Paused) {
			throw new TickReactorException("Cannot resume reactor when not in paused status");
		}
		paused.set(false);
		synchronized (runner) {
			runner.notifyAll();	
		}
		
		status = TickReactorStatus.Running;
	}

	public void pause() throws TickReactorException {
		if (status != TickReactorStatus.Running) {
			throw new TickReactorException("Cannot paused reactor when not in running status");
		}

		try {
			paused.set(true);
			status = TickReactorStatus.Paused;
		} catch (Exception e) {
			throw new TickReactorException("Runner therad interrupted exception ");
		}

	}

	public void dispose() throws TickReactorException {

	}

	public void newTick(Tick tick) {
		tickRouter.streamTick(tick);
	}

	/**
	 * This will get paused or whatever when the user does that
	 * 
	 * @author dkrebs
	 *
	 */
	private class Runner extends Thread {

		/**
		 * This goes full blast unless its paused?
		 */
		public void run() {
			setName("TickReactor");
			logger.debug("Starting Tick Reactor Runner Thread");
			System.out.println("Starting Tick Reactor Runner");
			// this is the controller what do you need?
			while (!interrupted()) {
				try {
					// there you go full speed ahead
					synchronized (this) {
						
					
						for (TickReactorStrategy strategy : strategies) {
							if(paused.get()) { 
								wait();
							}
							strategy.run();
							System.out.println("runner running");
						}	
					}
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) {
						return;
					}
					logger.error("Exception in Tick Reactor Runner Thread " + e.toString());
					continue;
				}
			}

		}
	}

}
