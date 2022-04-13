package com.dunkware.trade.service.data.json.search;

import java.time.LocalDate;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.enums.DOperator;

public class EntitySignalSearchQueryBuilder {
	
	
	

	public static EntitySignalSearchQueryBuilder newBuilder() { 
			return new EntitySignalSearchQueryBuilder();
	}
	
	private EntitySignalSearchQuery query = new EntitySignalSearchQuery();
	
	private EntitySignalSearchQueryBuilder() { 
		
	}
	
	public EntitySignalSearchQueryBuilder setStream(String stream) { 
		query.setStreamIdentifier(stream);
		return this;
	}
	
	public EntitySignalSearchQueryBuilder addSignalTyps(String...types) { 
		for (String string : types) {
			query.getSignalTypes().add(string);
		}
		return this;
	}
	
	public EntitySignalSearchQueryBuilder addEntities(String... entities) { 
		for (String string : entities) {
			query.getEntities().add(string);
		}
		
		return this;
	}
	
	public EntitySignalSearchQueryBuilder addVarCriteria(String identifier, DOperator operator, double value) {
		EntityVarCriteria crit = new EntityVarCriteria();
		crit.setIdentifier(identifier);
		crit.setOperator(operator);
		crit.setValue(value);
		query.getVarCriterias().add(crit);
		return this;
	}
	
	public EntitySignalSearchQueryBuilder setDateRange(LocalDate start, LocalDate stop) { 
		DateRange range = new DateRange();
		range.setStart(DDate.from(start));
		range.setStop(DDate.from(stop));
		query.setCalendarRange(range);
		return this;
	}
	
	public EntitySignalSearchQuery build() { 
		return query;
	}
	
}
