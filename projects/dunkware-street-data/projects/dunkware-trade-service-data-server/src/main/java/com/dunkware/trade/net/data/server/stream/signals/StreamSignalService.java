package com.dunkware.trade.net.data.server.stream.signals;

import java.util.Collection;

public interface StreamSignalService {
	
	public StreamSignalProvider getProvider(String sreamIdentifier) throws Exception;
	
	public Collection<StreamSignalProvider> getProviders();
	

}
