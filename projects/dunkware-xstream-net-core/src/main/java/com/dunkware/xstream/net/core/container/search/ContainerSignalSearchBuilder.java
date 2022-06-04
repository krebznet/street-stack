package com.dunkware.xstream.net.core.container.search;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.data.GOperator;
import com.dunkware.net.proto.data.GTimeUnit;
import com.dunkware.xstream.net.core.container.ContainerEntitySignal;
import com.dunkware.xstream.net.core.container.search.signal.SignalEntityPredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalRelativeRangePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalTimeRangePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalTypePredicate;
import com.dunkware.xstream.net.core.container.search.signal.SignalVariablePredicate;

public class ContainerSignalSearchBuilder {

	public static ContainerSignalSearchBuilder newBuilder() { 
		return new ContainerSignalSearchBuilder();
	}
	
	private ContainerSignalSearchBuilder() { 
		
	}
	
	
	private List<Predicate<ContainerEntitySignal>> predicates = new ArrayList<Predicate<ContainerEntitySignal>>();
	
	private List<String> entities = new ArrayList<String>();
	private List<String> signalTypes = new ArrayList<String>();
	
	public ContainerSignalSearchBuilder includeEntities(List<String> entities) { 
		this.entities.addAll(entities);
		return this;
	}
	
	public ContainerSignalSearchBuilder includeEntity(String entity) { 
		this.entities.add(entity);
		return this;
	}
	
	public ContainerSignalSearchBuilder includeSignalType(String type) { 
		this.signalTypes.add(type);
		return this;
	}
	
	public ContainerSignalSearchBuilder includeSignalTypes(List<String> types) { 
		this.signalTypes.addAll(types);
		return this;
	}
	
	public ContainerSignalSearchBuilder varCriteria(String var, GOperator operator, double value) {
		predicates.add(SignalVariablePredicate.newInstance(var, operator, value));
		return this;
	}
	
	public ContainerSignalSearchBuilder relativeTimeRange(GTimeUnit timeUnit, int range) {
		this.predicates.add(SignalRelativeRangePredicate.newInstance(timeUnit, range));
		return this;
	}
	
	public ContainerSignalSearchBuilder dateTimeRange(LocalDateTime from, LocalDateTime to) { 
		this.predicates.add(SignalTimeRangePredicate.newInstance(from, to));
		return this;
	}
	
	public List<Predicate<ContainerEntitySignal>> build() { 
		if(entities.size() > 0)
			predicates.add(SignalEntityPredicate.newInstance(entities));
		if(signalTypes.size() > 0)
			predicates.add(SignalTypePredicate.newInstance(signalTypes));
		return predicates;
	}
	
	
}
