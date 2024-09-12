package com.dunkware.trade.service.tick.client;

import com.dunkware.trade.service.tick.client.impl.TickServiceClientImpl;

public class TickServiceClientFactory {

	public static TickServiceClient connect(String endpoint) throws TickServiceClientException { 
		TickServiceClientImpl impl = new TickServiceClientImpl();
		impl.connect(endpoint);
		return impl;
	}
}
