package com.dunkware.xstream.net.core.container;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public abstract class ContainerSearchPredicate<T> implements Predicate<T> {
	
	public abstract void timeUpdate(Container contaienr);
	
	private LocalDateTime searchStartTime = null; 
	private LocalDateTime searchStopTime = null;
	
	protected Container container; 
	protected SessionEntitySearch search; 
	
	protected void init(Container container) { 
		this.container = container;
		
	}
	
	protected void searchStart() {
		searchStartTime = container.getDateTime();
	}
	
	protected void searchStop() { 
		searchStopTime = container.getDateTime();
	}
	
	public int getSearchTime() { 
		if(searchStartTime == null || searchStopTime == null) { 
			return -1; 
		}
		// get the calendar period between the times (years, months & days)
		Duration  duration = Duration.between(searchStartTime.toInstant(container.getZoneOffset()), searchStopTime.toInstant(container.getZoneOffset()));
		return duration.getNano();
		
	}
}
