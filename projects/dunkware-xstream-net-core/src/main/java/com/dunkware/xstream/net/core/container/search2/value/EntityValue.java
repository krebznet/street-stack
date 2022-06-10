package com.dunkware.xstream.net.core.container.search2.value;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.model.search.SessionEntityValue;

public interface EntityValue {
	
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException;
	
	/**
	 * if returns false, means that it can't resolve beacuse of historical 
	 * data requirements. 
	 * @return
	 */
	public boolean canResolve(ContainerEntity entity);

	/**
	 * Checks if it can resolve after canResolve() returns true. 
	 * @return
	 * @throws ContainerSearchException
	 */
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException;
	
	
	public Object resolve(ContainerEntity entity) throws ContainerSearchException;
	
	
	
}
