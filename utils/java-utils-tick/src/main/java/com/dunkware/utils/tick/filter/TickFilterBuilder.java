package com.dunkware.utils.tick.filter;

import com.dunkware.utils.tick.filter.TickFilter.TypeCriteria;

public class TickFilterBuilder {
	
	public static TickFilterBuilder newBuiler() { 
		return new TickFilterBuilder();
	}
	
	
	public static TickFilter typeIncludeFilter(Integer...types) {
		int[] intTypes = new int[types.length];
		int i = 0;
		for (Integer type : types) {
			intTypes[0] = type;
			i++;
		}
		return newBuiler().includeTickTypes(intTypes).build();
	}
	
	public static TickFilter typeExcludeFilter(int...types) {
		return newBuiler().excludeTickTypes(types).build();
	}
	
	private TickFilter.TypeCriteria tickTypeCriteria = null; 
	private int[] tickTypes; 
	
	public TickFilterBuilder includeTickTypes(int...types) {
		this.tickTypes = types;
		tickTypeCriteria = TypeCriteria.Include;
		return this;
	}
	
	public TickFilterBuilder excludeTickTypes(int...types) { 
		this.tickTypes = types;
		tickTypeCriteria = TypeCriteria.Exclude;
		return this;
	}
	
	public TickFilter build() { 
		return new TickFilter(tickTypeCriteria, tickTypes);
	}

}
