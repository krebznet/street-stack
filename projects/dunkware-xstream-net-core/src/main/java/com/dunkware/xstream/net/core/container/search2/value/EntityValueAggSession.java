package com.dunkware.xstream.net.core.container.search2.value;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.util.StreamSearchUtil;
import com.dunkware.xstream.net.model.search.EntityFieldAggSession;
import com.dunkware.xstream.net.model.search.SessionEntityValue;
import com.dunkware.xstream.net.model.search.TimeRangeSession;

public class EntityValueAggSession implements EntityValue {

	private SessionEntityValue value; 
	private Container container; 
	private EntityFieldAggSession aggSession;
	private String varIdent; 
	private TimeRangeSession timeRange; 
	
	@Override
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException {
		this.value = value; 
		this.container = container; 
		aggSession = value.getAggSession();
		aggSession.getField().getIdentifier();
		timeRange = aggSession.getTimeRange();
	}

	@Override
	public boolean canResolve(ContainerEntity entity) {
		// if we are a session agg we should always be able to return true
		return true; 
	}

	@Override
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException {
		if(StreamSearchUtil.canFillTimeRangeSession(entity, timeRange)) { 
			return true; 
		}
		return false;
	}

	@Override
	public Object resolve(ContainerEntity entity) throws ContainerSearchException {
		// getTimeRange of vars 
		// run the function 
		// so if this is 3,543
		// then do the function
		return null;
	}
	
	

}
