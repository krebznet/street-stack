package com.dunkware.xstream.data.cache.search.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.xstream.data.cache.CacheEntity;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.CacheSearchResults;
import com.dunkware.xstream.data.cache.CacheStream;
import com.dunkware.xstream.data.cache.CacheStreamObserver;
import com.dunkware.xstream.data.cache.search.SignalSearchBuilder;

/**
 * Only want to search for 
 * @author duncankrebs
 *
 */
public class EntityRelativeSignalCountPredicate extends CacheStreamObserver implements Predicate<CacheEntity> {

	
	public static EntityRelativeSignalCountPredicate newInstance(String entity, String signalType, GOperator operator, GTimeUnit timeUnit, int TimeValue, int count)  {
		return new EntityRelativeSignalCountPredicate(entity, signalType, operator, timeUnit, count, TimeValue);
	}
	
	private String signalType;
	private int count;
	private GOperator operator;
	private GTimeUnit timeUnit;
	private int timeValue;
	private LocalDateTime minTime;
	private CacheStream stream;
	private String entity;
	
	private List<Predicate<CacheEntitySignal>> signalCountSearch = null;

	private EntityRelativeSignalCountPredicate(String entity, String signalType, GOperator operator, GTimeUnit timeUnit, int count,
			int TimeValue) {
		this.signalType = signalType;
		this.entity = entity;
		this.operator = operator;
		this.timeUnit = timeUnit;
		this.count = count;
	}

	@Override
	public void update(CacheStream stream) {
		this.stream = stream; 
		minTime = stream.getTime();
		if (timeUnit == GTimeUnit.DAY) {
			minTime = minTime.minusDays(timeValue);
		}
		if (timeUnit == GTimeUnit.SECOND) {
			minTime = minTime.minusSeconds(timeValue);
		}
		if (timeUnit == GTimeUnit.MINUTE) {
			minTime = minTime.minusMinutes(timeValue);
		}
		if (timeUnit == GTimeUnit.HOUR) {
			minTime = minTime.minusHours(timeValue);
		}
		 
		signalCountSearch =  SignalSearchBuilder.newBuilder().relativeTimeRange(timeUnit, count).includeEntity(entity).includeSignalType(signalType).build();
		for (Predicate<CacheEntitySignal> predicate : signalCountSearch) {
			if (predicate instanceof CacheStreamObserver) {
				CacheStreamObserver obs = (CacheStreamObserver)predicate;
				obs.update(this.stream);
			}
		}
	}

	@Override
	public boolean test(CacheEntity t) {
		// fun part 
		// new query for signal 
		CacheSearchResults<CacheEntitySignal> results  = stream.signalSearch(signalCountSearch);
		int signalCount = results.getResults().size();
		if(operator == GOperator.GT) { 
			if(count > signalCount) { 
				return true; 
			}
			return false; 
		}
		if(operator == GOperator.LT) { 
			if(count < signalCount) { 
				return true; 
			}
			return false; 
		}
		if(operator == GOperator.GTE) { 
			if(count > signalCount || count == signalCount) { 
				return true; 
			}
			return false; 
		}
		if(operator == GOperator.LTE) { 
			if(count < signalCount || count == signalCount) { 
				return true; 
			}
			return false; 
		}
		if(operator == GOperator.EQ) { 
			if( count == signalCount) { 
				return true; 
			}
			return false; 
		}
		if(operator == GOperator.NQ) { 
			if( count != signalCount) { 
				return true; 
			}
			return false; 
		}
		
		return false;
	}

}
