package com.dunkware.trade.sdk.core.runtime.system;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public interface System {

	public void init(SystemType type, SystemContext context) throws SystemException;
	
	public void resume(SystemType type, SystemContext context) throws SystemException;
	
	public void stop(); 
	
	
}
