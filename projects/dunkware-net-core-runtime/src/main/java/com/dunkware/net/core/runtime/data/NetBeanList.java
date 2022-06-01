package com.dunkware.net.core.runtime.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.common.util.executor.DExecutor;

public class NetBeanList<T extends NetBean> {
	private List<NetBeanObserver> observers;
	private Semaphore observerLock = new Semaphore(1);
	
	private List<T> elements = new ArrayList<T>();
	
	public NetBeanList(DExecutor executor) { 
		// executor. 
	}
	
	public T get(int index) { 
		return elements.get(index);
	}
	
	public void addObserver(NetBeanObserver observer) { 
		
	}
	
	public void removeListener(NetBeanObserver observer) { 
		
	}
	
	public void add(T element) { 
		elements.add(element);
	}
	
	public void remove(T element) { 
		elements.remove(element);
	}
	
	public void clear() { 
		elements.clear();
	}
	
	public void addAll(Collection<T> collection) { 
		elements.addAll(collection);
	}
	

}
