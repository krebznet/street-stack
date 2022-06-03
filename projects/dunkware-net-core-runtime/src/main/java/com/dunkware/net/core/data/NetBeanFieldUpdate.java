package com.dunkware.net.core.data;

public interface NetBeanFieldUpdate {
	
	public Object getOldValue();
	
	public Object getNewValue();
	
	public NetBeanField getField();

}
