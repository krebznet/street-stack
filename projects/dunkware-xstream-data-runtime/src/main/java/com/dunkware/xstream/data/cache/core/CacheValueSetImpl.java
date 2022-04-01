package com.dunkware.xstream.data.cache.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xstream.data.cache.CacheException;
import com.dunkware.xstream.data.cache.CacheValueSet;

public class CacheValueSetImpl implements CacheValueSet {

	private Map<String,Object> values = new ConcurrentHashMap<String,Object>();

	@Override
	public Map<String, Object> getValues() {
		return values;
	}

	@Override
	public Object getValue(String name) throws CacheException {
		if(values.get(name) == null) { 
			throw new CacheException("Value " + name + " not found on value set");
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
