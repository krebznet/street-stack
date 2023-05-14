package com.dunkware.trade.service.beach.server.runtime.core;

import com.dunkware.trade.service.beach.protocol.play.PlayExitTrigger;
import com.dunkware.trade.service.beach.protocol.play.PlayExitTriggerType;
import com.dunkware.trade.service.beach.server.runtime.core.exit.trigger.BeachTradeTrimerExitTrigger;

public class BeachTradeExitTriggerFactory {

	public static BeachTradeExitTrigger create(PlayExitTrigger model) throws Exception { 
		if(model.getType() == PlayExitTriggerType.TIMER) { 
			return new BeachTradeTrimerExitTrigger();
		}
		throw new Exception("Trigger type " + model.getType() + " not handled in factory");
	}
}
