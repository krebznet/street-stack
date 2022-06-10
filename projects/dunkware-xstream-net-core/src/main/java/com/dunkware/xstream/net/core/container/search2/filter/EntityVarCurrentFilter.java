package com.dunkware.xstream.net.core.container.search2.filter;

import java.util.function.Predicate;

import com.dunkware.xstream.net.core.container.Container;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.core.container.search2.value.EntityValue;
import com.dunkware.xstream.net.core.container.search2.value.EntityValue;
import com.dunkware.xstream.net.model.search.SessionEntityFilterValue;
import com.dunkware.xstream.net.model.search.SessionEntityValue;

// auto wire predicdates? 
public class EntityVarCurrentFilter implements Predicate<ContainerEntity> {

	private SessionEntityFilterValue value;
	
	private Container container; 
	
	private EntityValue containerValue;
	
	
	public void init(SessionEntityFilterValue value, Container container) throws ContainerSearchException { 
		this.value = value;
		this.container = container; 
		value.getValue();
		value.getValue().getType(); 
		//Session
		//containerValue = ContainerEntityValueFactory.newInstance(value);
		
		
	}
	
	
	@Override
	public boolean test(ContainerEntity t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
	
}
