package com.dunkware.common.util.data;

public interface NetListWatcher {

	void beanInsert(NetList list, NetBean bean);
	
	void beanUpdate(NetList list, NetBean bean); 
	
	void beanRemove(NetList list, NetBean bean);
}
