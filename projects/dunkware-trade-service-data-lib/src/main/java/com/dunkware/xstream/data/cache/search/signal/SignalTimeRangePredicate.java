package com.dunkware.xstream.data.cache.search.signal;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.xstream.data.cache.CacheEntitySignal;

public class SignalTimeRangePredicate implements Predicate<CacheEntitySignal> {

	public static SignalTimeRangePredicate newInstance(LocalDateTime from, LocalDateTime to) { 
		return new SignalTimeRangePredicate(from, to);
	}
	
	private LocalDateTime from;
	private LocalDateTime to;
	
	private SignalTimeRangePredicate(LocalDateTime from, LocalDateTime to) { 
		this.from = from;
		this.to = to;
	}
	
	@Override
	public boolean test(CacheEntitySignal t) {
		if(t.getTime().isAfter(from) && to.isBefore(to)) { 
			return true;
		}
		return false; 
	}
	
	
	

}
