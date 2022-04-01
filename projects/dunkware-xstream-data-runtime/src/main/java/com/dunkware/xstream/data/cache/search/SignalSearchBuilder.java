package com.dunkware.xstream.data.cache.search;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.core.GTimeUnit;
import com.dunkware.xstream.data.cache.CacheEntitySignal;
import com.dunkware.xstream.data.cache.search.signal.SignalEntityPredicate;
import com.dunkware.xstream.data.cache.search.signal.SignalRelativeRangePredicate;
import com.dunkware.xstream.data.cache.search.signal.SignalTimeRangePredicate;
import com.dunkware.xstream.data.cache.search.signal.SignalTypePredicate;
import com.dunkware.xstream.data.cache.search.signal.SignalVariablePredicate;

public class SignalSearchBuilder {

	public static SignalSearchBuilder newBuilder() { 
		return new SignalSearchBuilder();
	}
	
	private SignalSearchBuilder() { 
		
	}
	
	
	private List<Predicate<CacheEntitySignal>> predicates = new ArrayList<Predicate<CacheEntitySignal>>();
	
	private List<String> entities = new ArrayList<String>();
	private List<String> signalTypes = new ArrayList<String>();
	
	public SignalSearchBuilder includeEntities(List<String> entities) { 
		this.entities.addAll(entities);
		return this;
	}
	
	public SignalSearchBuilder includeEntity(String entity) { 
		this.entities.add(entity);
		return this;
	}
	
	public SignalSearchBuilder includeSignalType(String type) { 
		this.signalTypes.add(type);
		return this;
	}
	
	public SignalSearchBuilder includeSignalTypes(List<String> types) { 
		this.signalTypes.addAll(types);
		return this;
	}
	
	public SignalSearchBuilder varCriteria(String var, GOperator operator, double value) {
		predicates.add(SignalVariablePredicate.newInstance(var, operator, value));
		return this;
	}
	
	public SignalSearchBuilder relativeTimeRange(GTimeUnit timeUnit, int range) {
		this.predicates.add(SignalRelativeRangePredicate.newInstance(timeUnit, range));
		return this;
	}
	
	public SignalSearchBuilder dateTimeRange(LocalDateTime from, LocalDateTime to) { 
		this.predicates.add(SignalTimeRangePredicate.newInstance(from, to));
		return this;
	}
	
	public List<Predicate<CacheEntitySignal>> build() { 
		if(entities.size() > 0)
			predicates.add(SignalEntityPredicate.newInstance(entities));
		if(signalTypes.size() > 0)
			predicates.add(SignalTypePredicate.newInstance(signalTypes));
		return predicates;
	}
	
	
}
