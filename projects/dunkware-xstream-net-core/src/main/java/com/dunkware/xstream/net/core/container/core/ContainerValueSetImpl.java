package com.dunkware.xstream.net.core.container.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.net.core.container.ContainerException;
import com.dunkware.xstream.net.core.container.ContainerValueSet;

public class ContainerValueSetImpl implements ContainerValueSet {

	private Map<String,Object> values = new ConcurrentHashMap<String,Object>();

	@Override
	public Map<String, Object> getValues() {
		return values;
	}

	@Override
	public Object getValue(String name) throws ContainerException {
		if(values.get(name) == null) { 
			throw new ContainerException("Value " + name + " not found on value set");
		}
		return values.get(name);
	}

	@Override
	public boolean hasValue(String name) {
		if(values.get(name) == null) { 
			return false;
		}
		return true;
	}

	@Override
	public void setValue(String name, Object value) {
		values.put(name, value);
	}
	
	
	

}
