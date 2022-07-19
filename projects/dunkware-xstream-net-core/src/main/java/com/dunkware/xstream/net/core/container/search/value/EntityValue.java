package com.dunkware.xstream.net.core.container.search.value;

import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public interface EntityValue {
	
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException;
	
	/**
	 * if returns false, means that it can't resolve beacuse of historical 
	 * data requirements. 
	 * @return
	 */
	public boolean canResolve(ContainerEntity entity) throws ContainerSearchException;

	/**
	 * Checks if it can resolve after canResolve() returns true. 
	 * @return
	 * @throws ContainerSearchException
	 */
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException;
	
	
	public Object resolve(ContainerEntity entity) throws ContainerSearchException;
	
	
	void timeUpdate(Container container);
	
	
	
}
