package com.dunkware.net.core.runtime.data;

public interface NetBeanListObserver {

	public void beanUpdate(NetBean bean);
	
	public void beanInsert(NetBean bean);
	
	public void beanRemove(NetBean bean);
}
