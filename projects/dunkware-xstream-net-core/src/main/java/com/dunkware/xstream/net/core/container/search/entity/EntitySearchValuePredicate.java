package com.dunkware.xstream.net.core.container.search.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchPredicate;
import com.dunkware.xstream.net.core.container.search.value.EntityValue;

public class EntitySearchValuePredicate extends ContainerSearchPredicate<ContainerEntity> {

	
	private Container container; 
	private SessionEntityFilterValue filterType;
	private EntityValue entityValue; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public void init(SessionEntityFilterValue filterType, Container container) throws ContainerSearchException { 
		super.init(container);
		this.filterType = filterType; 
		this.container = container;
		this.entityValue =  EntitySearchHelper.createEntityValue(filterType.getValue(), container);
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
			return EntitySearchHelper.testCondition(resolvedValue, filterType.getCondition());
		} catch (Exception e) {
			logger.error("Got exception on EntityValueFIlter testCondition call " + e.toString());
			return false; 
		}
	}

	@Override
	public void timeUpdate(Container contaienr) {
		// TODO Auto-generated method stub
		
	}
	
	

}
