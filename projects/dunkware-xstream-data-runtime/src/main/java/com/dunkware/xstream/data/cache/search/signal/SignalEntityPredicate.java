package com.dunkware.xstream.data.cache.search.signal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.dunkware.xstream.data.cache.CacheEntitySignal;

public class SignalEntityPredicate implements Predicate<CacheEntitySignal> {
	
	public static SignalEntityPredicate newInstance(List<String> types) { 
		Map<String,String> map = new HashMap<String,String>();
		for (String string : types) {
			map.put(string, string);
		}
		return new SignalEntityPredicate(map);
	}
	
	private Map<String,String> types;
	
	private SignalEntityPredicate(Map<String,String> types) { 
		this.types = types;
	}

	@Override
	public boolean test(CacheEntitySignal t) {
		if(types.get(t.getEntityIdentifier()) != null) { 
			return true; 
		}
		return false;
	}
	
	

}
