package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import java.util.HashMap;

public class CacheEntity {

	private int key; 
	private HashMap<Integer,CacheElement> elements = new HashMap<Integer,CacheElement>();
	
	public CacheEntity(int key) { 
		this.key = key; 
	}
	
	public void put(Integer key, CacheElement element) { 
		this.elements.put(key,element);
	}
	
	public CacheElement get(int element) { 
		return elements.get(element);
	}
	
	public CacheElement getOrCreate(Integer key) { 
		CacheElement element = elements.get(key);
		if(element == null) { 
			element = new CacheElement(key);
			elements.put(key, element);
		}
		return element;
	}
	
	
}
