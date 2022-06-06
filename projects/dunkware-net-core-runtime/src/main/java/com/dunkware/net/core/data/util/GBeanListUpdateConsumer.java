package com.dunkware.net.core.data.util;

import com.dunkware.net.core.data.NetBeanList;
import com.dunkware.net.proto.data.GBeanListUpdate;

public interface GBeanListUpdateConsumer {

	public void listUpdate(NetBeanList list, GBeanListUpdate update); 
	
}
