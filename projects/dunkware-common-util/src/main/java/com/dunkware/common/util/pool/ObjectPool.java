package com.dunkware.common.util.pool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjectPool<T> {
	
	private Class<T> clazz;
	private Queue<T> pool = new ConcurrentLinkedQueue<T>();
	
	
	public ObjectPool() { 
		
	}
	
	public int getSize() { 
		return pool.size();
	}
	
	public T get() throws Exception { 
		if(pool.size() == 0) { 
			try {
				T object = clazz.newInstance();
				return object;	
			} catch (Exception e) {
				throw e;
			}
			
		}
		return pool.remove();
	}
	
	public void put(T object) { 
		pool.add(object);
	}

}
