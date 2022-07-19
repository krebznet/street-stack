package com.dunkware.xstream.net.core.container.search.value;

import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public class EntityValueCurrentVar implements EntityValue {

	private SessionEntityValue value; 
	private String varIdent = null;
	
	@Override
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException {
		 varIdent = value.getField().getIdentifier();
		
		
	}

	@Override
	public boolean canResolve(ContainerEntity entity) {
		if(entity.getVar(varIdent) == null) { 
			// log me 
			return false; 
		}
		return true; 
		
		
		
	}

	@Override
	public boolean canResolveSession(ContainerEntity entity) throws ContainerSearchException {
		if(entity.getVar(varIdent).getLastValue() != null) { 
			return true;
		}
		return false; 
		
	}

	@Override
	public Object resolve(ContainerEntity entity) throws ContainerSearchException {
		return entity.getVar(varIdent).getLastValue();
	}

	@Override
	public void timeUpdate(Container container) {
		// TODO Auto-generated method stub
		
	}
	
	

	

	

	
}
