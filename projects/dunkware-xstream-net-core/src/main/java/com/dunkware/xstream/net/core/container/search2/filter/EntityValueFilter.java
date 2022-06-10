package com.dunkware.xstream.net.core.container.search2.filter;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.value.EntityValue;
import com.dunkware.xstream.net.model.search.SessionEntityFilterValue;

public class EntityValueFilter implements Predicate<ContainerEntity> {

	
	private Container container; 
	private SessionEntityFilterValue filterType;
	private EntityValue entityValue; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public void init(SessionEntityFilterValue filterType, Container container) throws ContainerSearchException { 
		this.filterType = filterType; 
		this.container = container;
		this.entityValue =  EntityFilterPredicateBuilder.createEntityValue(filterType.getValue(), container);
		this.entityValue.init(filterType.getValue(),container);
		
	}
 	
	@Override
	public boolean test(ContainerEntity t) {
		Object resolvedValue = null;
		try {
			if(entityValue.canResolveSession(t)) { 
				resolvedValue = entityValue.resolve(t);
				if(resolvedValue == null) { 
					logger.error("EntityValue Filter got a resolved null value on entity value type " + entityValue.getClass().getName());
					return false; 
				}
			}	
		} catch (Exception e) {
			logger.error("Entity value filter caught exception resolving entity value type " + entityValue.getClass().getName());
			return false; 
		}
		try {
			return EntityFilterHelper.testCondition(resolvedValue, filterType.getCondition());
		} catch (Exception e) {
			logger.error("Got exception on EntityValueFIlter testCondition call " + e.toString());
			return false; 
		}
	}

}
