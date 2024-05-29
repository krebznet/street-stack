package com.dunkware.utils.tick.stream.impl.updaters;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.utils.tick.TickException;
import com.dunkware.utils.tick.reactor.TickReactorException;
import com.dunkware.utils.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.utils.tick.reactor.blueprint.updater.TimeUpdaterType;
import com.dunkware.utils.tick.reactor.impl.TickReactorTick;
import com.dunkware.utils.tick.reactor.impl.TickReactorUpdater;
import com.dunkware.utils.tick.type.TimeTick;

public class TickReactorTimeUpdater implements TickReactorUpdater {

	private TimeUpdaterType type;
	private AtomicInteger counter = new AtomicInteger();
	
	@Override
	public void init(ReactorUpdater type) throws TickReactorException {
		this.type = (TimeUpdaterType)type;
	}

	@Override
	public boolean updateReactorTick(TickReactorTick tick) throws TickException {
		if(counter.incrementAndGet() == type.getInterval()) { 
			LocalTime time = TimeTick.decode(tick.createTick());
			LocalTime updated = time.plusSeconds(1);
			
			tick.setInt(TimeTick.HOUR, updated.getHour());
			tick.setInt(TimeTick.MINUTE, updated.getMinute());
			tick.setInt(TimeTick.SECOND, updated.getSecond());
			tick.setLong(TimeTick.NANOSECOND, updated.getNano());
			counter.set(0);
			return true; 
		}
		return false;
	}
	
	

}
