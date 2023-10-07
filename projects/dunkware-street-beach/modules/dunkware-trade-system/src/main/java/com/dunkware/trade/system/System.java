package com.dunkware.trade.system;

import com.dunkware.trade.persistence.system.SystemEntity;

public interface System {

	SystemStatus getStatus();
	
	SystemEntity getEntity();
	
	long getId();
	
	
	
}
