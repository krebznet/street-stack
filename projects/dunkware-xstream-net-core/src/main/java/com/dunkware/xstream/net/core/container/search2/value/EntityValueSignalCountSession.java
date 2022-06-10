package com.dunkware.xstream.net.core.container.search2.value;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.filter.EntityFilterHelper;
import com.dunkware.xstream.net.core.container.search2.util.StreamSearchUtil;
import com.dunkware.xstream.net.model.search.EntitySignalCountSession;
import com.dunkware.xstream.net.model.search.SessionEntityValue;
import com.dunkware.xstream.net.model.search.TimeRangeSession;

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
	public boolean canResolve(ContainerEntity entity) {
		return true; 
	}

	@Override
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException {
		return true; 
	}

	@Override
	public Object resolve(ContainerEntity entity) throws ContainerSearchException {
		return EntityFilterHelper.getSignalCount(timeRange, entity, signalCount.getSignal().getIdentifier());
	}
	
	

}
