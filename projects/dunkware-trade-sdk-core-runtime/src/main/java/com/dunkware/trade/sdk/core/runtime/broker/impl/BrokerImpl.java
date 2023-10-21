package com.dunkware.trade.sdk.core.runtime.broker.impl;

import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerConnected;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerConnecting;
import com.dunkware.trade.sdk.core.runtime.broker.event.EBrokerDisconnected;
import com.dunkware.trade.tick.api.instrument.impl.InstrumentProviderImpl;

public abstract class BrokerImpl extends InstrumentProviderImpl implements Broker  {

	private BrokerStatus status = BrokerStatus.Disconnected;
	
	@Override
	public BrokerStatus getStatus() {
		return status; 
	}

	protected void setStatus(BrokerStatus status) {
		boolean notify = false; 
		if(this.status != status) { 
			notify = true; 
		}
		this.status = status;
		if(notify) { 
			if(status == BrokerStatus.Connected) { 
				getEventNode().event(new EBrokerConnected(this));
			}
			if(status == BrokerStatus.Disconnected) { 
				getEventNode().event(new EBrokerDisconnected(this));
			}
			if(status == BrokerStatus.Connecting) { 
				getEventNode().event(new EBrokerConnecting(this));
			}
		}
		
	}
	
	
}
