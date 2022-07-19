package com.dunkware.xstream.net.core.container.search.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.xstream.model.search.SessionEntityFilterValueCompare;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ContainerSearchPredicate;
import com.dunkware.xstream.net.core.container.search.value.EntityValue;

public class EntitySearchValeuComparePredicate extends ContainerSearchPredicate<ContainerEntity>  {

	private Container container; 
	private SessionEntityFilterValueCompare filterType; 
	private EntityValue targetValue = null;
	private EntityValue compareValue = null;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public void init(SessionEntityFilterValueCompare filterType, Container container) throws ContainerSearchException {
		super.init(container);
		this.container = container; 
		this.filterType = filterType;
		try {
			targetValue = EntitySearchHelper.createEntityValue(filterType.getValue1(), container);
			targetValue.init(filterType.getValue1(), container);
			compareValue = EntitySearchHelper.createEntityValue(filterType.getValue2(), container);
			compareValue.init(filterType.getValue2(), container);
		} catch (Exception e) {
			throw new ContainerSearchException("Exception init Entity Value compare filter " + e.toString());
		}
		
	}
	
	@Override
	public boolean test(ContainerEntity t) {
		try {
			searchStart();
			if(targetValue.canResolveSession(t) && compareValue.canResolveSession(t)) { 
				Object resolvedTarget = targetValue.resolve(t);
				Object resolvedCompare = compareValue.resolve(t);
				return EntitySearchHelper.testValueCompare(resolvedTarget, resolvedCompare, filterType.getFunction().getFunction(), filterType.getCondition());
			} else { 
				return false;
			}
		} catch (Exception e) {
			logger.error("Exception testing entity value compare filter " + e.toString());
			return false; 
		} finally { 
			searchStop();
		}
		
		
	}

	@Override
	public void timeUpdate(Container contaienr) {
		// TODO Auto-generated method stub
		
	}

	
}
