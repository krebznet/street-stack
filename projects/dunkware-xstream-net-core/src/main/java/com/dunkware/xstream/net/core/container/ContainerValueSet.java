package com.dunkware.xstream.net.core.container;

import java.util.Map;

public interface ContainerValueSet {
	
	Map<String,Object> getValues();

	Object getValue(String name) throws ContainerException;
	
	boolean hasValue(String name);
	
	void setValue(String name, Object value);
	

	
}
