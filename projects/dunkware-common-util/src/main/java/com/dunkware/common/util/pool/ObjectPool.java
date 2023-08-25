package com.dunkware.common.util.pool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.dunkware.common.util.dtime.DTime;

public class ObjectPool<T> {
	
	private Class<T> type;
	private Queue<T> pool = new ConcurrentLinkedQueue<T>();
	
	
	public static void main(String[] args) {
		ObjectPool<DTime> pool = new ObjectPool<DTime>();
		try {
			pool.get();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public ObjectPool() { 
		
	}
	
	public int getSize() { 
		return pool.size();
	}
	
	
	public T get() throws Exception { 
	
		
		if(pool.size() == 0) { 
			try {
		
				Type t = getClass().getGenericSuperclass();
		        ParameterizedType pt = (ParameterizedType) t;
		 
		        type = (Class) pt.getActualTypeArguments()[0];
			        
				T object = type.newInstance();
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
