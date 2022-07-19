package com.dunkware.xstream.net.core.container.search.value;

import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public class EntityValueSignalCountHistorical implements EntityValue {

	private SessionEntityValue value; 
	private Container container; 
	
	@Override
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException {
		this.value = value; 
		this.container = container; 
		// ContainerExtension
	}

	@Override
	public boolean canResolve(ContainerEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object resolve(ContainerEntity entity) throws ContainerSearchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void timeUpdate(Container container) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
