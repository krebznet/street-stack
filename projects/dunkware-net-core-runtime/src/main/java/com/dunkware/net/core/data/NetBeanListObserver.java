package com.dunkware.net.core.data;

public interface NetBeanListObserver extends NetBeanObserver {

	public void beanInsert(NetBean bean);
	
	public void beanRemove(NetBean bean);
}
