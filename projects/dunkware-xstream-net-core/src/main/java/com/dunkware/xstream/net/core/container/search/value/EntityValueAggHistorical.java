package com.dunkware.xstream.net.core.container.search.value;

import com.dunkware.xstream.model.search.SessionEntityValue;
import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.ext.ContainerAggExt;

public class EntityValueAggHistorical implements EntityValue {
	
	private SessionEntityValue se; 
	private Container container; 
	
	private ContainerAggExt ext;
	private boolean hasAggExt = true;
	@Override
	public void init(SessionEntityValue value, Container container) throws ContainerSearchException {
		this.se = value; 
		this.container = container; 
		if(container.hasExtension(ContainerAggExt.class)) { 
			try {
				ext = (ContainerAggExt)container.getExtension(ContainerAggExt.class);	
			} catch (Exception e) {
				throw new ContainerSearchException("Exception casting container agg ext " + e.toString());
			}
		} else { 
			hasAggExt = false; 
		}
	}

	@Override
	public boolean canResolve(ContainerEntity entity) {
		if(!hasAggExt) { 
			return false; 
		}
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
	
	}

	
	
}
