package com.dunkware.xstream.net.core.container.search.signal;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.net.proto.data.GTimeUnit;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.ContainerObserver;

public class SignalRelativeRangePredicate extends ContainerObserver implements Predicate<ContainerEntitySignal> {

	
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
	public void update(Container stream) {
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
	public boolean test(ContainerEntitySignal t) {
		
		if(t.getTime().isAfter(minTime)) { 
			
			return true;
		}
	
		return false; 
	}
	
	
	

}
