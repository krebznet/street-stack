package com.dunkware.xstream.net.core.container.search.value;

import com.dunkware.xstream.model.search.EntitySignalCountSession;
import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.model.search.TimeRangeSession;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search.entity.EntitySearchHelper;

public class EntityValueSignalCountSession implements EntityValue  {

	private SessionEntityValue value; 
	private Container container; 
	private EntitySignalCountSession signalCount;
	private TimeRangeSession timeRange;
	
	@Override
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException {
		this.value = value; 
		this.container = container;
		signalCount = value.getSignalCountSession();
		timeRange = value.getSignalCountSession().getTimeRange();
	}

	@Override
	public boolean canResolve(ContainerEntity entity) throws ContainerSearchException{
		return EntitySearchHelper.canFillTimeRangeSession(entity, timeRange);
		
	}

	@Override
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException {
		return true; 
	}

	@Override
	public Object resolve(ContainerEntity entity) throws ContainerSearchException {
		return EntitySearchHelper.getSignalCount(timeRange, entity, signalCount.getSignal().getIdentifier());
	}

	@Override
	public void timeUpdate(Container container) {
		// TODO Auto-generated method stub
		
	}
	
	

}
