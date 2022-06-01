package com.dunkware.net.core.runtime.data;

import java.util.List;

public abstract class NetBean {
	
	public List<NetBeanObserver> observers;
	
	public void addObserver(NetBeanObserver observer) { 
		
	}
	
	public void removeObserver(NetBeanObserver observer) { 
		
	}
	
	public void notifyUpdate() { 
		// thread? --
	}
	


}
