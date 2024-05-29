package com.dunkware.utils.tick.filter;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.utils.tick.proto.TickProto.Tick;

public class TickFilter {
	
	public enum TypeCriteria { 
		Include,Exclude;
	}
	
	private ConcurrentHashMap<Integer, Integer> tickTypes = new ConcurrentHashMap<Integer, Integer>();
	
	private TypeCriteria typeCriteria = null;
	
	public TickFilter(TypeCriteria typeCriteria, int[] types) { 
		this.typeCriteria = typeCriteria;
		for (int i : types) {
			tickTypes.put(i, i);
		}
	}
	
	public boolean match(Tick tick) { 
		if(typeCriteria == null) { 
			return true;
		}
		if(typeCriteria == TypeCriteria.Include) { 
			if(tickTypes.containsKey(tick.getType())) { 
				return true;
			}
			return false; 
		}
		// we assume it must = exclude
		if(tickTypes.containsKey(tick.getType())) {
			return false; 
		}
		return true;
	}

}
