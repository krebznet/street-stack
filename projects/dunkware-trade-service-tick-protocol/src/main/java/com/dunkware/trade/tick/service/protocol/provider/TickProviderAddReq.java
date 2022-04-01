package com.dunkware.trade.tick.service.protocol.provider;

import com.dunkware.trade.tick.model.provider.TickProviderSpec;

public class TickProviderAddReq {
	
	private TickProviderSpec spec;
	
	public TickProviderAddReq() { 
		
	}

	public TickProviderSpec getSpec() {
		return spec;
	}

	public void setSpec(TickProviderSpec spec) {
		this.spec = spec;
	}
	
	
	
	
	
}
