package com.dunkware.xstream.data.cache;

import java.util.Map;

public interface CacheValueSet {
	
	Map<String,Object> getValues();

	Object getValue(String name) throws CacheException;
	
	boolean hasValue(String name);
	
	void setValue(String name, Object value);
	

	
}
