package com.dunkware.common.tick.reactor.impl.updaters;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.common.tick.reactor.TickReactorException;
import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;
import com.dunkware.common.tick.reactor.blueprint.updater.TimeUpdaterType;
import com.dunkware.common.tick.reactor.impl.TickReactorTick;
import com.dunkware.common.tick.reactor.impl.TickReactorUpdater;
import com.dunkware.common.tick.time.TimeTick;
import com.dunkware.common.util.dtime.DTime;

public class TickReactorTimeUpdater implements TickReactorUpdater {

	private TimeUpdaterType type;
	private AtomicInteger counter = new AtomicInteger();
	
	@Override
	public void init(ReactorUpdater type) throws TickReactorException {
		this.type = (TimeUpdaterType)type;
	}

	@Override
	public boolean updateReactorTick(TickReactorTick tick) {
		if(counter.incrementAndGet() == type.getInterval()) { 
			DTime time = TimeTick.decode(tick.createTick());
			DTime updated = DTime.from(time.get().plusSeconds(1));
			
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
