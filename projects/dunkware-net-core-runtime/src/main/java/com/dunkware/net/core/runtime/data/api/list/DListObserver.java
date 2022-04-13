package com.dunkware.net.core.runtime.data.api.list;

import com.dunkware.net.core.runtime.data.api.bean.DBean;

public interface DListObserver {
	
	public void listInsert(DList list, int index);

	public void listRemove(DList list, DBean bean);
	
}
