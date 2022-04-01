package com.dunkware.trade.sdk.lib.runtime.signal;

import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.runtime.system.System;
import com.dunkware.trade.sdk.core.runtime.system.SystemContext;
import com.dunkware.trade.sdk.core.runtime.system.SystemException;
import com.dunkware.trade.sdk.lib.model.signal.SignalSystemType;

public class SignalSystem implements System {

	private SignalSystemType type;
	private SystemContext context; 
	
	@Override
	public void init(SystemType type, SystemContext context) throws SystemException {
		this.type = (SignalSystemType)type;
		this.context = (SystemContext)context;
		
	}

	@Override
	public void resume(SystemType type, SystemContext context) throws SystemException {
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	
	// okay we need a signal listener
	//

	
}
