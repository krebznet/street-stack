package com.dunkware.xstream.data.cache.search.signal;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheStreamObserver;

public class SignalRelativeRangePredicate extends CacheStreamObserver implements Predicate<CacheEntitySignal> {

	
	public static SignalRelativeRangePredicate newInstance(GTimeUnit timeUnit, int value) {
		return new SignalRelativeRangePredicate(timeUnit, value);
	}
	
	private GTimeUnit timeUnit; 
	private int value; 
	
	private LocalDateTime minTime; 
	private LocalDateTime from; 
	
	private SignalRelativeRangePredicate(GTimeUnit unit, int value) { 
		this.timeUnit = unit; 
		this.value = value;
	}
	
	
	
	@Override
	public void update(CacheStream stream) {
		  minTime = stream.getTime();
			if(timeUnit == GTimeUnit.DAY) { 
				minTime = minTime.minusDays(value);
			}
			if(timeUnit == GTimeUnit.SECOND) { 
				minTime = minTime.minusSeconds(value);
			}
			if(timeUnit == GTimeUnit.MINUTE) { 
				minTime = minTime.minusMinutes(value);
			}
			if(timeUnit == GTimeUnit.HOUR) { 
				minTime = minTime.minusHours(value);
			}
	}


	

	@Override
	public boolean test(CacheEntitySignal t) {
		
		if(t.getTime().isAfter(minTime)) { 
			
			return true;
		}
	
		return false; 
	}
	
	
	

}
