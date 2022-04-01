package com.dunkware.xstream.data.cache.search.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.dunkware.xstream.data.cache.CacheEntity;

public class EntityIncludePredicate implements Predicate<CacheEntity> {
	
	public static EntityIncludePredicate newInstance(List<String> types) { 
		Map<String,String> map = new HashMap<String,String>();
		for (String string : types) {
			map.put(string, string);
		}
		return new EntityIncludePredicate(map);
	}
	
	private Map<String,String> types;
	
	private EntityIncludePredicate(Map<String,String> types) { 
		this.types = types;
	}

	@Override
	public boolean test(CacheEntity t) {
		if(types.get(t.getIdentifier()) != null) { 
			return true; 
		}
		return false;
	}
	
	

}
