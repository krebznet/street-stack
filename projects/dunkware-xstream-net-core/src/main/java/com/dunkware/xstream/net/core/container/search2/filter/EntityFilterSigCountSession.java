package com.dunkware.xstream.net.core.container.search2.filter;

import java.util.function.Predicate;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.value.EntityValue;
import com.dunkware.xstream.net.model.search.Condition;
import com.dunkware.xstream.net.model.search.EntitySignalCountSession;
import com.dunkware.xstream.net.model.search.SessionEntityFilterSignalCountSession;

public class EntityFilterSigCountSession implements Predicate<ContainerEntity> {

	 
	private Container container; 
	private String signalIdent; 
	private Condition condition; 
	private EntitySignalCountSession signalCount;
	private EntityValue entityValue;
	
	public void init(SessionEntityFilterSignalCountSession filter, Container container) throws ContainerSearchException { 
		this.container = container; 
		//filter.getValue().getValue();
		//this.signalCount = filter.get
		//		filter.getSignalCount()
		
		try {
		//	entityValue = EntityFilterPredicateBuilder.createEntityValue(filter.get.getValue(),container);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public boolean test(ContainerEntity t) {
		
		return false;
	}

	
	
}
