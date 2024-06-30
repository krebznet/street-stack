package com.dunkware.xstream.core.signal.search;

import com.dunkware.xstream.api.XStreamSignalSearch;

public class XStreamSignalSearchBuilder {

	public static XStreamSignalSearchBuilder builder() { 
		return new XStreamSignalSearchBuilder();
	}
	
	private XStreamSignalSearchImpl search = new XStreamSignalSearchImpl();
	
	private XStreamSignalSearchBuilder() { 
		
	}
	
	public XStreamSignalSearchBuilder signalTypeFilter(int...ids) { 
		TypeCriteria crit = new TypeCriteria(ids);
		search.addPredicate(crit);
		return this;
	}
	
	public XStreamSignalSearchBuilder entityFilter(int...ids) { 
		EntityCriteria crit = new EntityCriteria(ids);
		search.addPredicate(crit);
		return this;
	}
	
	public XStreamSignalSearch build() { 
		return search;
	}
	
	
}
