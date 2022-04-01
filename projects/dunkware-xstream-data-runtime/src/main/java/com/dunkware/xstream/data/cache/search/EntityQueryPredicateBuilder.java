package com.dunkware.xstream.data.cache.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.dunkware.net.proto.stream.GEntityQuery;
import com.dunkware.xstream.data.cache.CacheEntity;
import com.dunkware.xstream.data.cache.search.entity.EntityIncludePredicate;

public class EntityQueryPredicateBuilder {
	
	public static List<Predicate<CacheEntity>> build(GEntityQuery query) {
		List<Predicate<CacheEntity>> preds = new ArrayList<Predicate<CacheEntity>>();
		if(query.getEntitiesCount() > 0) { 
			preds.add(EntityIncludePredicate.newInstance(query.getEntitiesList()));
		}
		if(query.getSignalCountCritierasList().size() > 0) { 
			// if historicla date range - 
			// need to get all signals of type x within a givien time range where entity is = to this 
		}
		
		if(query.getVarCriteriasList().size() > 0) {
			
		}
		// maybe relative signal count
		return preds;
	}

}
