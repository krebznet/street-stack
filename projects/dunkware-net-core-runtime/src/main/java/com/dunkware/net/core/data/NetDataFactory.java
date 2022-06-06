package com.dunkware.net.core.data;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.net.core.data.core.NetBeanImpl;

public class NetDataFactory {

	public static NetDataFactory newInstance(DExecutor executor) { 
		return new NetDataFactory(executor);
	}
	
	private DExecutor executor; 
		
	private NetDataFactory(DExecutor executor) { 
		this.executor = executor;
	}
	
	public NetBean createBean(int id) { 
		return new NetBeanImpl(id,executor);
	}
}
