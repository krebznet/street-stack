 package com.dunkware.trade.service.beach.server.trade;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public interface BeachSystem  {

	/**
	 * Returns the beach broker account that trades are executed through
	 * @return
	 */
	BeachAccount getAccount();
	
	
	SystemType getSystemType();
	
	// Spec - status -> Unrealized GainLoss -> UnrealizedGainLossPercent -> Open trades -> capital traded. 
	
	
	
	

}
