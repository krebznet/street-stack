package com.dunkware.trade.net.data.server.stream.streamprovider;

import java.util.Collection;

import com.dunkware.trade.service.stream.descriptor.StreamDescriptors;

public interface StreamDataProviders {
	
	public StreamDataProvider getDataProvider(String provider) throws Exception; 
	
	public Collection<StreamDataProvider> getProviders();
	
	public StreamDescriptors getStreamDescriptors();

}
